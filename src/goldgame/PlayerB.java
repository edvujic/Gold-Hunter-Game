package goldgame;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;
import ch.aplu.util.SoundPlayerExt;
import static goldgame.GameClass.GCOLS;
import static goldgame.InputScreen.PLAYERMONEY;
import static goldgame.InputScreen.STEPNUM;

import static goldgame.PlayGame.invMoney;
import static goldgame.PlayGame.mario;
import static goldgame.PlayGame.sonic;
import static goldgame.PlayGame.vMoney;
import java.awt.Color;
import static java.lang.Math.abs;
import java.util.ArrayList;

public class PlayerB extends Actor {

    public static int startMoneyB = PLAYERMONEY;
    public static int usedMoneyB = 0;
    public static int stepsTakenB = 0;
    public static int collectedMoneyB = 0;
    public static int costForGoalB = 0;

    int xTargetCoinB;
    int yTargetCoinB;
    int coinIndexB;

    int xTempTargetB;
    int yTempTargetB;
    boolean lostAddedToGrid = false;

    int moveMoneyB = 5;
    int goalReachedB = 10;

    public static ArrayList<Location> stepCoordinatesB = new ArrayList<>();
    public static int stepCountB = 0;

    public PlayerB() {
        super("src/goldgame/sprites/green.png");
        stepCoordinatesB.add(new Location(GCOLS - 1, 0));
    }

    @Override
    public void act() {
        boolean check = isMoneyInsufficient();
        mario.isGameOver();
        if (check == true) {
            System.out.println("GREEN ELENDI!");
            this.setActEnabled(false);
            this.hide();
            this.stepsTakenB = 0;
            if (lostAddedToGrid == false) {
                Actor lostGreen = new Actor("src/goldgame/sprites/lostGreen.png");
                gameGrid.addActor(lostGreen, this.getLocation());
                lostAddedToGrid = true;
            }
            sonic.stepsTakenC = 0;
            openInvC(true);
        } else {
            gameGrid.setGridColor(Color.GREEN);
            if (this.getX() < xTargetCoinB || this.getX() > xTargetCoinB) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinB, this.getY())));
                this.move();
                stepCoordinatesB.add(this.getLocation());
                stepsTakenB++;
                stepCountB++;

            } else if (this.getX() == xTargetCoinB) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinB, yTargetCoinB)));
                this.move();
                stepCoordinatesB.add(this.getLocation());
                stepsTakenB++;
                stepCountB++;

            }
            if (xTargetCoinB == this.getX() && yTargetCoinB == this.getY()) {
                this.setActEnabled(false);
                //SoundPlayerExt coinGot = new SoundPlayerExt("src/goldgame/sprites/coin.wav");
                //coinGot.setVolume(600);
                //coinGot.play();

                startMoneyB += vMoney.get(coinIndexB).value;
                collectedMoneyB += vMoney.get(coinIndexB).value;

                vMoney.get(coinIndexB).removeSelf();
                vMoney.remove(coinIndexB);

                sonic.stepsTakenC = 0;
                stepsTakenB = 0;
                this.setSlowDown(4);
                System.out.println("GREEN TOOK A COIN");

                System.out.println("NO COINS LEFT? " + vMoney.isEmpty());

                this.getCoinCoordinate(false);
                mario.isGameOver();
                openInvC(true);

                startMoneyB -= moveMoneyB;
                usedMoneyB += moveMoneyB;
            }
            if (stepsTakenB == STEPNUM) {
                this.setActEnabled(false);
                stepsTakenB = 0;
                sonic.stepsTakenC = 0;
                this.setSlowDown(4);
                mario.isGameOver();
                openInvC(true);

                startMoneyB -= moveMoneyB;
                usedMoneyB += moveMoneyB;
            }
            mario.collideHidden(this.getLocation(), "TURN: GREEN (B)");
        }

    }

    public void getCoinCoordinate(boolean isThereGoal) {

        xTempTargetB = this.xTargetCoinB;
        yTempTargetB = this.yTargetCoinB;

        mario.isGameOver();
        ArrayList<Integer> distanceFromGreen = new ArrayList<>();
        ArrayList<Double> profitPlayerB = new ArrayList<>();

        for (Coin itr : vMoney) {
            int distance = abs(itr.getX() - this.getX()) + abs(itr.getY() - this.getY());
            distanceFromGreen.add(distance);
            profitPlayerB.add((double) itr.value / (double) distance);
        }

        double max = profitPlayerB.get(0);
        int maxIndex = 0;

        for (int i = 0; i < profitPlayerB.size(); i++) {

            if (profitPlayerB.get(i) > max) {
                max = profitPlayerB.get(i);
                maxIndex = i;
            }
        }

        this.coinIndexB = maxIndex;
        this.xTargetCoinB = vMoney.get(maxIndex).getX();
        this.yTargetCoinB = vMoney.get(maxIndex).getY();

        if (isThereGoal) {
            this.setActEnabled(true);
        }

        boolean check = this.isMoneyInsufficient();
        if (check == false) {
            if (this.xTargetCoinB == xTempTargetB && this.yTargetCoinB == yTempTargetB) {
                System.out.println("AYNI HEDEFE GİDİLİYOR!");

            } else {
                System.out.println("BAŞKA HEDEFE GİDİLİYOR!");
                startMoneyB -= goalReachedB;
                usedMoneyB += goalReachedB;
                costForGoalB += goalReachedB;
                xTempTargetB = this.xTargetCoinB;
                yTempTargetB = this.yTargetCoinB;
            }

        } else {
            System.out.println("PLAYER B IS ELIMINATED!");
        }
        System.out.println("GREEN IS HEADING FOR X: " + this.xTargetCoinB + " Y: " + this.yTargetCoinB);
        System.out.println("\n\n");
    }

    public boolean isMoneyInsufficient() {
        System.out.println("B'NİN MEVCUT PARASI: " + startMoneyB);
        boolean isIns = false;
        if (startMoneyB <= 0) {
            isIns = true;
            startMoneyB = 0;
        }
        return isIns;

    }

    public void openInvC(boolean isThereGoal) {
        //SoundPlayerExt pl = new SoundPlayerExt("src/goldgame/sprites/open.wav");
        //pl.setVolume(600);
        if (invMoney.size() > 1) {
            //pl.play();
            sonic.openInvCoin();
            sonic.openInvCoin();
            sonic.getCoinCoordinate(isThereGoal);

        } else if (invMoney.size() == 1) {
            //pl.play();
            sonic.openInvCoin();
            sonic.getCoinCoordinate(isThereGoal);
        } else {
            sonic.getCoinCoordinate(isThereGoal);
        }

    }

}
