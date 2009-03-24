package java_quetico_trip_planner;
import java.text.SimpleDateFormat; 
import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;


/**
 * Trip Information JFrame
 *
 * This dialog will allow the user to input trip information
 * and calculate the total cost of the trip.
 *
 * @author cleblanc
 */
public class JDialogMaintainTrip extends javax.swing.JDialog {

    // Default simple date instance for converting dates
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    // Per night fee for adults
    private static long PERNIGHTFEEADULT = 20;

    // Per night fee for children
    private static long PERNIGHTFEECHILDREN = 8;
    
    /** 
     * Trip Information JFrame
     * 
     * Creates all of the components for the JFrame dialog.
     */
    public JDialogMaintainTrip() {
        initComponents();
        setCustomFrameProperties();
        updateTripInformationFields();
    }
    
    /**
     * Set Custom Frame Properties
     * 
     * Set any custom frame properties not specified by NetBeans.
     */
    private void setCustomFrameProperties()
    {
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
     * Set Txt Children Text
     * 
     * Updates the value of txtChildren text field to reflect the number of
     * children in the group.
     * 
     */
    private void setTxtChildrenText()
    {
        try {
            // Convert total guests and adults to integers
            int totalGuests = Integer.parseInt(txtTotalGuests.getText());
            int adults = Integer.parseInt(txtAdults.getText());

            // Determine the number of children by subtraction
            int children = totalGuests - adults;

            if (children >= 0) {
                // Set the text for the txtChildren field
                txtChildren.setText(children + "");
            }
        } catch (Exception e) {
            txtChildren.setText("0");
        }
    }

    /**
     * Set Txt Trip Duration
     *
     * Updates the value of the txtTripDuration text field to reflect the total
     * duration of the trip based on the start and end dates.
     */
    private void setTxtTripDuration()
    {
        try {
            // Parse dates for comparison
            sdf.setLenient(false);
            Date startDate = this.sdf.parse(txtStartDate.getText());
            Date endDate = this.sdf.parse(txtEndDate.getText());
            
            // Subtract dates and convert to days
            long tripDuration = ((endDate.getTime() - startDate.getTime())
                    / (1000 * 60 * 60 * 24)) +1;
            // Clear if invalid
            if (tripDuration > 0) {
                txtTripDuration.setText(tripDuration + " days");
            } else {
                txtTripDuration.setText("");
            }
        } catch (Exception e) {
            // Clear if invalid
            txtTripDuration.setText("");
        }        
    }

    /**
     * Set Txt Total Camping Fees
     *
     * Updates the value of the txtTotalCampingFees text field to reflect
     * the total fees for a trip based on duration and number and type of
     * guests.
     */
    private void setTxtTotalCampingFees()
    {
        // Parse dates for comparison (has to be done in a try block)
        try {
            int totalAdults = Integer.parseInt(txtAdults.getText());
            int totalChildren = Integer.parseInt(txtChildren.getText());

            sdf.setLenient(false);
            Date startDate = this.sdf.parse(txtStartDate.getText());
            Date endDate = this.sdf.parse(txtEndDate.getText());

            // Subtract dates and convert to days
            long tripDuration = (endDate.getTime() - startDate.getTime())
                    / (1000 * 60 * 60 * 24);
            
            // Do actual calculations
            long totalCampingFeeLong = ((tripDuration+1) *
                    (totalAdults * PERNIGHTFEEADULT)) +
                    ((tripDuration+1) *
                    (totalChildren * PERNIGHTFEECHILDREN));

            // Convert the total to a currency
            String totalCampingFee = NumberFormat.getCurrencyInstance()
                    .format(totalCampingFeeLong);

            // Set the text
            txtTotalCampingFees.setText(totalCampingFee);

            // Enable the exit button if we succesfully calculated the total
            buttonExit.setEnabled(true);

        } catch (Exception e) {
            // Clear if invalid
            txtTripDuration.setText("");

            // Disable the exit button if we couldn't calculated the total
            buttonExit.setEnabled(false);

        }
    }

    /**
     * Set All Fields Default
     *
     * Returns the frame to it's default state without entered data.
     */
    private void setAllFieldsDefault()
    {
        // Clear text fields
        txtGroupLeader.setText("");
        txtTotalGuests.setText("");
        txtAdults.setText("");
        txtChildren.setText("");
        txtStartDate.setText("");
        txtEndDate.setText("");
        txtTripDuration.setText("");
        txtTotalCampingFees.setText("");

        // Uncheck checkboxes
        checkTow.setSelected(false);
        checkCanoeRental.setSelected(false);

        // Clear radio groups
        grpPaymentType.clearSelection();

        // Set exit back to disabled?
        buttonExit.setEnabled(false);
    }

    /**
     * Destroy Dialog
     *
     * This method checks if the application is allowed to exit and does just
     * that.
     */
    private void destroyDialog()
    {
        // Check if the group has a selection (in a try-block incase it is null)
        try {
            // Check if the exit button is enabled
            if (buttonExit.isEnabled()) {
                if (grpPaymentType.getSelection().isSelected()) {
                    if (checkCanoeRental.isSelected()) {
                        // Prompt the user if they would like to select their canoes
                        int pickCanoes = JOptionPane.showConfirmDialog(null, 
                                "Would you like to pick your canoes?",
                                "Pick canoes?",
                                JOptionPane.YES_NO_OPTION);
                        if (pickCanoes == JOptionPane.YES_OPTION)
                        {
                            // Draw the canoe selection jframe
                            java.awt.EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                   new JDialogSelectCanoes().setVisible(true);
                                }
                            });
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Please finish calculating before exit.");
                    return;
                }
                updateTripInformation();
                dispose();
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,
                     "Please specify a payment type.");
        }
    }

