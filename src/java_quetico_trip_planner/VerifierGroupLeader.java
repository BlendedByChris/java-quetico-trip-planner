package java_quetico_trip_planner;
import javax.swing.*;

/**
 * Group Leader Verifier
 *
 * Verifier to check if group leader field is valid
 * @author cleblanc
 */
public class VerifierGroupLeader extends InputVerifier {

    // Error label for display next to element
    private JLabel lblError;

    // Error string
    private String errorStr = "";

    /**
     * Construct
     *
     * Call the parent constructor and set error label
     *
     * @param input The adults JTextField object
     */
    public VerifierGroupLeader(JLabel lblError)
    {
        super();
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
    public boolean verify (JComponent input)
    {
        // Cast input as fieldname
        JTextField textField    = (JTextField)input;
        String groupLeader      = textField.getText();  

        // Check for if the field is empty
        if (hasText(textField.getText()))
        {
            // Check if it is a valid length
            hasValidLength(groupLeader);
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
     * Has Valid Length
     *
     * Check that the given input has a valid length of 5 characters or more
     *
     * @param input
     * @return boolean
     */
    private boolean hasValidLength(String input)
    {
        if (input.length() < 5) {
            this.errorStr = this.errorStr +  "Group leader name must be " +
                    "atleast 5 characters.\n";
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
}
