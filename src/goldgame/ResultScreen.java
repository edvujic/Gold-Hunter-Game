package goldgame;

import static goldgame.PlayerA.*;
import static goldgame.PlayerB.*;
import static goldgame.PlayerC.*;
import static goldgame.PlayerD.*;
import javax.swing.ImageIcon;

/**
 * The ResultScreen class represents the result screen of the game.
 *
 * <p>
 * It displays the results of the game, including the steps taken, current
 * money, collected money, and money spent by each player.
 * </p>
 *
 * <p>
 * This class also provides functionality to write the results to a file.
 * </p>
 *
 * <p>
 * Coded by Hilal Ozkan and Edmond Vujici at Kocaeli University.
 * </p>
 */
public class ResultScreen extends javax.swing.JFrame {

    /**
     * Constructs a new ResultScreen instance.
     */
    public ResultScreen() {
        initComponents();
    }

    /**
     * Initializes the components of the ResultScreen GUI.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        // Generated code omitted for brevity...
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles the action performed when the "WRITE TO FILE" button is clicked.
     *
     * @param evt the action event
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        WriteToFile wf = new WriteToFile();
        wf.writeToOutput();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Main method to launch the ResultScreen GUI.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        // Look and feel setting code omitted for brevity...
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultScreen().setVisible(true);
            }
        });
    }

    /**
     * Adds information to the ResultScreen GUI.
     */
    public void addInfo() {
        this.setTitle("RESULT");
        this.setLocationRelativeTo(null);
        marioIcon.setText("");
        marioIcon.setIcon(new ImageIcon("src/goldgame/sprites/mario.png"));

        greenIcon.setText("");
        greenIcon.setIcon(new ImageIcon("src/goldgame/sprites/green.png"));

        sonicIcon.setText("");
        sonicIcon.setIcon(new ImageIcon("src/goldgame/sprites/sonic.png"));

        ghostIcon.setText("");
        ghostIcon.setIcon(new ImageIcon("src/goldgame/sprites/ghost.png"));

        marioSpent.setText("");
        marioSpent.setText(String.valueOf(usedMoneyA));

        greenSpent.setText("");
        greenSpent.setText(String.valueOf(usedMoneyB));

        sonicSpent.setText("");
        sonicSpent.setText(String.valueOf(usedMoneyC));

        ghostSpent.setText("");
        ghostSpent.setText(String.valueOf(usedMoneyD));

        marioCollected.setText("");
        marioCollected.setText(String.valueOf(collectedMoneyA));

        greenCollected.setText("");
        greenCollected.setText(String.valueOf(collectedMoneyB));

        sonicCollected.setText("");
        sonicCollected.setText(String.valueOf(collectedMoneyC));

        ghostCollected.setText("");
        ghostCollected.setText(String.valueOf(collectedMoneyD));

        marioCurrent.setText("");
        marioCurrent.setText(String.valueOf(startMoneyA));

        greenCurrent.setText("");
        greenCurrent.setText(String.valueOf(startMoneyB));

        sonicCurrent.setText("");
        sonicCurrent.setText(String.valueOf(startMoneyC));

        ghostCurrent.setText("");
        ghostCurrent.setText(String.valueOf(startMoneyD));

        marioStep.setText("");
        marioStep.setText(String.valueOf(stepCountA));

        greenStep.setText("");
        greenStep.setText(String.valueOf(stepCountB));

        sonicStep.setText("");
        sonicStep.setText(String.valueOf(stepCountC));

        ghostStep.setText("");
        ghostStep.setText(String.valueOf(stepCountD));

    }
}
