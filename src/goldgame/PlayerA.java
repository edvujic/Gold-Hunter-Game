package goldgame;

// Importing classes -- başka sınıfların ithali.
import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;
import ch.aplu.util.SoundPlayerExt;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.Color;

// Importing static variables from other classes.
// Başka sınıflardan sabit değişkenlerin ithali
import static goldgame.GameClass.getPNG;
import static goldgame.InputScreen.PLAYERMONEY;
import static goldgame.InputScreen.STEPNUM;
import static goldgame.PlayGame.ghost;
import static goldgame.PlayGame.green;
import static goldgame.PlayGame.invMoney;
import static goldgame.PlayGame.mario;
import static goldgame.PlayGame.sonic;
import static goldgame.PlayGame.vMoney;


public class PlayerA extends Actor {

    // Player's initial money taken from pre-game frame -- Oyunun başında alınan para miktarı.
    public static int startMoneyA = PLAYERMONEY;
    public static int usedMoneyA = 0; // Player's used money for goal and steps taken -- Oyuncunun hedef belirleme ve adım atmada kullanılan para.
    public static int stepsTakenA = 0; // Steps taken by player in each round -- Her turda oyuncu tarafında atılan adım sayısı.
    public static int collectedMoneyA = 0; // Money collected by player -- Oyuncunun topladığı para.
    public static int costForGoalA = 0; // Used money for goal calcuation -- Hedef belirlemede kullanılan para.

    // Info about targeted coin -- Hedeflenen para için bilgiler.
    int xTargetCoinA;
    int yTargetCoinA;
    int coinIndexA;

    int xTempTargetA;
    int yTempTargetA;
    
    // Control if player's out-of-money image is added to grid.
    // Oyuncunun para bittiğinde ızgaraya etksiz fotoğrafı eklendi mi diye kontrolü.
    boolean lostAddedToGrid = false; 
    public static int stepCountA = 0; // Total taken steps -- Toplam atılan adım sayısı.
    public static ArrayList<Location> stepCoordinatesA = new ArrayList<>(); // Location tracker - Konum takibi.