    /**
     * Update Trip Information
     *
     * Saves the trip information into the trip information object.
     */
    private void updateTripInformation()
    {
        TripInformation ti = TripInformation.getInstance();
        ti.groupLeader = txtGroupLeader.getText();
        
        ti.totalGuests = Integer.parseInt(txtTotalGuests.getText());
        ti.adults = Integer.parseInt(txtAdults.getText());
        ti.children = ti.totalGuests - ti.adults;
        
        if (radioCash.isSelected())
            ti.paymentType = "Cash";
        else if (radioCreditCard.isSelected())
            ti.paymentType = "Credit Card";
        else if (radioCheck.isSelected())
            ti.paymentType = "Check";
        else if (radioOther.isSelected())
            ti.paymentType = "Other";

        ti.startDate = txtStartDate.getText();
        ti.endDate = txtEndDate.getText();
        ti.tow = checkTow.isSelected();
        ti.canoeRental = checkCanoeRental.isSelected();

        ti.tripDuration = txtTripDuration.getText();
        ti.totalCampingFees = txtTotalCampingFees.getText();

        ti.updated = true;
    }

    /**
     * Update Trip Information Fields
     *
     * Repopulates the dialog fields based on the trip information class
     */
    private void updateTripInformationFields()
    {
        TripInformation ti = TripInformation.getInstance();
        txtGroupLeader.setText(ti.groupLeader);
        txtTotalGuests.setText(Integer.toString(ti.totalGuests));
        txtAdults.setText(Integer.toString(ti.adults));

        if (ti.paymentType.equals("Cash"))
            radioCash.setSelected(true);
        if (ti.paymentType.equals("Credit Card"))
            radioCreditCard.setSelected(true);
        if (ti.paymentType.equals("Check"))
            radioCheck.setSelected(true);
        if (ti.paymentType.equals("Other"))
            radioOther.setSelected(true);
        
        txtStartDate.setText(ti.startDate);
        txtEndDate.setText(ti.endDate);
        if (ti.tow) checkTow.setSelected(true);
        if (ti.canoeRental) checkCanoeRental.setSelected(true);

        if (ti.updated = true)
            destroyErrorLabels();
    }

