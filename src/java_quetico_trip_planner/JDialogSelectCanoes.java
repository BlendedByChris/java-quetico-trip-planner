package java_quetico_trip_planner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 * Canoe Selection JFrame
 * 
 * This dialog will allow the user to select which canoe types they would like
 * to use during their trip. It will access the canoe types from a database.
 *
 * @author cleblanc
 */
public class JDialogSelectCanoes extends javax.swing.JDialog {

    private ResultSet resultSet = null;
    private TripInformation tripInformation = TripInformation.getInstance();

    private int unassignedGuests = tripInformation.totalGuests;

    private String [] canoeHeader = tripInformation.canoeHeader;
    private String [][] canoes = tripInformation.canoes;

    private DefaultTableModel tableModel;
    
    private int tblCanoeSelectionsRowCount = 0;
    
    /** Creates new form JDialogSelectCanoes */
    public JDialogSelectCanoes() {

        tableModel = new DefaultTableModel(canoes, canoeHeader);

        initComponents();

        // Set the result set for use in this dialog instance
        try {
            resultSet = new DbCanoe().getAllCanoes();
        } catch (SQLException ex) {
            Logger.getLogger(JDialogSelectCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Populate the JList of canoe types
        try {
            updateCanoeChoices();
        } catch (SQLException ex) {
            Logger.getLogger(JDialogSelectCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Populate group fields
        updateTripInformationFields();
    }

    /**
     * Set Custom Frame Properties
     *
     * Set any custom frame properties not specified by NetBeans.
     */
    public void setCustomFrameProperties()
    {
         // Set the window size (since NetBeans wont)
        this.setSize(430, 340);

        // Center the frame on the screen
        this.setLocationRelativeTo(null);

        // Set the custom icon
        javax.swing.ImageIcon LogoIconImage =
            new javax.swing.ImageIcon("icon.jpg");
            java.awt.Image LogoIcon =
            LogoIconImage.getImage();
            setIconImage(LogoIcon);
    }

    /**
     * Select Canoe
     *
     * Sets the canoe selection table row based on the canoe selection list
     * @throws SQLException
     */
    private void selectCanoe() throws SQLException
    {
        ResultSet c = new DbCanoe().getCanoeByCanoe(
                listCanoeChoices.getSelectedValue().toString());
        c.next();
        String capacity = c.getString("clCapacity");
        int capacityInt = Integer.parseInt(capacity);

        // Check for unasisgned guests
        if ((unassignedGuests - capacityInt) >= 0)
        {
            unassignedGuests = unassignedGuests - capacityInt;
            txtUnassignedGuests.setText(Integer.toString(unassignedGuests));
            tblCanoeSelections.setValueAt(c.getString("clCanoe"),
                    tblCanoeSelectionsRowCount, 0);
            tblCanoeSelections.setValueAt(c.getString("clManufacturer"),
                    tblCanoeSelectionsRowCount, 1);
            tblCanoeSelections.setValueAt(c.getString("clLayup"),
                    tblCanoeSelectionsRowCount, 2);
            tblCanoeSelections.setValueAt(c.getString("clWeight"),
                    tblCanoeSelectionsRowCount, 3);
            tblCanoeSelections.setValueAt(capacity,
                    tblCanoeSelectionsRowCount, 4);
            tblCanoeSelectionsRowCount++;
        }
    }

    /**
     * Update Canoe Choices
     *
     * Populates the JList of canoe choices
     * @throws java.sql.SQLException
     */
    private void updateCanoeChoices() throws SQLException
    {
        DefaultListModel list = new DefaultListModel();
        int i = 0;
        while (resultSet.next()) {
            list.add(i, resultSet.getString("clCanoe"));
            i++;
        }
        listCanoeChoices.setModel(list);
    }

    /**
     * Update Trip Information Fields
     *
     * Updates the trip information fields
     */
    private void updateTripInformationFields()
    {
        txtGroupLeader.setText(tripInformation.groupLeader);
        txtTotalGuests.setText(Integer.toString(tripInformation.totalGuests));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpGroupInformation = new javax.swing.JPanel();
        txtUnassignedGuests = new javax.swing.JTextField();
        lblNotAssigned = new javax.swing.JLabel();
        txtTotalGuests = new javax.swing.JTextField();
        lblTotalGuests = new javax.swing.JLabel();
        txtGroupLeader = new javax.swing.JTextField();
        lblGroupLeader = new javax.swing.JLabel();
        grpCanoeSelections = new javax.swing.JPanel();
        scrollCanoeSelections = new javax.swing.JScrollPane();
        tblCanoeSelections = new javax.swing.JTable();
        grpCanoeChoices = new javax.swing.JPanel();
        scrollCanoeChoices = new javax.swing.JScrollPane();
        listCanoeChoices = new javax.swing.JList();
        buttonSelect = new javax.swing.JButton();
        buttonChange = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quetico Canoe Selector");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        grpGroupInformation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Group Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        grpGroupInformation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUnassignedGuests.setEditable(false);
        txtUnassignedGuests.setFont(new java.awt.Font("Arial", 0, 11));
        grpGroupInformation.add(txtUnassignedGuests, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 40, -1));

        lblNotAssigned.setFont(new java.awt.Font("Arial", 0, 11));
        lblNotAssigned.setText("Not Assigned:");
        grpGroupInformation.add(lblNotAssigned, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        txtTotalGuests.setEditable(false);
        txtTotalGuests.setFont(new java.awt.Font("Arial", 0, 11));
        grpGroupInformation.add(txtTotalGuests, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 40, -1));

        lblTotalGuests.setFont(new java.awt.Font("Arial", 0, 11));
        lblTotalGuests.setText("Total Guests:");
        grpGroupInformation.add(lblTotalGuests, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        txtGroupLeader.setEditable(false);
        txtGroupLeader.setFont(new java.awt.Font("Arial", 0, 11));
        grpGroupInformation.add(txtGroupLeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 130, 20));

        lblGroupLeader.setFont(new java.awt.Font("Arial", 0, 11));
        lblGroupLeader.setText("Group Leader Name:");
        grpGroupInformation.add(lblGroupLeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        getContentPane().add(grpGroupInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, 110));

        grpCanoeSelections.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Canoe Selections", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        grpCanoeSelections.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCanoeSelections.setModel(tableModel);
        tblCanoeSelections.getTableHeader().setReorderingAllowed(false);
        scrollCanoeSelections.setViewportView(tblCanoeSelections);

        grpCanoeSelections.add(scrollCanoeSelections, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 370, 100));

        getContentPane().add(grpCanoeSelections, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 400, 140));

        grpCanoeChoices.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Canoe Choices", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        grpCanoeChoices.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listCanoeChoices.setFont(new java.awt.Font("Arial", 0, 11));
        scrollCanoeChoices.setViewportView(listCanoeChoices);

        grpCanoeChoices.add(scrollCanoeChoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 80));

        getContentPane().add(grpCanoeChoices, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 130, 110));

