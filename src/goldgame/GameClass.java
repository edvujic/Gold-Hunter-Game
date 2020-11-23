package goldgame;

import ch.aplu.util.SoundPlayerExt;
import java.awt.AWTException;
import java.util.Random;

// GameClass is used to get the parameters to construct the grid in PlayGame.java.
// GameClass sınıfı parametleri alır ve PlayGame.java sınıfında ızgarayı oluşturur.
public class GameClass {

    public static int GCOLS;
    public static int GROWS;
    public static int GSTEPNUM;
    public static int GPERCENTCOIN;
    public static int GINITPLAYERMONEY;

    public static void getParam(int C, int R, int SN, int perM, int playM) throws InterruptedException, AWTException {
        GCOLS = C; // Column size -- Sütun sayısı.
        GROWS = R; // Row size -- Satır sayısı.
        GSTEPNUM = SN; // Number of steps taken by each playr -- Her oyuncunun atılacağı adım sayısı.
        GPERCENTCOIN = perM; // Percentage of cells with coin -- Izgrananın hücrelerinde altın sıklığı.
        GINITPLAYERMONEY = playM; // Initial player money -- Oyuncuların başlangıç parası.

        System.out.println("GAME CONFIGURATION: ");
        System.out.println("COLUMN NUMBER: " + GCOLS);
        System.out.println("ROW NUMBER: " + GROWS);
        System.out.println("HOW MANY STEPS WILL PLAYER TAKE?: " + GSTEPNUM);
        System.out.println("PERCENT OF MONEY: " + GPERCENTCOIN);
        System.out.println("INITIAL PLAYER MONEY: " + GINITPLAYERMONEY);

        // Creating the grid.
        // Izgara oluşturma.
        makeGrid();
    }

    public static void makeGrid() throws InterruptedException, AWTException {
        PlayGame pg = new PlayGame(GCOLS, GROWS);
        SoundPlayerExt sp = new SoundPlayerExt("src/goldgame/sprites/gamemusic.wav");
        sp.setVolume(500);
        sp.playLoop();
    }

    // getMoneyValue() function gets random element from values array to assign to coin values.
    // Altınların değerlerini tanımlamak için values dizinden rastgele bir eleman seçilir ve altının değeri olarak atanır.
    public static int getMoneyValue() {
        int[] values = {5, 10, 15, 20};
        return values[new Random().nextInt(values.length)];
    }

    // getPNG() funtion is used to get the path to *.png files according to given random value.
    // getPNG() fonksiyonu, rastgele verilen altın değerine göre *.png dosyalarına dizini bulur.
    public static String getPNG(int imageLoc) {
        String imagePath = null;
        switch (imageLoc) {
            case 5:
                imagePath = "src/goldgame/sprites/bronze.png";
                break;
            case 10:
                imagePath = "src/goldgame/sprites/silver.png";
                break;
            case 15:
                imagePath = "src/goldgame/sprites/golden.png";
                break;
            default:
                imagePath = "src/goldgame/sprites/diamond.png";
                break;
        }
        return imagePath;
    }
}
