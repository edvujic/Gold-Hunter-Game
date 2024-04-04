package goldgame;

import ch.aplu.jgamegrid.Location;
import static goldgame.PlayerA.stepCoordinatesA;
import static goldgame.PlayerB.stepCoordinatesB;
import static goldgame.PlayerC.stepCoordinatesC;
import static goldgame.PlayerD.stepCoordinatesD;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    public static void writeToOutput() {
        File output = new File("output.txt");
        try {
            if (output.createNewFile()) {
                System.out.println("FILE CREATED: " + output.getName());
            }
        } catch (IOException ex) {
            System.out.println("An error occurred.");
        }

        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write("\nPLAYER A: ");
            for (Location lc : stepCoordinatesA) {
                myWriter.write(String.valueOf(lc));
            }
            myWriter.write("\nPLAYER B: ");
            for (Location lc : stepCoordinatesB) {
                myWriter.write(String.valueOf(lc));
            }
            myWriter.write("\nPLAYER C: ");
            for (Location lc : stepCoordinatesC) {
                myWriter.write(String.valueOf(lc));
            }
            myWriter.write("\nPLAYER D: ");
            for (Location lc : stepCoordinatesD) {
                myWriter.write(String.valueOf(lc));
            }
            myWriter.close();
            System.out.println("SUCESSFULLY WRITTEN TO FILE!");
        } catch (IOException ex) {
            System.out.println("An error occured.");
        }
    }
}
