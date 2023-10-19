package seedu.duke.financialrecords;
import seedu.duke.commands.KaChinnnngException;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the Income class
 * Basic income class inherits from FinancialRecord and does not include any additional attributes
 */
public class Income extends FinancialRecord {
    // Logger instance to log events and issues that occur during the execution of this class.
    private static final Logger LOGGER = Logger.getLogger(Income.class.getName());

    /**
     * This method is used to create a new financial record.
     * This method is used by all specific financial record creation classes in the application
     *
     * @param description String containing the description of the financial record
     * @param date LocalDate containing the date of the financial record
     * @param amount double containing the amount of the financial record
     * @throws KaChinnnngException if there is an error in the command
     */
    public Income(String description, LocalDate date, double amount) throws KaChinnnngException {
        super(description, date, amount);
        LOGGER.log(Level.INFO,("Income created with description: " +
                description + " date: " + date + " amount: " + amount));
    }

    /**
     * This method is used to get the category of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @return String containing the category of the financial record
     */
    @Override
    public String toString() {
        return "Income: " + getDescription() + 
                " | Date: " + getDateString()+ " | Amount: $" + String.format("%.2f", getAmount());
    }
}
