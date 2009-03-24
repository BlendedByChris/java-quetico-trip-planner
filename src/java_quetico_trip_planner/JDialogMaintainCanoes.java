/*
 * JDialogMaintainCanoes.java
 *
 * Created on Mar 21, 2009, 9:46:06 PM
 */

package java_quetico_trip_planner;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 * JDialog Maintain Canoes
 * 
 * A dialog to maintain the canoes available for a trip
 *
 * @author cleblanc
 */
public class JDialogMaintainCanoes extends javax.swing.JDialog {

    private ResultSet resultSet = null;

    /** Creates new form JDialogMaintainCanoes */
    public JDialogMaintainCanoes() {
        initComponents();
        try {
            resultSet = new DbCanoe().getAllCanoes();
        } catch (SQLException ex) {
            Logger.getLogger(JDialogMaintainCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        setLocationRelativeTo(null);
    }

    /**
     * Update Record Information Fields
     *
     * Sets the JFrame fields to the current selected record from the resultSet
     */
    private void updateRecordInformationFields()
    {
        try {
            txtCanoe.setText(resultSet.getString("clCanoe"));
            txtManufacturer.setText(resultSet.getString("clManufacturer"));
            txtLayup.setText(resultSet.getString("clLayup"));
            txtWeight.setText(resultSet.getString("clWeight"));
            txtCapacity.setText(resultSet.getString("clCapacity"));
        } catch (SQLException ex) {
            Logger.getLogger(JDialogMaintainCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * New Record
     *
     * Sets the resultset cursor to a new row and prepares the form for a new
     * record entry
     */
    private void newRecord()
    {
        System.out.println("new record");
        try {
            resultSet.moveToInsertRow();
            destroyAllFieldText();
            txtCanoe.setEditable(true);
            btnAddRecord.setEnabled(false);
            btnNextRecord.setEnabled(false);
            btnPrevRecord.setEnabled(false);
            btnDeleteRecord.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(JDialogMaintainCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update Record
     *
     * Updates the current result or inserts into the new cursor row
     *
     * @param newRecord - Whether this is a new cursor row
     * @throws java.sql.SQLException
     */
    private void updateRecord(boolean newRecord) throws SQLException
    {
        resultSet.updateString("clCanoe", txtCanoe.getText());
        resultSet.updateString("clManufacturer", txtManufacturer.getText());
        resultSet.updateString("clLayup", txtLayup.getText());
        resultSet.updateString("clWeight", txtWeight.getText());
        resultSet.updateString("clCapacity", txtCapacity.getText());

        if (newRecord) {
            resultSet.insertRow();
            resultSet.last();
            updateRecordInformationFields();
        } else {
            resultSet.updateRow();
        }
    }

    private void destroyAllFieldText()
    {
        txtCanoe.setText("");
        txtManufacturer.setText("");
        txtLayup.setText("");
        txtWeight.setText("");
        txtCapacity.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCanoe = new javax.swing.JTextField();
        txtManufacturer = new javax.swing.JTextField();
        txtLayup = new javax.swing.JTextField();
        txtWeight = new javax.swing.JTextField();
        txtCapacity = new javax.swing.JTextField();
        btnAddRecord = new javax.swing.JButton();
        btnUpdateRecord = new javax.swing.JButton();
        btnDeleteRecord = new javax.swing.JButton();
        btnPrevRecord = new javax.swing.JButton();
        btnNextRecord = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Record Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel1.setText("Canoe:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel2.setText("Manufacturer:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel3.setText("Layup:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setText("Weight:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setText("Capacity:");

        txtCanoe.setEditable(false);
        txtCanoe.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        txtManufacturer.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        txtLayup.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        txtWeight.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        txtCapacity.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtManufacturer)
                    .addComponent(txtLayup)
                    .addComponent(txtWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(txtCanoe, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(txtCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCanoe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLayup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddRecord.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnAddRecord.setText("Add");
        btnAddRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRecordActionPerformed(evt);
            }
        });

        btnUpdateRecord.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnUpdateRecord.setText("Update");
        btnUpdateRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordActionPerformed(evt);
            }
        });

        btnDeleteRecord.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnDeleteRecord.setText("Delete");
        btnDeleteRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRecordActionPerformed(evt);
            }
        });

        btnPrevRecord.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnPrevRecord.setText("<<");
        btnPrevRecord.setEnabled(false);
        btnPrevRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevRecordActionPerformed(evt);
            }
        });

        btnNextRecord.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnNextRecord.setText(">>");
        btnNextRecord.setEnabled(false);
        btnNextRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextRecordActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnPrevRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAddRecord)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdateRecord)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDeleteRecord)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExit))
                            .addComponent(btnNextRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAddRecord, btnDeleteRecord, btnExit, btnUpdateRecord});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrevRecord)
                    .addComponent(btnNextRecord))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRecord)
                    .addComponent(btnUpdateRecord)
                    .addComponent(btnDeleteRecord)
                    .addComponent(btnExit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAddRecord, btnDeleteRecord, btnExit, btnUpdateRecord});

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRecordActionPerformed
        newRecord();
}//GEN-LAST:event_btnAddRecordActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
}//GEN-LAST:event_btnExitActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            // Check if the table is empty
            if (!resultSet.next()) {
                newRecord(); // Add a new row
            } else {
                resultSet.first();
                btnPrevRecord.setEnabled(false);
                if (!resultSet.isLast()) btnNextRecord.setEnabled(true);
                updateRecordInformationFields();
            }                        
        } catch (SQLException ex) {
            Logger.getLogger(JDialogMaintainCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void btnNextRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextRecordActionPerformed
        try {
            resultSet.next();
            if (resultSet.isLast()) btnNextRecord.setEnabled(false);
            if (!resultSet.isFirst()) btnPrevRecord.setEnabled(true);
            updateRecordInformationFields();
        } catch (SQLException ex) {
            Logger.getLogger(JDialogMaintainCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnNextRecordActionPerformed

    private void btnPrevRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevRecordActionPerformed
        try {
            resultSet.previous();
            if (resultSet.isFirst()) btnPrevRecord.setEnabled(false);
            if (!resultSet.isLast()) btnNextRecord.setEnabled(true);
            updateRecordInformationFields();
        } catch (SQLException ex) {
            Logger.getLogger(JDialogMaintainCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_btnPrevRecordActionPerformed

    private void btnDeleteRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRecordActionPerformed
        try {
            // Check if this is the only record left in the table
            if (resultSet.isLast() && resultSet.isFirst()) {
                resultSet.deleteRow();
                newRecord();
            } else {
                resultSet.deleteRow();
                resultSet.first();
                btnPrevRecord.setEnabled(false); // Disable <<
                if (resultSet.isLast())
                    btnNextRecord.setEnabled(false);
                else
                    btnNextRecord.setEnabled(true);
                updateRecordInformationFields();
            }            
        } catch (SQLException ex) {
            Logger.getLogger(JDialogMaintainCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteRecordActionPerformed

    private void btnUpdateRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordActionPerformed
        try {
            // Check if you are actually updating the current row
            if (btnAddRecord.isEnabled()) {
                updateRecord(false);
            } else {
                updateRecord(true);
                btnAddRecord.setEnabled(true);
                txtCanoe.setEditable(false);
                if (!resultSet.isFirst())
                    btnPrevRecord.setEnabled(true);
                btnDeleteRecord.setEnabled(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDialogMaintainCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateRecordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRecord;
    private javax.swing.JButton btnDeleteRecord;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNextRecord;
    private javax.swing.JButton btnPrevRecord;
    private javax.swing.JButton btnUpdateRecord;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCanoe;
    private javax.swing.JTextField txtCapacity;
    private javax.swing.JTextField txtLayup;
    private javax.swing.JTextField txtManufacturer;
    private javax.swing.JTextField txtWeight;
    // End of variables declaration//GEN-END:variables

}