    /**
     * Destroy Error Labels
     *
     * Hides all of the field verifier errors
     */
    private void destroyErrorLabels()
    {
        lblGroupLeaderError.setVisible(false);
        lblTotalGuestsError.setVisible(false);
        lblAdultsError.setVisible(false);
        lblStartDateError.setVisible(false);
        lblEndDateError.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpPaymentType = new javax.swing.ButtonGroup();
        lblGroupLeader = new javax.swing.JLabel();
        txtGroupLeader = new javax.swing.JTextField();
        paneGroupSize = new javax.swing.JPanel();
        lblAdultsChildren = new javax.swing.JLabel();
        lblTotalGuests = new javax.swing.JLabel();
        txtTotalGuests = new javax.swing.JTextField();
        txtAdults = new javax.swing.JTextField();
        lblSlash = new javax.swing.JLabel();
        txtChildren = new javax.swing.JTextField();
        lblAdultsError = new javax.swing.JLabel();
        lblTotalGuestsError = new javax.swing.JLabel();
        paneAdditionalEquipment = new javax.swing.JPanel();
        checkTow = new javax.swing.JCheckBox();
        checkCanoeRental = new javax.swing.JCheckBox();
        paneTripDuration = new javax.swing.JPanel();
        lblStartDate = new javax.swing.JLabel();
        lblEndDate = new javax.swing.JLabel();
        txtStartDate = new javax.swing.JTextField();
        txtEndDate = new javax.swing.JTextField();
        lblStartDateError = new javax.swing.JLabel();
        lblEndDateError = new javax.swing.JLabel();
        panePaymentType = new javax.swing.JPanel();
        radioCash = new javax.swing.JRadioButton();
        radioCheck = new javax.swing.JRadioButton();
        radioCreditCard = new javax.swing.JRadioButton();
        radioOther = new javax.swing.JRadioButton();
        buttonCalculate = new javax.swing.JButton();
        buttonReset = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        lblTotalCampingFees = new javax.swing.JLabel();
        lblTripDuration = new javax.swing.JLabel();
        txtTripDuration = new javax.swing.JTextField();
        txtTotalCampingFees = new javax.swing.JTextField();
        lblGroupLeaderError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quetico Trip Planning Information");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGroupLeader.setFont(new java.awt.Font("Arial", 0, 11));
        lblGroupLeader.setText("Group Leader Name:");
        getContentPane().add(lblGroupLeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        txtGroupLeader.setFont(new java.awt.Font("Arial", 0, 11));
        txtGroupLeader.setInputVerifier(new VerifierGroupLeader(lblGroupLeaderError));
        getContentPane().add(txtGroupLeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 140, -1));

        paneGroupSize.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Group Size", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
        paneGroupSize.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAdultsChildren.setFont(new java.awt.Font("Arial", 0, 11));
        lblAdultsChildren.setText("Adults / Children:");
        paneGroupSize.add(lblAdultsChildren, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        lblTotalGuests.setFont(new java.awt.Font("Arial", 0, 11));
        lblTotalGuests.setText("Total Guests:");
        paneGroupSize.add(lblTotalGuests, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

        txtTotalGuests.setFont(new java.awt.Font("Arial", 0, 11));
        txtTotalGuests.setInputVerifier(new VerifierTotalGuests(txtAdults,
            lblTotalGuestsError));
    txtTotalGuests.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            txtTotalGuestsCaretUpdate(evt);
        }
    });
    txtTotalGuests.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            txtTotalGuestsFocusGained(evt);
        }
    });
    paneGroupSize.add(txtTotalGuests, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 50, -1));

    txtAdults.setFont(new java.awt.Font("Arial", 0, 11));
    txtAdults.setInputVerifier(new VerifierAdults(txtTotalGuests, 
        lblAdultsError));
txtAdults.addCaretListener(new javax.swing.event.CaretListener() {
    public void caretUpdate(javax.swing.event.CaretEvent evt) {
        txtAdultsCaretUpdate(evt);
    }
    });
    txtAdults.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            txtAdultsFocusGained(evt);
        }
    });
    paneGroupSize.add(txtAdults, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 20, -1));

    lblSlash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblSlash.setText("/");
    paneGroupSize.add(lblSlash, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 10, 20));

    txtChildren.setEditable(false);
    txtChildren.setFont(new java.awt.Font("Arial", 0, 11));
    paneGroupSize.add(txtChildren, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 20, -1));

    lblAdultsError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_quetico_trip_planner/redicon.png"))); // NOI18N
    paneGroupSize.add(lblAdultsError, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 20, 20));

    lblTotalGuestsError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_quetico_trip_planner/redicon.png"))); // NOI18N
    paneGroupSize.add(lblTotalGuestsError, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 20, 20));

    getContentPane().add(paneGroupSize, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, 80));

    paneAdditionalEquipment.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Additional Equipment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

    checkTow.setFont(new java.awt.Font("Arial", 0, 11));
    checkTow.setText("Tow");

    checkCanoeRental.setFont(new java.awt.Font("Arial", 0, 11));
    checkCanoeRental.setText("Canoe Rental");

    javax.swing.GroupLayout paneAdditionalEquipmentLayout = new javax.swing.GroupLayout(paneAdditionalEquipment);
    paneAdditionalEquipment.setLayout(paneAdditionalEquipmentLayout);
    paneAdditionalEquipmentLayout.setHorizontalGroup(
        paneAdditionalEquipmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(paneAdditionalEquipmentLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(paneAdditionalEquipmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(checkTow)
                .addComponent(checkCanoeRental))
            .addContainerGap(53, Short.MAX_VALUE))
    );
    paneAdditionalEquipmentLayout.setVerticalGroup(
        paneAdditionalEquipmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(paneAdditionalEquipmentLayout.createSequentialGroup()
            .addComponent(checkTow)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(checkCanoeRental)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    getContentPane().add(paneAdditionalEquipment, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 160, -1));

    paneTripDuration.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trip Duration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N
    paneTripDuration.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    lblStartDate.setFont(new java.awt.Font("Arial", 0, 11));
    lblStartDate.setText("Start date:");
    paneTripDuration.add(lblStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 20));

    lblEndDate.setFont(new java.awt.Font("Arial", 0, 11));
    lblEndDate.setText("End date:");
    paneTripDuration.add(lblEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

    txtStartDate.setFont(new java.awt.Font("Arial", 0, 11));
    txtStartDate.setInputVerifier(new VerifierStartDate(txtEndDate,
        lblStartDateError));
paneTripDuration.add(txtStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 80, -1));

