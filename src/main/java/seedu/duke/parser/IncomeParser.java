package seedu.duke.parser;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Represents the parser for the Income class.
 * This class serves to parse the user input into a format that the Income class can understand
 */
public class IncomeParser {

    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String AMOUNT_FIELD = "am";
    public static final String INDEX_FIELD = "in";

    private static final Logger LOGGER = Logger.getLogger(IncomeParser.class.getName());

    static {
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/IncomeParser.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }


    /**
     * This method is used to parse the date of the income.
     * This method is used by the IncomeParser class in the application
     * This method is used by the IncomeManager class in the application
     *
     * @param incomeDateString String containing the date of the income
     * @return incomeDate LocalDate containing the date of the income
     * @throws KaChinnnngException if there is an error in the command
     */
    public static LocalDate parseDate(String incomeDateString) throws KaChinnnngException {
        assert incomeDateString != null : "incomeDateString should not be null";

        LocalDate incomeDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            incomeDate = LocalDate.parse(incomeDateString, formatter);
        } catch (DateTimeParseException e) {
            LOGGER.log(Level.WARNING, "Invalid date format" + incomeDateString, e);
            throw new KaChinnnngException("Please enter a valid date in the format dd/MM/yyyy");
        }
        if (incomeDate.isAfter(LocalDate.now())) {
            LOGGER.log(Level.WARNING, "Date is in the future" + incomeDateString);
            throw new KaChinnnngException("Please enter a date that is not in the future");
        }
        return incomeDate;
    }

    /**
     * This method is used to parse the fields of the income.
     * This method is used by the IncomeManager class in the application
     *
     * @param argumentsByField HashMap containing the description, date, and amount of the income
     * @return Income object created from the provided fields
     * @throws KaChinnnngException if there is an error in the command
     */
    public static Income parseIncome(HashMap<String, String> argumentsByField) throws KaChinnnngException {
        if (argumentsByField == null) {
            throw new KaChinnnngException("Income fields cannot be null");
        }
        if (!argumentsByField.containsKey(DESCRIPTION_FIELD) ||
                !argumentsByField.containsKey(DATE_FIELD) ||
                !argumentsByField.containsKey(AMOUNT_FIELD)) {
            throw new KaChinnnngException("Missing fields detected");
        }

        String incomeDescriptionString = argumentsByField.get(DESCRIPTION_FIELD);
        String incomeDateString = argumentsByField.get(DATE_FIELD);
        String incomeAmountString = argumentsByField.get(AMOUNT_FIELD);
        LocalDate incomeDate = parseDate(incomeDateString);
        double incomeAmount;

        try {
            incomeAmount = Double.parseDouble(incomeAmountString);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Invalid amount format" + incomeAmountString, e);
            throw new KaChinnnngException("Please enter a valid amount");
        }

        if (incomeAmount > 999999.99 || incomeAmount <= 0) {
            LOGGER.log(Level.WARNING, "Invalid amount" + incomeAmountString);
            throw new KaChinnnngException("Income amount must be between $0.01 and $999999.99");
        }
        return new Income(incomeDescriptionString, incomeDate, incomeAmount);
    }

    /**
     * This method is used to get the index of the income.
     * This method is used by the IncomeDeleter class in the application
     *
     * @param argumentsByFields HashMap containing the index of the income
     * @return index int containing the index of the income
     * @throws KaChinnnngException if there is an error in the command
     */
    public static int getIndex(HashMap<String, String> argumentsByFields) throws KaChinnnngException {
        assert argumentsByFields != null : "argumentsByFields should not be null";
        if (!argumentsByFields.containsKey(INDEX_FIELD)) {
            throw new KaChinnnngException("Missing index field detected");
        }
        int index;
        try {
            index = Integer.parseInt(argumentsByFields.get(INDEX_FIELD));
        } catch (NumberFormatException e) {
            throw new KaChinnnngException("Please enter a valid index");
        }
        if (index <= 0) {
            throw new KaChinnnngException("Please enter a valid index");
        }
        return index;
    }
}
