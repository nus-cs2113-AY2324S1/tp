package seedu.duke.financialrecords;
import seedu.duke.commands.KaChinnnngException;

import java.io.File;
import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Implementation of the Income class
 * Basic income class inherits from FinancialRecord and does not include any additional attributes
 */
public class Income extends FinancialRecord {
    // Logger instance to log events and issues that occur during the execution of this class.
    private static final Logger LOGGER = Logger.getLogger(Income.class.getName());

    static {
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/Income.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (SecurityException se) {
            LOGGER.log(Level.SEVERE, "Error creating log file", se);
            System.err.println("Insufficient permissions to create logs directory. Please check your permissions or " +
                    "run the program in a different directory.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }

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