txtEndDate.setFont(new java.awt.Font("Arial", 0, 11));
txtEndDate.setInputVerifier(new VerifierEndDate(txtEndDate,
    lblEndDateError));
    txtEndDate.addCaretListener(new javax.swing.event.CaretListener() {
        public void caretUpdate(javax.swing.event.CaretEvent evt) {
            txtEndDateCaretUpdate(evt);
        }
    });
    paneTripDuration.add(txtEndDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 80, -1));

    lblStartDateError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_quetico_trip_planner/redicon.png"))); // NOI18N
    paneTripDuration.add(lblStartDateError, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 20, 20));

    lblEndDateError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_quetico_trip_planner/redicon.png"))); // NOI18N
    paneTripDuration.add(lblEndDateError, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 20, 20));

    getContentPane().add(paneTripDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 170, 80));

    panePaymentType.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

    grpPaymentType.add(radioCash);
    radioCash.setFont(new java.awt.Font("Arial", 0, 11));
    radioCash.setText("Cash");

    grpPaymentType.add(radioCheck);
    radioCheck.setFont(new java.awt.Font("Arial", 0, 11));
    radioCheck.setText("Check");

    grpPaymentType.add(radioCreditCard);
    radioCreditCard.setFont(new java.awt.Font("Arial", 0, 11));
    radioCreditCard.setText("Credit Card");

    grpPaymentType.add(radioOther);
    radioOther.setFont(new java.awt.Font("Arial", 0, 11));
    radioOther.setText("Other");
    radioOther.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            radioOtherActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panePaymentTypeLayout = new javax.swing.GroupLayout(panePaymentType);
    panePaymentType.setLayout(panePaymentTypeLayout);
    panePaymentTypeLayout.setHorizontalGroup(
        panePaymentTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panePaymentTypeLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panePaymentTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(radioCash)
                .addComponent(radioCheck))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panePaymentTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(radioOther)
                .addComponent(radioCreditCard))
            .addContainerGap(8, Short.MAX_VALUE))
    );
    panePaymentTypeLayout.setVerticalGroup(
        panePaymentTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panePaymentTypeLayout.createSequentialGroup()
            .addGroup(panePaymentTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(radioCash)
                .addComponent(radioCreditCard))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panePaymentTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(radioCheck)
                .addComponent(radioOther))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    getContentPane().add(panePaymentType, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 160, 80));

    buttonCalculate.setFont(new java.awt.Font("Arial", 0, 11));
    buttonCalculate.setText("Calculate");
    buttonCalculate.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonCalculateActionPerformed(evt);
        }
    });
    getContentPane().add(buttonCalculate, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 80, -1));

    buttonReset.setFont(new java.awt.Font("Arial", 0, 11));
    buttonReset.setText("Reset");
    buttonReset.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonResetActionPerformed(evt);
        }
    });
    getContentPane().add(buttonReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 80, -1));

    buttonExit.setFont(new java.awt.Font("Arial", 0, 11));
    buttonExit.setText("Exit");
    buttonExit.setEnabled(false);
    buttonExit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonExitActionPerformed(evt);
        }
    });
    getContentPane().add(buttonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 80, -1));

    lblTotalCampingFees.setFont(new java.awt.Font("Arial", 0, 11));
    lblTotalCampingFees.setText("Total Camping Fees:");
    getContentPane().add(lblTotalCampingFees, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 100, 20));

    lblTripDuration.setFont(new java.awt.Font("Arial", 0, 11));
    lblTripDuration.setText("Trip Duration: ");
    getContentPane().add(lblTripDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 20));

    txtTripDuration.setEditable(false);
    txtTripDuration.setFont(new java.awt.Font("Arial", 0, 11));
    getContentPane().add(txtTripDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 60, -1));

    txtTotalCampingFees.setEditable(false);
    txtTotalCampingFees.setFont(new java.awt.Font("Arial", 0, 11));
    txtTotalCampingFees.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtTotalCampingFeesActionPerformed(evt);
        }
    });
    getContentPane().add(txtTotalCampingFees, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 60, -1));

    lblGroupLeaderError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_quetico_trip_planner/redicon.png"))); // NOI18N
    getContentPane().add(lblGroupLeaderError, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 20, 20));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAdultsCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtAdultsCaretUpdate
        setTxtChildrenText();
    }//GEN-LAST:event_txtAdultsCaretUpdate

