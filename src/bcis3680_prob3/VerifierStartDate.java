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
public class VerifierStartDate extends InputVerifier {

    // Default simple date instance for converting dates
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    // Error label for display next to element
    private JLabel lblError;

    // End date
    private JTextField txtEndDate;

    // Error string
    private String errorStr = "";

    /**
     * Construct
     *
     * Call the parent constructor and set the end date object
     *
     * @param input The end date JTextField object
     */
    public VerifierStartDate(JTextField input, JLabel lblError)
    {
        super();
        txtEndDate = input;
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
        JTextField txtStartDate    = (JTextField)input; 
        
        // Check that the field has text
        if (hasText(txtStartDate.getText())) {
            // Check that the field has a date
            if(hasValidDate(txtStartDate.getText())) {
                // Set the date (java is ugly and requires you to do a catch)
                try {
                    Date startDate = this.sdf.parse(txtStartDate.getText());
                    // Check if the date is after today
                    if (hasDateAfterToday(startDate))
                    {
                        // Check if the date is less than a year from now
                        hasDateLessThanYear(startDate);
                    }
                } catch (Exception e) {}             
            }
        }
        
        // Check if we have had any errors
        if (this.errorStr.length() > 0) {
            // Show the error
            lblError.setVisible(true);
            lblError.setToolTipText(this.errorStr);
            this.errorStr = "";
            
            // Clear the dependant field txtEndDate
            this.txtEndDate.setText("");
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
     * Has Date After Today
     *
     * Determine if a given input is past today
     *
     * @param input
     * @return boolean
     */
    private boolean hasDateAfterToday(Date input)
    {
        // Get a calender instance
        Date today = Calendar.getInstance().getTime();
        
        // Check that the input is greater than today
        if (input.before(today))
        {
            this.errorStr = this.errorStr + "This field must be a time in the "+
            "future.\n";
            return false;
        }
        return true;
    }

    /**
     * Has Date Less Than Year
     *
     * Determine if the given input is less than a year from now
     *
     * @param input
     * @return boolean
     */
    private boolean hasDateLessThanYear(Date input)
    {
        // Get a calender instance to add to
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, +1);
        
        // Get the result from the calender addition
        Date oneYearFromNow = cal.getTime();
        
        // Check that the input is less than a year from now
        if (input.after(oneYearFromNow))
        {
            this.errorStr = this.errorStr + "This field must be less than "+
            "a year in the future.\n";
            return false;
        }
        return true;        
    }
}
