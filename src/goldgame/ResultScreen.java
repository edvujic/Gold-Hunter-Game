package goldgame;

import static goldgame.PlayerA.*;
import static goldgame.PlayerB.*;
import static goldgame.PlayerC.*;
import static goldgame.PlayerD.*;
import javax.swing.ImageIcon;

public class ResultScreen extends javax.swing.JFrame {

    public ResultScreen() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        marioIcon = new javax.swing.JLabel();
        greenIcon = new javax.swing.JLabel();
        sonicIcon = new javax.swing.JLabel();
        ghostIcon = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        marioStep = new javax.swing.JLabel();
        greenStep = new javax.swing.JLabel();
        sonicStep = new javax.swing.JLabel();
        ghostStep = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        marioCurrent = new javax.swing.JLabel();
        greenCurrent = new javax.swing.JLabel();
        sonicCurrent = new javax.swing.JLabel();
        ghostCurrent = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        marioCollected = new javax.swing.JLabel();
        greenCollected = new javax.swing.JLabel();
        sonicCollected = new javax.swing.JLabel();
        ghostCollected = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        marioSpent = new javax.swing.JLabel();
        greenSpent = new javax.swing.JLabel();
        sonicSpent = new javax.swing.JLabel();
        ghostSpent = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RESULT");

        jButton1.setText("WRITE TO FILE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Player");

        marioIcon.setText("jLabel3");

        greenIcon.setText("jLabel4");

        sonicIcon.setText("jLabel5");

        ghostIcon.setText("jLabel6");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Step");

        marioStep.setText("jLabel8");

        greenStep.setText("jLabel9");

        sonicStep.setText("jLabel10");

        ghostStep.setText("jLabel11");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Current");

        marioCurrent.setText("jLabel13");

        greenCurrent.setText("jLabel14");

        sonicCurrent.setText("jLabel15");

        ghostCurrent.setText("jLabel16");

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Collected");

        marioCollected.setText("jLabel18");

        greenCollected.setText("jLabel19");

        sonicCollected.setText("jLabel20");

        ghostCollected.setText("jLabel21");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Spent");

        marioSpent.setText("jLabel23");

        greenSpent.setText("jLabel24");

        sonicSpent.setText("jLabel25");

        ghostSpent.setText("jLabel26");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(marioIcon)
                            .addComponent(greenIcon)
                            .addComponent(sonicIcon)
                            .addComponent(ghostIcon))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ghostStep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sonicStep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(greenStep)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(marioStep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(marioCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(greenCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sonicCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ghostCurrent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(marioCollected, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(greenCollected, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sonicCollected, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ghostCollected, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ghostSpent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sonicSpent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(greenSpent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(marioSpent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jLabel17)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marioIcon)
                    .addComponent(marioStep)
                    .addComponent(marioCurrent)
                    .addComponent(marioCollected)
                    .addComponent(marioSpent))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(greenIcon)
                    .addComponent(greenStep)
                    .addComponent(greenCurrent)
                    .addComponent(greenCollected)
                    .addComponent(greenSpent))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sonicIcon)
                    .addComponent(sonicStep)
                    .addComponent(sonicCurrent)
                    .addComponent(sonicCollected)
                    .addComponent(sonicSpent))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ghostIcon)
                    .addComponent(ghostStep)
                    .addComponent(ghostCurrent)
                    .addComponent(ghostCollected)
                    .addComponent(ghostSpent))
                .addGap(26, 26, 26)
                .addComponent(jButton1)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        WriteToFile wf = new WriteToFile();
        wf.writeToOutput();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ghostCollected;
    private javax.swing.JLabel ghostCurrent;
    private javax.swing.JLabel ghostIcon;
    private javax.swing.JLabel ghostSpent;
    private javax.swing.JLabel ghostStep;
    private javax.swing.JLabel greenCollected;
    private javax.swing.JLabel greenCurrent;
    private javax.swing.JLabel greenIcon;
    private javax.swing.JLabel greenSpent;
    private javax.swing.JLabel greenStep;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel marioCollected;
    private javax.swing.JLabel marioCurrent;
    private javax.swing.JLabel marioIcon;
    private javax.swing.JLabel marioSpent;
    private javax.swing.JLabel marioStep;
    private javax.swing.JLabel sonicCollected;
    private javax.swing.JLabel sonicCurrent;
    private javax.swing.JLabel sonicIcon;
    private javax.swing.JLabel sonicSpent;
    private javax.swing.JLabel sonicStep;
    // End of variables declaration//GEN-END:variables
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
