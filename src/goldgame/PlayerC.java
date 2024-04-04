package goldgame;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;
import ch.aplu.util.SoundPlayerExt;
import static goldgame.GameClass.GROWS;
import static goldgame.InputScreen.PLAYERMONEY;
import static goldgame.InputScreen.STEPNUM;
import static goldgame.PlayGame.ghost;
import static goldgame.PlayGame.invMoney;
import static goldgame.PlayGame.mario;
import static goldgame.PlayGame.vMoney;
import java.awt.Color;
import static java.lang.Math.abs;
import java.util.ArrayList;

/**
 * Represents a player in the gold collection game.
 */
public class PlayerC extends Actor {

    public static int startMoneyC = PLAYERMONEY;
    public static int usedMoneyC = 0;
    public static int stepsTakenC = 0;
    public static int collectedMoneyC = 0;
    public static int costForGoalC = 0;

    int xTargetCoinC;
    int yTargetCoinC;
    int coinIndexC;

    int xTempTargetC;
    int yTempTargetC;
    boolean lostAddedToGrid = false;
    public static int stepCountC = 0;

    int moveMoneyC = 5;
    int goalReachedC = 15;

    public static ArrayList<Location> stepCoordinatesC = new ArrayList<>();

    /**
     * Constructs a new PlayerC object.
     */
    public PlayerC() {
        super("src/goldgame/sprites/sonic.png");
        stepCoordinatesC.add(new Location(0, GROWS - 1));
    }

