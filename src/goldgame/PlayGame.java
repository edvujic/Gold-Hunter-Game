package goldgame;

import ch.aplu.jgamegrid.*;
import ch.aplu.util.SoundPlayerExt;
import java.awt.AWTException;
import java.awt.Color;
import java.util.ArrayList;

import static goldgame.GameClass.getMoneyValue;
import static goldgame.GameClass.getPNG;
import static goldgame.GameClass.GCOLS;
import static goldgame.GameClass.GROWS;
import static goldgame.GameClass.GPERCENTCOIN;

/**
 * The PlayGame class represents the game grid where the players and coins are
 * placed and the gameplay takes place.
 *
 * <p>
 * This class extends the GameGrid class and initializes the game environment,
 * including players and coins.
 * </p>
 *
 * <p>
 * It sets up the game grid, adds players, visible and invisible coins, and
 * starts the game with the first player.
 * </p>
 *
 * <p>
 * It also handles the initialization of players and coins, as well as their
 * movements and interactions during the game.
 * </p>
 *
 * <p>
 * Coded by Hilal Ozkan and Edmond Vujici at Kocaeli University.
 * </p>
 */
public class PlayGame extends GameGrid {

    /**
     * The first player, Mario.
     */
    public static PlayerA mario = new PlayerA();

    /**
     * The second player, Green.
     */
    public static PlayerB green = new PlayerB();

    /**
     * The third player, Sonic.
     */
    public static PlayerC sonic = new PlayerC();

    /**
     * The fourth player, Ghost.
     */
    public static PlayerD ghost = new PlayerD();

    /**
     * The number of visible coins to be added to the grid.
     */
    public static int vCoins = (int) Math.round(GPERCENTCOIN * 0.9);

    /**
     * The number of invisible coins to be added to the grid.
     */
    public static int invCoins = (int) Math.ceil(GPERCENTCOIN * 0.1);

    /**
     * ArrayList to store visible coins.
     */
    public static ArrayList<Coin> vMoney = new ArrayList<>();

    /**
     * ArrayList to store invisible coins.
     */
    public static ArrayList<Coin> invMoney = new ArrayList<>();

    /**
     * Constructs a new PlayGame instance with the specified number of columns and
     * rows.
     *
     * @param cols the number of columns in the game grid
     * @param rows the number of rows in the game grid
     * @throws AWTException
     */
    public PlayGame(int cols, int rows) throws AWTException {
        super(cols, rows, 30, Color.DARK_GRAY);
        this.setTitle("IMPLEMENTED BY: HILAL OZKAN AND EDMOND VUJICI");
        show();

        this.addActor(mario, new Location(GROWS - GROWS, GCOLS - GCOLS));
        mario.setActEnabled(false);
        mario.setSlowDown(4);

        this.addActor(green, new Location(GCOLS - 1, 0));
        green.setActEnabled(false);
        green.setSlowDown(4);

        this.addActor(sonic, new Location(0, GROWS - 1));
        sonic.setActEnabled(false);
        sonic.setSlowDown(4);

        this.addActor(ghost, new Location(GCOLS - 1, GROWS - 1));
        ghost.setActEnabled(false);
        ghost.setSlowDown(4);

        for (int i = 0; i < vCoins; i++) {
            int value = getMoneyValue();
            vMoney.add(i, new Coin(getPNG(value), value));
        }

        for (int i = 0; i < vMoney.size(); i++) {
            this.addActor(vMoney.get(i), this.getRandomEmptyLocation());
        }

        for (int i = 0; i < invCoins; i++) {
            int value = getMoneyValue();
            invMoney.add(i, new Coin(getPNG(value), value));
        }

        for (int i = 0; i < invMoney.size(); i++) {
            invMoney.get(i).hide();
            this.addActor(invMoney.get(i), this.getRandomEmptyLocation());
        }

        System.out.println("------------------------------------GAME STARTS-----------------------------------");
        mario.getCoinCoordinate(true);
    }
}
