package java_quetico_trip_planner;
import javax.swing.*;

/**
 * Adults Verifier
 *
 * Verifier to check if adults field is valid
 * @author cleblanc
 */
public class VerifierAdults extends InputVerifier {

    // Total guests object
    private JTextField txtTotalGuests;

    // Error label for display next to element
    private JLabel lblError;

    // Error string
    private String errorStr = "";

    /**
     * Construct
     *
     * Call the parent constructor and set the total guests object
     *
     * @param input The total guests JTextField object
     */
    public VerifierAdults(JTextField txtTotalGuests, JLabel lblError)
    {
        super();
        this.txtTotalGuests = txtTotalGuests;
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
        JTextField txtAdults    = (JTextField)input;
        
        // Check if it has text
        if (hasText(txtAdults.getText())) {
            // Check if the text is numeric
            if (hasNumeric(txtAdults.getText())) {
                // Convert string to integers
                int adults = Integer.parseInt(txtAdults.getText());
                int totalGuests = Integer.parseInt(this.txtTotalGuests.getText());               
                
                 // Check the size of the group
                hasValidAmount(adults, totalGuests);
            }              
        }

        // Check if we have had any errors
        if (this.errorStr.length() > 0) {
            // Show the error
            lblError.setVisible(true);
            lblError.setToolTipText(this.errorStr);
            this.errorStr = "";
        }
        else
        {
            // Hide the error
            lblError.setVisible(false);
        }
        return true;
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
     * Has Valid Amount
     *
     * Determine if the adults is greater than one but less than the total
     * number of guests.
     *
     * @param adults
     * @param totalGuests
     * @return boolean
     */
    private boolean hasValidAmount(int adults, int totalGuests)
    {
        if (adults < 1 || adults > totalGuests) {
            this.errorStr = this.errorStr + "This field must be between 1 and "+
                    totalGuests + ".\n";
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