private void txtTotalCampingFeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalCampingFeesActionPerformed
    setTxtTotalCampingFees();
}//GEN-LAST:event_txtTotalCampingFeesActionPerformed

private void buttonCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCalculateActionPerformed
    setTxtTotalCampingFees();    
}//GEN-LAST:event_buttonCalculateActionPerformed

private void txtEndDateCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtEndDateCaretUpdate
    setTxtTripDuration();
}//GEN-LAST:event_txtEndDateCaretUpdate

private void txtTotalGuestsCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTotalGuestsCaretUpdate
    setTxtChildrenText();
}//GEN-LAST:event_txtTotalGuestsCaretUpdate

private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
    setAllFieldsDefault();
}//GEN-LAST:event_buttonResetActionPerformed

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    destroyDialog();
}//GEN-LAST:event_formWindowClosing

private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
    destroyDialog();
}//GEN-LAST:event_buttonExitActionPerformed

private void radioOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOtherActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_radioOtherActionPerformed

private void txtTotalGuestsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTotalGuestsFocusGained
    txtTotalGuests.setText("");
}//GEN-LAST:event_txtTotalGuestsFocusGained

private void txtAdultsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAdultsFocusGained
    txtAdults.setText("");
}//GEN-LAST:event_txtAdultsFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCalculate;
    private javax.swing.JButton buttonExit;
    private javax.swing.JButton buttonReset;
    private javax.swing.JCheckBox checkCanoeRental;
    private javax.swing.JCheckBox checkTow;
    private javax.swing.ButtonGroup grpPaymentType;
    private javax.swing.JLabel lblAdultsChildren;
    private javax.swing.JLabel lblAdultsError;
    private javax.swing.JLabel lblEndDate;
    private javax.swing.JLabel lblEndDateError;
    private javax.swing.JLabel lblGroupLeader;
    private javax.swing.JLabel lblGroupLeaderError;
    private javax.swing.JLabel lblSlash;
    private javax.swing.JLabel lblStartDate;
    private javax.swing.JLabel lblStartDateError;
    private javax.swing.JLabel lblTotalCampingFees;
    private javax.swing.JLabel lblTotalGuests;
    private javax.swing.JLabel lblTotalGuestsError;
    private javax.swing.JLabel lblTripDuration;
    private javax.swing.JPanel paneAdditionalEquipment;
    private javax.swing.JPanel paneGroupSize;
    private javax.swing.JPanel panePaymentType;
    private javax.swing.JPanel paneTripDuration;
    private javax.swing.JRadioButton radioCash;
    private javax.swing.JRadioButton radioCheck;
    private javax.swing.JRadioButton radioCreditCard;
    private javax.swing.JRadioButton radioOther;
    private javax.swing.JTextField txtAdults;
    private javax.swing.JTextField txtChildren;
    private javax.swing.JTextField txtEndDate;
    private javax.swing.JTextField txtGroupLeader;
    private javax.swing.JTextField txtStartDate;
    private javax.swing.JTextField txtTotalCampingFees;
    private javax.swing.JTextField txtTotalGuests;
    private javax.swing.JTextField txtTripDuration;
    // End of variables declaration//GEN-END:variables

}