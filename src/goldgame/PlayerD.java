package goldgame;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;
import ch.aplu.util.SoundPlayerExt;
import static goldgame.GameClass.GCOLS;
import static goldgame.GameClass.GROWS;
import java.util.ArrayList;
import static goldgame.InputScreen.PLAYERMONEY;
import static goldgame.InputScreen.STEPNUM;
import static goldgame.PlayGame.green;
import static goldgame.PlayGame.mario;
import static goldgame.PlayGame.sonic;
import static goldgame.PlayGame.vMoney;
import java.awt.Color;
import static java.lang.Math.abs;

public class PlayerD extends Actor {

    public static int startMoneyD = PLAYERMONEY;
    public static int usedMoneyD = 0;
    public static int stepsTakenD = 0;
    public static int collectedMoneyD = 0;
    public static int costForGoalD = 0;

    int xTargetCoinD;
    int yTargetCoinD;
    int coinIndexD;

    int xTempTargetD;
    int yTempTargetD;
    boolean lostAddedToGrid = false;
    public static int stepCountD = 0;

    int moveMoneyD = 5;
    int goalReachedD = 20;

    public static ArrayList<Location> stepCoordinatesD = new ArrayList<>();

    public PlayerD() {
        super("src/goldgame/sprites/ghost.png");
        stepCoordinatesD.add(new Location(GCOLS - 1, GROWS - 1));
    }

