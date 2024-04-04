import ch.aplu.util.SoundPlayerExt;
import java.awt.AWTException;
import java.util.Random;

/**
 * The GameClass is used to provide parameters for constructing the grid in PlayGame.java.
 */
public class GameClass {

    public static int GCOLS;
    public static int GROWS;
    public static int GSTEPNUM;
    public static int GPERCENTCOIN;
    public static int GINITPLAYERMONEY;

    /**
     * Retrieves parameters and constructs the grid in PlayGame.java.
     *
     * @param C the number of columns in the grid
     * @param R the number of rows in the grid
     * @param SN the number of steps each player will take
     * @param perM the percentage of cells with coins
     * @param playM the initial money for players
     * @throws InterruptedException if interrupted while waiting
     * @throws AWTException if an AWT exception occurs
     */
    public static void getParam(int C, int R, int SN, int perM, int playM) throws InterruptedException, AWTException {
        GCOLS = C;
        GROWS = R;
        GSTEPNUM = SN;
        GPERCENTCOIN = perM;
        GINITPLAYERMONEY = playM;

        System.out.println("GAME CONFIGURATION: ");
        System.out.println("COLUMN NUMBER: " + GCOLS);
        System.out.println("ROW NUMBER: " + GROWS);
        System.out.println("HOW MANY STEPS WILL PLAYER TAKE?: " + GSTEPNUM);
        System.out.println("PERCENT OF MONEY: " + GPERCENTCOIN);
        System.out.println("INITIAL PLAYER MONEY: " + GINITPLAYERMONEY);

        makeGrid();
    }

    /**
     * Creates the game grid.
     *
     * @throws InterruptedException if interrupted while waiting
     * @throws AWTException if an AWT exception occurs
     */
    public static void makeGrid() throws InterruptedException, AWTException {
        PlayGame pg = new PlayGame(GCOLS, GROWS);
        SoundPlayerExt sp = new SoundPlayerExt("src/goldgame/sprites/gamemusic.wav");
        sp.setVolume(500);
        sp.playLoop();
    }

    /**
     * Retrieves a random value for coin's worth.
     *
     * @return a random value for coin's worth
     */
    public static int getMoneyValue() {
        int[] values = {5, 10, 15, 20};
        return values[new Random().nextInt(values.length)];
    }

    /**
     * Retrieves the file path for a coin image based on its value.
     *
     * @param imageLoc the value of the coin
     * @return the file path for the coin's image
     */
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
