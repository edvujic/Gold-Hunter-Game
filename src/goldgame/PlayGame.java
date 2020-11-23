// CODED BY: HİLAL ÖZKAN AND EDMOND VUJIĆI AT KOCAELI UNIVERSITY
package goldgame;

// JGameGrid ile başka kullanılan kütüphaneler eklenir.
// Import JGameGrid and other needed libraries.
import ch.aplu.jgamegrid.*;
import ch.aplu.util.SoundPlayerExt;
import java.awt.AWTException;
import java.awt.Color;
import java.util.ArrayList;

// Getting the values from the GameClass for the gameplay and size of grid.
// GameClass'tan alınan değişkenler oyunun mekaniği ve ızgara büyüklüğü için kullanılır.
import static goldgame.GameClass.getMoneyValue;
import static goldgame.GameClass.getPNG;
import static goldgame.GameClass.GCOLS;
import static goldgame.GameClass.GROWS;
import static goldgame.GameClass.GPERCENTCOIN;

// PlayGame class extends GameGrid class in order to form a grid.
// PlayGame sınıfı GameGrid'den katılım alır ve ızgaraya oluşturur.
public class PlayGame extends GameGrid {

    // Creating the object for the players ("actors" in terms of JGameGrid).
    // Oyuncu nesneleri oluşturulur. (JGameGrid'de "aktörler" olarak geçer).
    public static PlayerA mario = new PlayerA();
    public static PlayerB green = new PlayerB();
    public static PlayerC sonic = new PlayerC();
    public static PlayerD ghost = new PlayerD();

    // vCoins stands for the number of visible coins to be added in the grid. 
    // vCoins görünür altınlar sayısı anlamına gelir, daha sonra ızgaraya eklenecek.
    public static int vCoins = (int) Math.round(GPERCENTCOIN * 0.9);
    // invCoins stands for the number of invisible coins to be added in the grid.
    // invCoins görünmez altınlar sayısı anlamına gelir, daha sonra ızgaraya eklenecek.
    public static int invCoins = (int) Math.ceil(GPERCENTCOIN * 0.1);

    // Creating the ArrayList for the visible and invisible coins.
    // Görünür ve görünmez altınlar için ArrayList oluşturulur.
    public static ArrayList<Coin> vMoney = new ArrayList<>();
    public static ArrayList<Coin> invMoney = new ArrayList<>();

    public PlayGame(int cols, int rows) throws AWTException {
        // Called super() with row and column number for grid.
        // Izgrayı oluşturmak için satır ve sütun sayısıyla super() fonk. çağrılır.
        super(cols, rows, 30, Color.DARK_GRAY);

        // Adding the title.
        // Başlık eklenir.
        this.setTitle("IMPLEMENTED BY: HILAL OZKAN AND EDMOND VUJICI");

        // Used to make the grid visible.
        // Izgarayı görünür yapmak için kullanılır.
        show();

        // The 1st player (Mario) is added at location (0,0) in grid.
        // 1. oyuncu (Mario), (0,0) konumuna ızgaraya eklenir.
        this.addActor(mario, new Location(GROWS - GROWS, GCOLS - GCOLS));
        mario.setActEnabled(false);
        mario.setSlowDown(4);

        // The 2nd player (Green from Among Us) is added at location (column size, 0)
        // 2. oyuncu (Among Us oyunundan Yeşil), (sütun sayısı, 0) konumuna eklenir.
        this.addActor(green, new Location(GCOLS - 1, 0));
        green.setActEnabled(false);
        green.setSlowDown(4);

        // The 3rd player (Sonic) is added at location (0, row size)
        // 3. oyuncu (Sonic), (0, satır sayısı) konumuna eklenir.
        this.addActor(sonic, new Location(0, GROWS - 1));
        sonic.setActEnabled(false);
        sonic.setSlowDown(4);

        // The 4th player (Pink Ghost from Pacman) is added at location (column size, row size)
        // 4 oyuncu (Pacman'den Pembe Hayalet), (sütun sayısı, satır sayısı) konumuna eklenir.
        this.addActor(ghost, new Location(GCOLS - 1, GROWS - 1));
        ghost.setActEnabled(false);
        ghost.setSlowDown(4);

        // Adding the visible coins to the visible coin ArrayList. (Together with random values from function getMoneyValue())
        // Görünür altınlar, görünür altınlar ArrayList'ine eklenir. (getMoneyValue() fonksiyonundan alınan rastgele değerleri ile beraber)
        for (int i = 0; i < vCoins; i++) {
            int value = getMoneyValue();
            vMoney.add(i, new Coin(getPNG(value), value));
        }

        // Adding the visible coins to the grid using the method addActor().
        // addActor() metodu kullanılarak görünür altınlar ızgaraya eklenir.
        for (int i = 0; i < vMoney.size(); i++) {
            this.addActor(vMoney.get(i), this.getRandomEmptyLocation());
        }

        // Adding the invisible coins to the visible coin ArrayList. (Together with random values from function getMoneyValue())
        // Görünmez altınlar, görünmez altınlar ArrayList'ine eklenir. (getMoneyValue() fonksiyonundan alınan rastgele değerleri ile beraber)
        for (int i = 0; i < invCoins; i++) {
            int value = getMoneyValue();
            invMoney.add(i, new Coin(getPNG(value), value));
        }

        // Adding the invisible coins to the grid using the method addActor().
        // addActor() metodu kullanılarak görünmez altınlar ızgaraya eklenir.
        for (int i = 0; i < invMoney.size(); i++) {
            // Hiding done here.
            // Gizlenmesi burada yapılır.
            invMoney.get(i).hide();
            this.addActor(invMoney.get(i), this.getRandomEmptyLocation());
        }

        // Starting the game with Mario going first.
        // Oyuna Mario ile başlanır.
        System.out.println("------------------------------------GAME STARTS-----------------------------------");
        mario.getCoinCoordinate(true);
    }
}