    @Override
    public void act() {
        boolean check = isMoneyInsufficient();
        mario.isGameOver();
        if (check) {
            System.out.println("SONIC ELIMINATED!");
            this.setActEnabled(false);
            this.hide();
            this.stepsTakenC = 0;
            if (!lostAddedToGrid) {
                Actor lostSonic = new Actor("src/goldgame/sprites/lostSonic.png");
                gameGrid.addActor(lostSonic, this.getLocation());
                lostAddedToGrid = true;
            }
            ghost.stepsTakenD = 0;
            ghost.getCoinCoordinate(true);
        } else {
            gameGrid.setGridColor(Color.CYAN);
            if (this.getX() < xTargetCoinC || this.getX() > xTargetCoinC) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinC, this.getY())));
                this.move();
                stepCoordinatesC.add(this.getLocation());
                stepsTakenC++;
                stepCountC++;

            } else if (this.getX() == xTargetCoinC) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinC, yTargetCoinC)));
                this.move();
                stepCoordinatesC.add(this.getLocation());
                stepsTakenC++;
                stepCountC++;

            }
            if (xTargetCoinC == this.getX() && yTargetCoinC == this.getY()) {
                this.setActEnabled(false);
                startMoneyC += vMoney.get(coinIndexC).value;
                collectedMoneyC += vMoney.get(coinIndexC).value;

                vMoney.get(coinIndexC).removeSelf();
                vMoney.remove(coinIndexC);

                ghost.stepsTakenD = 0;
                stepsTakenC = 0;
                this.setSlowDown(4);

                System.out.println("SONIC TOOK A COIN");

                System.out.println("NO COINS LEFT? " + vMoney.isEmpty());
                this.getCoinCoordinate(false);
                mario.isGameOver();
                ghost.getCoinCoordinate(true);

                startMoneyC -= moveMoneyC;
                usedMoneyC += moveMoneyC;
            }

            if (stepsTakenC == STEPNUM) {
                this.setActEnabled(false);
                stepsTakenC = 0;
                ghost.stepsTakenD = 0;
                this.setSlowDown(4);
                mario.isGameOver();
                ghost.getCoinCoordinate(true);

                startMoneyC -= moveMoneyC;
                usedMoneyC += moveMoneyC;
            }

            mario.collideHidden(this.getLocation(), "TURN: SONIC (C)");
        }

    }

    /**
     * Retrieves the coordinates of the coin for the player to target.
     * @param isThereGoal Flag indicating if there is a goal to reach.
     */
    public void getCoinCoordinate(boolean isThereGoal) {

        xTempTargetC = this.xTargetCoinC;
        yTempTargetC = this.yTargetCoinC;

        mario.isGameOver();

        ArrayList<Integer> distanceFromSonic = new ArrayList<>();
        ArrayList<Double> profitPlayerC = new ArrayList<>();

        for (Coin itr : vMoney) {
            int distance = abs(itr.getX() - this.getX()) + abs(itr.getY() - this.getY());
            distanceFromSonic.add(distance);
            profitPlayerC.add((double) itr.value / (double) distance);

        }

        double max = profitPlayerC.get(0);
        int maxIndex = 0;

        for (int i = 0; i < profitPlayerC.size(); i++) {

            if (profitPlayerC.get(i) > max) {
                max = profitPlayerC.get(i);
                maxIndex = i;
            }

        }

        this.coinIndexC = maxIndex;
        this.xTargetCoinC = vMoney.get(maxIndex).getX();
        this.yTargetCoinC = vMoney.get(maxIndex).getY();

        if (isThereGoal) {
            this.setActEnabled(true);
        }

        boolean check = this.isMoneyInsufficient();
        if (!check) {
            if (this.xTargetCoinC == xTempTargetC && this.yTargetCoinC == yTempTargetC) {
                System.out.println("GOING TO THE SAME GOAL!");
            } else {
                System.out.println("GOING TO ANOTHER GOAL!");
                startMoneyC -= goalReachedC;
                usedMoneyC += goalReachedC;
                costForGoalC += goalReachedC;
                xTempTargetC = this.xTargetCoinC;
                yTempTargetC = this.yTargetCoinC;
            }
        } else {
            System.out.println("PLAYER C IS ELIMINATED!");
        }

        System.out.println("C IS HEADING FOR X: " + this.xTargetCoinC + " Y: " + this.yTargetCoinC);
        System.out.println("\n\n");
    }

    /**
     * Opens inventory for Player C.
     */
    public void openInvCoin() {

        ArrayList<Integer> invDistanceFromC = new ArrayList<>();

        for (Coin itr : invMoney) {
            int distance = abs(itr.getX() - this.getX()) + abs(itr.getY() - this.getY());
            invDistanceFromC.add(distance);
        }

        int invMin = invDistanceFromC.get(0);
        int invMinIndex = 0;

        for (int i = 0; i < invDistanceFromC.size(); i++) {

            if (invDistanceFromC.get(i) < invMin) {
                invMin = invDistanceFromC.get(i);
                invMinIndex = i;
            }
        }

        Location invXY = new Location(invMoney.get(invMinIndex).getLocation());
        int invValue = invMoney.get(invMinIndex).value;

        System.out.println("SONIC OPENED A HIDDEN COIN AT: " + invXY);
        Coin inv1 = new Coin(invPNG(invValue), invValue);
        vMoney.add(inv1);
        gameGrid.addActor(inv1, invXY);

        invMoney.get(invMinIndex).removeSelf();
        invMoney.remove(invMinIndex);
        invDistanceFromC.removeAll(invDistanceFromC);

    }

    /**
     * Checks if Player C has insufficient money.
     * @return True if Player C has insufficient money, false otherwise.
     */
    public boolean isMoneyInsufficient() {
        System.out.println("C'S CURRENT MONEY: " + startMoneyC);
        return startMoneyC <= 0;
    }

    /**
     * Retrieves the file path of the inventory coin sprite based on its value.
     * @param value The value of the inventory coin.
     * @return The file path of the inventory coin sprite.
     */
    public String invPNG(int value) {

        String png = "";

        switch (value) {
            case 5:
                png = "src/goldgame/sprites/invBronze.png";
                break;
            case 10:
                png = "src/goldgame/sprites/invSilver.png";
                break;
            case 15:
                png = "src/goldgame/sprites/invGold.png";
                break;
            default:
                png = "src/goldgame/sprites/invDiamond.png";
                break;
        }
        return png;

    }

}
