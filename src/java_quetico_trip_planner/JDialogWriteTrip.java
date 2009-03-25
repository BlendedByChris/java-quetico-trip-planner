package java_quetico_trip_planner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JDialog Write Trip
 *
 * Shows a file chooser dialog for the user to select where they would like
 * to store the trip information summary. This file by default is stored in
 * C:\LeaderName_TripDate.xls. It is a tab delimited file.
 *
 * @author cleblanc
 */
public class JDialogWriteTrip extends javax.swing.JDialog {

    private TripInformation tripInformation = TripInformation.getInstance();

    private String [] canoeHeader = tripInformation.canoeHeader;
    private String [][] canoes = tripInformation.canoes;

    /** Creates new form JDialogWriteTrip */
    public JDialogWriteTrip()
    {
        initComponents();
        setCustomFrameProperties();
    }

    /**
     * Set Custom Frame Properties
     *
     * Set any custom frame properties not specified by NetBeans.
     */
    private void setCustomFrameProperties()
    {
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    /**
     * Write File
     *
     * Write a new file to a specified location with detauls from the
     * TripInformation singleton class.
     *
     * @param filename
     */
    private void writeFile(File filename)
    {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));

            // Write trip information to the file
            out.print("Group Leader:\t");
            out.println(tripInformation.groupLeader);

            out.print("Total Guests:\t");
            out.println(tripInformation.totalGuests);

            out.print("Adults:\t");
            out.println(tripInformation.adults);

            out.print("Children:\t");
            out.println(tripInformation.children);

            out.print("Start Date:\t");
            out.println(tripInformation.startDate);

            out.print("End Date:\t");
            out.println(tripInformation.endDate);

            out.print("Tow:\t");
            out.println(tripInformation.tow);

            out.print("Canoe Rental:\t");
            out.println(tripInformation.canoeRental);

            out.print("Payment Type:\t");
            out.println(tripInformation.paymentType);

            out.print("Total Duration:\t");
            out.println(tripInformation.tripDuration);

            out.print("Total Camping Fees:\t");
            out.println(tripInformation.totalCampingFees);

            // Write canoes to the file
            out.println("\nCanoes:");
            // Write header
            for (int i=0; i < canoeHeader.length; i++) {
                out.print(canoeHeader[i]+"\t");
            }
            out.print("\n");

            // Write rows
            String canoe = "";
            for (int i=0; i < 10; i++) {
                for (int j=0; j < 5; j++) {                    
                    if (canoes[i][j] != null) canoe = canoes[i][j];
                    else canoe = "";
                    out.print(canoe+"\t");
                }
                out.print("\n");
            }

            // Closes the file
            out.close();
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

        fcWriteTrip.setDialogTitle("Write Trip Information");
        fcWriteTrip.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        fcWriteTrip.setFileFilter(new FilterJDialogWriteTrip());
        fcWriteTrip.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        fcWriteTrip.setSelectedFile(new File("C:\\" + tripInformation.groupLeader + "_" + tripInformation.startDate.replace("/", "-") + ".xls"));
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
            dispose();
        }

        if (evt.getActionCommand().equals("ApproveSelection")) {
            writeFile(fcWriteTrip.getSelectedFile());
            dispose();
        }
    }//GEN-LAST:event_fcWriteTripActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fcWriteTrip;
    // End of variables declaration//GEN-END:variables

}