    @Override
    public void act() {
        boolean check = isMoneyInsufficient();
        mario.isGameOver();
        if (check) {
            System.out.println("GHOST ELIMINATED!");
            this.setActEnabled(false);
            this.hide();
            this.stepsTakenD = 0;
            if (!lostAddedToGrid) {
                Actor lostGhost = new Actor("src/goldgame/sprites/lostGhost.png");
                gameGrid.addActor(lostGhost, this.getLocation());
                lostAddedToGrid = true;
            }

            mario.stepsTakenA = 0;
            mario.getCoinCoordinate(true);
        } else {
            gameGrid.setGridColor(Color.MAGENTA);
            if (this.getX() < xTargetCoinD || this.getX() > xTargetCoinD) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinD, this.getY())));
                this.move();
                stepCoordinatesD.add(this.getLocation());
                stepsTakenD++;
                stepCountD++;

            } else if (this.getX() == xTargetCoinD) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinD, yTargetCoinD)));
                this.move();
                stepCoordinatesD.add(this.getLocation());
                stepsTakenD++;
                stepCountD++;

            }
            if (xTargetCoinD == this.getX() && yTargetCoinD == this.getY()) {
                this.setActEnabled(false);

                startMoneyD += vMoney.get(coinIndexD).value;
                collectedMoneyD += vMoney.get(coinIndexD).value;

                vMoney.get(coinIndexD).removeSelf();
                vMoney.remove(coinIndexD);

                mario.stepsTakenA = 0;
                stepsTakenD = 0;
                this.setSlowDown(4);
                System.out.println("PINK GHOST TOOK A COIN");
                System.out.println("NO COINS LEFT? " + vMoney.isEmpty());
                this.getCoinCoordinate(false);
                mario.isGameOver();
                mario.getCoinCoordinate(true);
                startMoneyD -= moveMoneyD;
                usedMoneyD += moveMoneyD;

            }
            if (stepsTakenD == STEPNUM) {
                this.setActEnabled(false);
                stepsTakenD = 0;
                mario.stepsTakenA = 0;
                mario.getCoinCoordinate(true);
                this.setSlowDown(4);
                mario.isGameOver();
                startMoneyD -= moveMoneyD;
                usedMoneyD += moveMoneyD;

            }

            mario.collideHidden(this.getLocation(), "TURN: GHOST (D)");
        }

    }

    public void getCoinCoordinate(boolean isThereGoal) {

        xTempTargetD = this.xTargetCoinD;
        yTempTargetD = this.yTargetCoinD;

        mario.isGameOver();

        int distGoalA;
        int distGoalB;
        int distGoalC;

        int distGoalDfromA;
        int distGoalDfromB;
        int distGoalDfromC;

        mario.getCoinCoordinate(false);
        green.getCoinCoordinate(false);
        sonic.getCoinCoordinate(false);

        distGoalA = abs(mario.getX() - mario.xTargetCoinA)
                + abs(mario.getY() - mario.yTargetCoinA);

        distGoalB = abs(green.getX() - green.xTargetCoinB)
                + abs(green.getY() - green.yTargetCoinB);

        distGoalC = abs(sonic.getX() - sonic.xTargetCoinC)
                + abs(sonic.getY() - sonic.yTargetCoinC);

        distGoalDfromA = abs(this.getX() - mario.xTargetCoinA)
                + abs(this.getY() - mario.yTargetCoinA);

        distGoalDfromB = abs(this.getX() - green.xTargetCoinB)
                + abs(this.getY() - green.yTargetCoinB);

        distGoalDfromC = abs(this.getX() - sonic.xTargetCoinC)
                + abs(this.getY() - sonic.yTargetCoinC);

        boolean isFasterA = false;
        boolean isFasterB = false;
        boolean isFasterC = false;

        if (distGoalA < distGoalDfromA) {
            isFasterA = true;
        }

        if (distGoalB < distGoalDfromB) {
            isFasterB = true;
        }

        if (distGoalC < distGoalDfromC) {
            isFasterC = true;
        }

        ArrayList<Integer> distanceFromGhost = new ArrayList<>();
        ArrayList<Double> profitPlayerD = new ArrayList<>();
        ArrayList<Coin> bestProfitD = new ArrayList<>();

        for (Coin itr : vMoney) {
            int distance = abs(itr.getX() - this.getX()) + abs(itr.getY() - this.getY());
            distanceFromGhost.add(distance);
            profitPlayerD.add((double) itr.value / (double) distance);
        }

        if (isFasterA) {
            profitPlayerD.set(mario.coinIndexA, Double.NEGATIVE_INFINITY);
        }

        if (isFasterB) {
            profitPlayerD.set(green.coinIndexB, Double.NEGATIVE_INFINITY);
        }

        if (isFasterC) {
            profitPlayerD.set(sonic.coinIndexC, Double.NEGATIVE_INFINITY);
        }

        double max = profitPlayerD.get(0);
        int maxIndex = 0;

        for (int i = 0; i < profitPlayerD.size(); i++) {
            if (profitPlayerD.get(i) > max) {
                max = profitPlayerD.get(i);
                maxIndex = i;
            }
        }

        this.coinIndexD = maxIndex;
        this.xTargetCoinD = vMoney.get(maxIndex).getX();
        this.yTargetCoinD = vMoney.get(maxIndex).getY();

        if (isThereGoal) {
            this.setActEnabled(true);
        }

        boolean check = this.isMoneyInsufficient();
        if (check == false) {
            if (this.xTargetCoinD == xTempTargetD && this.yTargetCoinD == yTempTargetD) {
                System.out.println("SAME TARGET!");

            } else {
                System.out.println("NEW TARGET!");
                startMoneyD -= goalReachedD;
                usedMoneyD += goalReachedD;
                costForGoalD += goalReachedD;
                xTempTargetD = this.xTargetCoinD;
                yTempTargetD = this.yTargetCoinD;
            }
        } else {
            System.out.println("PLAYER D IS ELIMINATED!");
        }

        System.out.println("GHOST HEADING FOR COIN AT X: " + xTargetCoinD + " Y: " + yTargetCoinD);
        System.out.println("\n\n");
    }

    public boolean isMoneyInsufficient() {
        System.out.println("D'S CURRENT MONEY: " + startMoneyD);
        boolean isIns = false;
        if (startMoneyD <= 0) {
            isIns = true;
            startMoneyD = 0;
        }
        return isIns;
    }
}
