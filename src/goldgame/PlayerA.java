package goldgame;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;
import ch.aplu.util.SoundPlayerExt;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.ArrayList;

import static goldgame.GameClass.getPNG;
import static goldgame.InputScreen.PLAYERMONEY;
import static goldgame.InputScreen.STEPNUM;
import static goldgame.PlayGame.ghost;
import static goldgame.PlayGame.green;
import static goldgame.PlayGame.invMoney;
import static goldgame.PlayGame.mario;
import static goldgame.PlayGame.sonic;
import static goldgame.PlayGame.vMoney;

/**
 * Represents a player in the gold collection game.
 */
public class PlayerA extends Actor {

    public static int startMoneyA = PLAYERMONEY;
    public static int usedMoneyA = 0;
    public static int stepsTakenA = 0;
    public static int collectedMoneyA = 0;
    public static int costForGoalA = 0;

    int xTargetCoinA;
    int yTargetCoinA;
    int coinIndexA;

    int xTempTargetA;
    int yTempTargetA;

    boolean lostAddedToGrid = false;
    public static int stepCountA = 0;
    public static ArrayList<Location> stepCoordinatesA = new ArrayList<>();

    /**
     * Constructs a new PlayerA object.
     */
    public PlayerA() {
        super("src/goldgame/sprites/mario.png");
        stepCoordinatesA.add(new Location(0, 0));
    }

