package bcis3680_prob3;
import javax.swing.*;

/**
 * Total Guests Verifier
 *
 * Verifier to check if group leader field is valid
 * @author cleblanc
 */
public class VerifierTotalGuests extends InputVerifier {

    // Adults
    private JTextField txtAdults;

    // Error label for display next to element
    private JLabel lblError;

    // Error String
    private String errorStr = "";

    /**
     * Construct
     *
     * Call the parent constructor, set the adults object and set error label
     *
     * @param txtAdults The adults JTextField object
     * @param lblError The label to populate the error with
     */
    public VerifierTotalGuests(JTextField txtAdults, JLabel lblError)
    {
        super();
        this.txtAdults = txtAdults;
        this.lblError = lblError;
    }

    /**
     * Verify
     *
     * Verify a specified field and produce any errors
     *
     * @param input The verifying JComponent object
     * @return boolean Whether the input is valid
     */
    public boolean verify(JComponent input)
    {
        // Cast input as fieldname
        JTextField txtTotalGuests    = (JTextField)input;

        // Check if the input is empty
        if (hasText(txtTotalGuests.getText())) {
            // Check if the input has a numeric value
            if (hasNumeric(txtTotalGuests.getText()))
            {
                int totalGuests = Integer.parseInt(txtTotalGuests.getText());
                // Check if the number of guests is valid is valid
                hasValidAmount(totalGuests);
            }
        }
        
        // Check if we have had any errors
        if (this.errorStr.length() > 0) {
            // Show the error
            lblError.setVisible(true);
            lblError.setToolTipText(this.errorStr);
            this.errorStr = "";            
            return true;
        } else {
            // Hide the error
            lblError.setVisible(false);
            return true;
        }
       

    }

    /**
     * Has Valid Amount
     *
     * Determine if the input is greater than one but less than the nine.
     *
     * @param input
     * @return boolean
     */
    private boolean hasValidAmount(int input)
    {
        if (input < 1 || input > 9) {
            this.errorStr = this.errorStr + "Total guests must be between 1 and 9.\n";
            return false;
        } else {
            return true;
        }          
    }

    /**
     * Has Text
     *
     * Determine if the provided string is not empty otherwise store and error.
     *
     * @param input
     * @return boolean
     */
    private boolean hasText(String input)
    {
        if (input.equalsIgnoreCase("")) {
            this.errorStr = this.errorStr + "This field must not be empty. \n";
            return false;
        } else {
            return true;
        }          
    }

    /**
     * Has Numeric
     *
     * Determine if a given input can be converted to an integer
     *
     * @param input
     * @return boolean
     */
    private boolean hasNumeric(String input)
    {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            this.errorStr = this.errorStr + "This field must be numeric.\n";
            return false;
        }
    }
}