    public PlayerA() {
        // Setting player's image -- Oyuncu görseli konma.
        super("src/goldgame/sprites/mario.png");
        // Adding ınıtal player location -- Başlangıç konumu ekleme.
        stepCoordinatesA.add(new Location(0, 0));

    }
    @Override
    public void act() {

        boolean check = isMoneyInsufficient();
        this.isGameOver();

        if (check == true) {
            System.out.println("MARIO ELENDI!");
            this.setActEnabled(false);
            this.hide();
            this.stepsTakenA = 0;
            if (lostAddedToGrid == false) {
                Actor lostMario = new Actor("src/goldgame/sprites/lostMario.png");
                gameGrid.addActor(lostMario, this.getLocation());
                lostAddedToGrid = true;
            }
            green.stepsTakenB = 0;
            green.getCoinCoordinate(true);
        } else {
            gameGrid.setGridColor(Color.RED);
            // Movement in the X-axis -- X-eksenine göre hareket.
            if (this.getX() < xTargetCoinA || this.getX() > xTargetCoinA) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinA, this.getY())));
                this.move();
                stepCoordinatesA.add(this.getLocation());
                stepsTakenA++;
                stepCountA++;
            // Movement in the Y-axis -- Y-eksenine göre hareket.
            } else if (this.getX() == xTargetCoinA) {
                setDirection(getLocation().get4CompassDirectionTo(new Location(xTargetCoinA, yTargetCoinA)));
                this.move();
                stepCoordinatesA.add(this.getLocation());
                stepsTakenA++;
                stepCountA++;
            }
            // Checks if player is at the same position with targeted coin.
            // Oyuncu, hedeflediği altınla aynı konuma mı geldi diye kotrolü.
            if (xTargetCoinA == this.getX() && yTargetCoinA == this.getY()) {
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
                // If player took the coin then next player's turn.
                // Oyuncu hedefini aldıysa başka oyuncuya sıra verme.
                green.getCoinCoordinate(true);

                startMoneyA -= 5;
                usedMoneyA += 5;

            }
            if (stepsTakenA == STEPNUM) {
                this.setActEnabled(false);
                stepsTakenA = 0;
                green.stepsTakenB = 0;
                this.setSlowDown(4);
                mario.isGameOver();
                green.getCoinCoordinate(true);
                startMoneyA -= 5;
                usedMoneyA += 5;
            }
            // Checks if player collided with a hidden coin.
            // Oyuncu gizli altınla çarpıştı mı diye kontrol eder.
            collideHidden(this.getLocation(), "TURN: MARIO (A)");
        }

    }

    public void getCoinCoordinate(boolean isThereGoal) {
        xTempTargetA = this.xTargetCoinA;
        yTempTargetA = this.yTargetCoinA;
        mario.isGameOver();
        
        ArrayList<Integer> distanceFromMario = new ArrayList<>();
        for (Coin itr : vMoney) {
            // Gets distance to every coin from player's current location for ever roud.
            // Her tur için oyuncunun mevcut konumundan her altına olan uzaklığı hesaplar ve listeye atar.
            int distance = abs(itr.getX() - this.getX()) + abs(itr.getY() - this.getY());
            distanceFromMario.add(distance);
        }
        // Finding the closest coin.
        // En yakın altın belirleme.
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
        if (check == false) {
            // Checks if player goes to the same coin that they targeted the previous round to avoid unnecessary money usage.
            // Gereksiz altın kullanımı önlemek için daha önce gidilen altına tekrar gidilmeye çalışıyorsa kontrolü
            if (this.xTargetCoinA == xTempTargetA && this.yTargetCoinA == yTempTargetA) {
                System.out.println("AYNI HEDEFE GİDİLİYOR!");
            } else {
                System.out.println("BAŞKA HEDEFE GİDİLİYOR!");
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

    public boolean isMoneyInsufficient() {
        System.out.println("A'NIN MEVCUT PARASI: " + startMoneyA);
        boolean isIns = false;
        if (startMoneyA <= 0) {
            isIns = true;
            startMoneyA = 0;
        }
        return isIns;
    }

    public void isGameOver() {

        if (vMoney.isEmpty() || (!mario.isVisible() && !green.isVisible() && !sonic.isVisible() && !ghost.isVisible())) {

            //gameGrid.removeAllActors();
            mario.setActEnabled(false);
            green.setActEnabled(false);
            sonic.setActEnabled(false);
            ghost.setActEnabled(false);
            System.out.println("A'nın adımları: ");
            for (Location lc : mario.stepCoordinatesA) {
                System.out.print(lc + "-");
            }
            System.out.println("\nA'nın attığı toplam adım sayısı: " + mario.stepCountA);
            System.out.println("\nA'nın hamle için harcadığı para: " + mario.usedMoneyA);
            System.out.println("\nA'nın kalan parası: " + mario.startMoneyA);
            System.out.println("\nA'nın topladığı para: " + mario.collectedMoneyA);
            System.out.println("\nA'nın hedef belirlemede kullandığı para: " + mario.costForGoalA);
            System.out.println("\n");
            System.out.println("B'nin adımları: ");
            for (Location lc : green.stepCoordinatesB) {
                System.out.print(lc + "-");
            }
            System.out.println("\nB'nın attığı toplam adım sayısı: " + green.stepCountB);
            System.out.println("\nB'nın hamle için harcadığı para: " + green.usedMoneyB);
            System.out.println("\nB'nın kalan parası: " + green.startMoneyB);
            System.out.println("\nB'nın topladığı para: " + green.collectedMoneyB);
            System.out.println("\nB'nın hedef belirlemede kullandığı para: " + green.costForGoalB);
            System.out.println("\n");
            System.out.println("C'nin adımları: ");
            for (Location lc : sonic.stepCoordinatesC) {
                System.out.print(lc + "-");
            }
            System.out.println("\nC'nın attığı toplam adım sayısı: " + sonic.stepCountC);
            System.out.println("\nC'nın hamle için harcadığı para: " + sonic.usedMoneyC);
            System.out.println("\nC'nın kalan parası: " + sonic.startMoneyC);
            System.out.println("\nC'nın topladığı para: " + sonic.collectedMoneyC);
            System.out.println("\nC'nın hedef belirlemede kullandığı para: " + sonic.costForGoalC);
            System.out.println("\n");
            System.out.println("D'nin adımları: ");
            for (Location lc : ghost.stepCoordinatesD) {
                System.out.print(lc + "-");
            }
            System.out.println("\nD'nın attığı toplam adım sayısı: " + ghost.stepCountD);
            System.out.println("\nD'nın hamle için harcadığı para: " + ghost.usedMoneyD);
            System.out.println("\nD'nın kalan parası: " + ghost.startMoneyD);
            System.out.println("\nD'nın topladığı para: " + ghost.collectedMoneyD);
            System.out.println("\nD'nın hedef belirlemede kullandığı para: " + ghost.costForGoalD);
            System.out.println("\n");
            System.out.println("\n");

            //  SoundPlayerExt pl = new SoundPlayerExt("src/goldgame/sprites/round_end.wav");
            //  pl.setVolume(600);
            //  pl.play();
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