        buttonSelect.setFont(new java.awt.Font("Arial", 0, 11));
        buttonSelect.setText("Select");
        buttonSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSelectActionPerformed(evt);
            }
        });
        getContentPane().add(buttonSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 80, -1));

        buttonChange.setFont(new java.awt.Font("Arial", 0, 11));
        buttonChange.setText("Change");
        getContentPane().add(buttonChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 80, -1));

        buttonDelete.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        buttonDelete.setText("Delete");
        getContentPane().add(buttonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, 80, -1));

        buttonExit.setFont(new java.awt.Font("Arial", 0, 11));
        buttonExit.setText("Exit");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });
        getContentPane().add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // Set initial frame properties
        setCustomFrameProperties();
    }//GEN-LAST:event_formWindowOpened

    private void buttonSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSelectActionPerformed
        try {
            selectCanoe();
        } catch (SQLException ex) {
            Logger.getLogger(JDialogSelectCanoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonSelectActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed

        for (int i=0; i < tableModel.getRowCount(); i++) {
            for (int j=0; j < tableModel.getColumnCount(); j++) {
                canoes[i][j] = (String) tableModel.getValueAt(i, j);
            }
        }
        tripInformation.canoes = canoes;
        dispose();
    }//GEN-LAST:event_buttonExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChange;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonSelect;
    private javax.swing.JPanel grpCanoeChoices;
    private javax.swing.JPanel grpCanoeSelections;
    private javax.swing.JPanel grpGroupInformation;
    private javax.swing.JLabel lblGroupLeader;
    private javax.swing.JLabel lblNotAssigned;
    private javax.swing.JLabel lblTotalGuests;
    private javax.swing.JList listCanoeChoices;
    private javax.swing.JScrollPane scrollCanoeChoices;
    private javax.swing.JScrollPane scrollCanoeSelections;
    private javax.swing.JTable tblCanoeSelections;
    private javax.swing.JTextField txtGroupLeader;
    private javax.swing.JTextField txtTotalGuests;
    private javax.swing.JTextField txtUnassignedGuests;
    // End of variables declaration//GEN-END:variables

}
