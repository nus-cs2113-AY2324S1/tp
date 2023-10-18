package seedu.duke.parser;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;

/**
 * Represents the parser for the Income class.
 * This class serves to parse the user input into a format that the Income class can understand
 */
public class IncomeParser {
    public static final String DESCRIPTION_FIELD = "de";
    public static final String DATE_FIELD = "da";
    public static final String AMOUNT_FIELD = "am";
    public static final String INDEX_FIELD = "in";

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
        LocalDate incomeDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            incomeDate = LocalDate.parse(incomeDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new KaChinnnngException("Please enter a valid date in the format dd/MM/yyyy");
        }
        if (incomeDate.isAfter(LocalDate.now())) {
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
            throw new KaChinnnngException("Please enter a valid amount");
        }

        if (incomeAmount > 999999.99 || incomeAmount <= 0) {
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