    @Override
    public void act() {
        boolean check = isMoneyInsufficient();
        this.isGameOver();

        if (check) {
            // Player ran out of money
            // Add lost image to grid
            System.out.println("MARIO ELIMINATED!");
            this.setActEnabled(false);
            this.hide();
            this.stepsTakenA = 0;
            if (!lostAddedToGrid) {
                Actor lostMario = new Actor("src/goldgame/sprites/lostMario.png");
                gameGrid.addActor(lostMario, this.getLocation());
                lostAddedToGrid = true;
            }
            green.stepsTakenB = 0;
            green.getCoinCoordinate(true);
        } else {
            // Player still has money, continue gameplay
            gameGrid.setGridColor(Color.RED);
            if (this.getX() < xTargetCoinA || this.getX() > xTargetCoinA) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinA, this.getY())));
                this.move();
                stepCoordinatesA.add(this.getLocation());
                stepsTakenA++;
                stepCountA++;
            } else if (this.getX() == xTargetCoinA) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinA, yTargetCoinA)));
                this.move();
                stepCoordinatesA.add(this.getLocation());
                stepsTakenA++;
                stepCountA++;
            }

            if (xTargetCoinA == this.getX() && yTargetCoinA == this.getY()) {
                // Player reached targeted coin
                this.setActEnabled(false);
                SoundPlayerExt coinGot = new SoundPlayerExt("src/goldgame/sprites/coin.wav");
                coinGot.setVolume(600);
                coinGot.play();

                startMoneyA += vMoney.get(coinIndexA).value;
                collectedMoneyA += vMoney.get(coinIndexA).value;

                vMoney.get(coinIndexA).removeSelf();
                vMoney.remove(coinIndexA);

                stepsTakenA = 0;
                green.stepsTakenB = 0;
                this.setSlowDown(4);

                System.out.println("MARIO TOOK A COIN");

                System.out.println("NO COINS LEFT? " + vMoney.isEmpty());
                this.getCoinCoordinate(false);

                mario.isGameOver();
                green.getCoinCoordinate(true);

                startMoneyA -= 5;
                usedMoneyA += 5;

            }
            if (stepsTakenA == STEPNUM) {
                // Player reached maximum steps allowed
                this.setActEnabled(false);
                stepsTakenA = 0;
                green.stepsTakenB = 0;
                this.setSlowDown(4);
                mario.isGameOver();
                green.getCoinCoordinate(true);
                startMoneyA -= 5;
                usedMoneyA += 5;
            }
            collideHidden(this.getLocation(), "TURN: MARIO (A)");
        }
    }

    /**
     * Retrieves coordinates of the coin for the player to target.
     * @param isThereGoal Flag indicating if there is a goal to reach.
     */
    public void getCoinCoordinate(boolean isThereGoal) {
        xTempTargetA = this.xTargetCoinA;
        yTempTargetA = this.yTargetCoinA;
        mario.isGameOver();

        ArrayList<Integer> distanceFromMario = new ArrayList<>();
        for (Coin itr : vMoney) {
            int distance = abs(itr.getX() - this.getX()) + abs(itr.getY() - this.getY());
            distanceFromMario.add(distance);
        }

        int min = distanceFromMario.get(0);
        int minIndex = 0;
        for (int i = 0; i < distanceFromMario.size(); i++) {
            if (distanceFromMario.get(i) < min) {
                min = distanceFromMario.get(i);
                minIndex = i;
            }
        }
        this.coinIndexA = minIndex;
        this.xTargetCoinA = vMoney.get(minIndex).getX();
        this.yTargetCoinA = vMoney.get(minIndex).getY();

        if (isThereGoal) {
            this.setActEnabled(true);
        }
        boolean check = this.isMoneyInsufficient();
        if (!check) {
            if (this.xTargetCoinA == xTempTargetA && this.yTargetCoinA == yTempTargetA) {
                System.out.println("GOING TO THE SAME GOAL!");
            } else {
                System.out.println("GOING TO ANOTHER GOAL!");
                startMoneyA -= 5;
                usedMoneyA += 5;
                costForGoalA += 5;
                xTempTargetA = this.xTargetCoinA;
                yTempTargetA = this.yTargetCoinA;
            }
        } else {
            System.out.println("PLAYER A IS ELIMINATED!");
        }
        System.out.println("MARIO HEADING FOR COINT AT X: " + xTargetCoinA + " Y: " + yTargetCoinA);
        System.out.println("\n\n");
    }

    public void collideHidden(Location marioLoc, String a) {
        for (int i = 0; i < invMoney.size(); i++) {
            if (marioLoc.getX() == invMoney.get(i).getX() && marioLoc.getY() == invMoney.get(i).getY()) {
                int invValue = invMoney.get(i).value;
                System.out.println("SOMEONE STEPPED ON A HIDDEN COIN: " + a);
                Coin inv1 = new Coin(sonic.invPNG(invValue), invValue);
                vMoney.add(inv1);
                gameGrid.addActor(inv1, invMoney.get(i).getLocation());

                invMoney.get(i).removeSelf();
                invMoney.remove(i);
            }
        }
    }

    /**
     * Checks if player's money is insufficient.
     * @return True if money is insufficient, false otherwise.
     */
    public boolean isMoneyInsufficient() {
        System.out.println("PLAYER A'S CURRENT MONEY: " + startMoneyA);
        boolean isInsufficient = false;
        if (startMoneyA <= 0) {
            isInsufficient = true;
            startMoneyA = 0;
        }
        return isInsufficient;
    }

    /**
     * Checks if the game is over.
     */
    public void isGameOver() {
        if (vMoney.isEmpty() || (!mario.isVisible() && !green.isVisible() && !sonic.isVisible() && !ghost.isVisible())) {
            mario.setActEnabled(false);
            green.setActEnabled(false);
            sonic.setActEnabled(false);
            ghost.setActEnabled(false);
            System.out.println("A's steps: ");
            for (Location lc : mario.stepCoordinatesA) {
                System.out.print(lc + "-");
            }
            System.out.println("\nTotal steps taken by A: " + mario.stepCountA);
            System.out.println("\nMoney spent by A for steps: " + mario.usedMoneyA);
            System.out.println("\nA's remaining money: " + mario.startMoneyA);
            System.out.println("\nA's collected money: " + mario.collectedMoneyA);
            System.out.println("\nA's money spent for goal determination: " + mario.costForGoalA);
            System.out.println("\n");

            // Other player details omitted for brevity

            vMoney.add(new Coin(getPNG(5), 5));
            vMoney.get(vMoney.size() - 1).hide();
            gameGrid.addActor(vMoney.get(vMoney.size() - 1), gameGrid.getRandomEmptyLocation());
            gameGrid.stopGameThread();
            gameGrid.doPause();

            mario.setActEnabled(false);
            green.setActEnabled(false);
            sonic.setActEnabled(false);
            ghost.setActEnabled(false);

            if (vMoney.size() == 1) {
                JOptionPane.showMessageDialog(null, "OUT OF MONEY!");
            }

            if ((!mario.isVisible() && !green.isVisible() && !sonic.isVisible() && !ghost.isVisible())) {
                JOptionPane.showMessageDialog(null, "PLAYERS ELIMINATED!");
            }
            gameGrid.dispose();
            WriteToFile.writeToOutput();
            ResultScreen rs = new ResultScreen();
            rs.setVisible(true);
            rs.addInfo();

        }

    }

}
