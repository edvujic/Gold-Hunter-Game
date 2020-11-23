package goldgame;

import ch.aplu.jgamegrid.Actor;

// Class Coin inherents class Actor because every coin is an actor.
// Coin sınıfı Actor sınıfından kalıtım alır, çünkü her altın bir aktördür.
public class Coin extends Actor {

    int value;
    String name;

    // Constructing the coin.
    // Altınları yapılandırma.
    public Coin(String filepath, int value1) {

        // super() function allows us to put a *.png file as actor's image.
        // super() fonksiyonu ile *.png dosyaları bir aktörün resmi olarak konulabilir.
        super(filepath);
        this.value = value1;
        this.name = filepath.replace("src/goldgame/sprites/", "").replace(".png", "");
    }

}
