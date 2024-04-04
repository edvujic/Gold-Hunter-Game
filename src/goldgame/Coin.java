/**
 * Represents a coin in the game.
 */
public class Coin extends Actor {

    /** The value of the coin. */
    int value;

    /** The name of the coin. */
    String name;

    /**
     * Constructs a new coin with the given image file path and value.
     *
     * @param filepath the file path of the coin's image
     * @param value1 the value of the coin
     */
    public Coin(String filepath, int value1) {
        super(filepath);
        this.value = value1;
        this.name = filepath.replace("src/goldgame/sprites/", "").replace(".png", "");
    }

}
