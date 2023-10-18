package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.util.logging.Logger;

/**
 * Represents the abstract class for all financial records.
 * This class serves as a blueprint for all specific financial record classes in the application
 *
 */
public abstract class FinancialRecord{

    // Logger instance to log events and issues that occur during the execution of this class.
    private static final Logger LOGGER = Logger.getLogger(FinancialRecord.class.getName());
    
    protected String description;
    protected LocalDate date;
    protected double amount;

    /**
     * This method is used to create a new financial record.
     * This method is used by all specific financial record creation classes in the application
     *
     * @param description String containing the description of the financial record
     * @param date LocalDate containing the date of the financial record
     * @param amount double containing the amount of the financial record
     * @throws KaChinnnngException if there is an error in the command
     */
    public FinancialRecord(String description, LocalDate date, double amount) throws KaChinnnngException {
        assert description != null : "description should not be null";
        assert date != null : "date should not be null";

        if (description.trim().isEmpty()) {
            LOGGER.warning("Description is empty");
            throw new KaChinnnngException("Description cannot be empty!");
        }
        if (amount < 0) {
            LOGGER.warning("Amount is negative");
            throw new KaChinnnngException("Amount cannot be negative!");
        }
        if (date.isAfter(LocalDate.now())) {
            LOGGER.warning("Date is in the future");
            throw new KaChinnnngException("Date cannot be in the future!");
        }
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    /**
     * This method is used to get the category of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @return String containing the category of the financial record
     */
    public String getCategory() {
        return "";
    }

    /**
     * This method is used to get the description of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @return String containing the description of the financial record
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method is used to get the date of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @return LocalDate containing the date of the financial record
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * This method is used to get the date of the financial record in a string format.
     * This method is used by all specific financial record classes in the application
     *
     * @return String containing the date of the financial record in a string format
     */
    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        return date.format(formatter);
    }
    /**
     * This method is used to get the amount of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @return double containing the amount of the financial record
     */
    public double getAmount() {
        return amount;
    }

    /**
     * This method is used to set the description of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @param description String containing the description of the financial record
     * @throws KaChinnnngException if there is an error in the command
     */
    public void setDescription(String description) throws KaChinnnngException {
        assert description != null : "description should not be null";
        if (description.trim().isEmpty()) {
            throw new KaChinnnngException("Description cannot be empty!");
        }
        this.description = description;
    }

    /**
     * This method is used to set the date of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @param date LocalDate containing the date of the financial record
     * @throws KaChinnnngException if there is an error in the command
     */
    public void setDate(LocalDate date) throws KaChinnnngException {
        assert date != null : "date should not be null";
        this.date = date;
    }

    /**
     * This method is used to set the amount of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @param amount double containing the amount of the financial record
     * @throws KaChinnnngException if there is an error in the command
     */
    public void setAmount(double amount) throws KaChinnnngException {
        assert amount >= 0 : "amount should not be negative";
        this.amount = amount;
    }

    /**
     * This method is used to get the category of the financial record.
     * This method is used by all specific financial record classes in the application
     *
     * @return String containing the category of the financial record
     */
    public String toString() {
        return "";
    }
}
