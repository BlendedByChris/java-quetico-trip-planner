package bcis3680_prob3;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Start Date Verifier Verifier
 *
 * Verifier to check if group leader field is valid
 * @author cleblanc
 */
public class VerifierEndDate extends InputVerifier {

    // Default simple date instance for converting dates
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    // Start date
    private JTextField txtStartDate;

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
    public VerifierEndDate(JTextField input, JLabel lblError)
    {
        super();
        this.txtStartDate = input;
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
        JTextField txtEndDate    = (JTextField)input; 
        
        // Check that the field has text
        if (hasText(txtEndDate.getText())) {
            // Check that the field has a date
            if(hasValidDate(txtEndDate.getText())) {
                // Set the date (java is ugly and requires you to do a catch)
                try {
                    Date endDate = this.sdf.parse(txtEndDate.getText());
                    hasDateAfterOrOnStartDate(endDate);
                    hasDateBefore30Days(endDate);
                } catch (Exception e) {}             
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
        }
        return true;         
    }

    /**
     * Has Valid Date
     *
     * Determin if the provided string contains a valid date
     *
     * @param input
     * @return boolean
     */
    private boolean hasValidDate(String input)
    {       
        // Store a temporary date
        Date parsedDate = null;
        
        // Make sure it's not going to do anything goofy
        sdf.setLenient(false);
        
        // Check that it is parseable
        try {
            parsedDate = this.sdf.parse(input);
        } catch (Exception e) {
            this.errorStr = this.errorStr + "This field must be a date in"+
                    "format MM/dd/yyyy.\n";
            return false;
        }
        
        // Check that it is is a valid date
        if (!this.sdf.format(parsedDate).equals(input)) {
            this.errorStr = this.errorStr + "This field must be a date in"+
            "format MM/dd/yyyy.\n";
            return false;
        }        
        return true;
    }

    /**
     * Has Date After Start Date
     *
     * Determine if the input date is after the start date passed in the
     * constructor.
     *
     * @param input
     * @return boolean
     */
    private boolean hasDateAfterOrOnStartDate(Date input)
    {
        // Set the date (java is ugly and requires you to do a catch)
        try {
            Date startDate = this.sdf.parse(txtStartDate.getText());
            // Check if the input is past the startDate
            if (input.before(startDate))
            {
                this.errorStr = this.errorStr + "This field must be past the "+
                        "start date.\n";
                return false;
            }
        } catch (Exception e) {}
        return true;
    }

    /**
     * Has Date Before 30 Days
     *
     * Determine if the input is before 30 days from itself
     *
     * @param input
     * @return boolean
     */
    private boolean hasDateBefore30Days(Date input)
    {
        // Set the date (java is ugly and requires you to do a catch)
        try {
            Date startDate = this.sdf.parse(txtStartDate.getText());
            
            // Get a calender instance and add a year to it
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);
            cal.add(Calendar.DATE, 30);
            
            // Get the result from the addition
            Date startDatePlus30Days = cal.getTime();
            
            // Check that the input is less than one month from the start date
            if (input.after(startDatePlus30Days))
            {
                this.errorStr = this.errorStr + "This field must be less than "+
                "thirty days from the start date.\n";
                return false;
            }
            
            return false;
        } catch (Exception e) { }
        return true;
    }
}
