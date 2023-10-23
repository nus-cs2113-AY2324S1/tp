package seedu.duke.commands;

import seedu.duke.parser.IncomeParser;
import java.util.HashMap;
import seedu.duke.financialrecords.Income;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.File;

/**
 * Represents the class that manages the creation of a new income.
 * This class serves as a blueprint for all specific income creation classes in the application
 *
 */
public class IncomeManager extends Commands{
    // Logger instance to log events and issues that occur during the execution of this class.
    private static final Logger LOGGER = Logger.getLogger(IncomeManager.class.getName());
    private final String details;
    private Income newIncome;

    static{
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/IncomeManager.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }
    public IncomeManager(String details) {
        assert details != null : "details should not be null"; // Ensure that details is not null
        this.details = details;
    }

    /**
     * This method is used to execute the command.
     *
     * @throws KaChinnnngException if there is an error in the command
     */
    @Override
    public void execute() throws KaChinnnngException {
        HashMap<String, String> incomeFields = extractIncomeFields(details);
        newIncome = IncomeParser.parseIncome(incomeFields);
        LOGGER.log(Level.INFO, "successful parsing of income"); // logging successful parsing of income
    }

    /**
     * This method returns the new income created.
     *
     * @return newIncome
     */
    public Income getNewIncome() {
        return newIncome;
    }

    /**
     * This method extracts the fields of the income.
     *
     * @param details String containing the details of the income
     * @return incomeFields HashMap containing the fields of the income
     * @throws KaChinnnngException if there is an error in the command
     */
    private HashMap<String, String> extractIncomeFields(String details) throws KaChinnnngException{
        assert details != null : "details should not be null";
        // uses a HashMap to store the fields of the income
        HashMap<String,String> incomeFields = new HashMap<>();

        String[] parts = details.split("/description|/date|/amount");

        // If the parts (description,date, amount) are not present, throw an exception
        if(parts.length != 4) {
            LOGGER.log(Level.INFO,"Missing fields detected in income details" + details);
            throw new KaChinnnngException("Missing fields detected");
        }
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, parts[1].trim());
        incomeFields.put(IncomeParser.DATE_FIELD, parts[2].trim());
        incomeFields.put(IncomeParser.AMOUNT_FIELD, parts[3].trim());

        return incomeFields;
    }
}
