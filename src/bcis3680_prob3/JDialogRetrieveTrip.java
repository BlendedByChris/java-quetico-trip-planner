/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDialogWriteTrip.java
 *
 * Created on Mar 18, 2009, 11:13:59 PM
 */

package bcis3680_prob3;

/**
 *
 * @author cleblanc
 */
public class JDialogRetrieveTrip extends javax.swing.JDialog {

    /** Creates new form JDialogWriteTrip */
    public JDialogRetrieveTrip()
    {
        initComponents();
    }

    /**
     * Set Trip Files
     *
     * Write the trip information file.
     */
    private void setTripInformationFile()
    {
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcRetrieveTrip = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        fcRetrieveTrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fcRetrieveTripActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fcRetrieveTrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fcRetrieveTrip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fcRetrieveTripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fcRetrieveTripActionPerformed


        if (evt.getActionCommand().equals("CancelSelection")) {
            System.out.println("Cancel buttion selected");
            dispose();
        }

        if (evt.getActionCommand().equals("ApproveSelection")) {
            String selectedFile = fcRetrieveTrip.getSelectedFile().getName();
            System.out.println("Selected file name (" + selectedFile+ ") was saved.");
        }
}//GEN-LAST:event_fcRetrieveTripActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JDialogRetrieveTrip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fcRetrieveTrip;
    // End of variables declaration//GEN-END:variables

}
