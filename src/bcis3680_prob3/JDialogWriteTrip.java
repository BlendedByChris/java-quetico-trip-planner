package bcis3680_prob3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author cleblanc
 */
public class JDialogWriteTrip extends javax.swing.JDialog {

    private File tripFile = null;

    /** Creates new form JDialogWriteTrip */
    public JDialogWriteTrip()
    {
        initComponents();
    }

    private void newTripInformationFile(File filename)
    {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
            out.println("Test:\tchicken.");
            out.close();
            dispose();
        } catch (IOException ex) {
            Logger.getLogger(JDialogWriteTrip.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcWriteTrip = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        fcWriteTrip.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fcWriteTrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fcWriteTripActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fcWriteTrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fcWriteTrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fcWriteTripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fcWriteTripActionPerformed
        if (evt.getActionCommand().equals("CancelSelection")) {
            System.out.println("Cancel buttion selected");
            dispose();
        }

        if (evt.getActionCommand().equals("ApproveSelection")) {
            newTripInformationFile(fcWriteTrip.getSelectedFile());
        }
    }//GEN-LAST:event_fcWriteTripActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JDialogWriteTrip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fcWriteTrip;
    // End of variables declaration//GEN-END:variables

}
