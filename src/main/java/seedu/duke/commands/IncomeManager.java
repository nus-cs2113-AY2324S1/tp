package seedu.duke.commands;

import seedu.duke.parser.IncomeParser;
import java.util.HashMap;
import seedu.duke.financialrecords.Income;

/**
 * Represents the class that manages the creation of a new income.
 * This class serves as a blueprint for all specific income creation classes in the application
 *
 */
public class IncomeManager extends Commands{
    private final String details;
    private Income newIncome;

    public IncomeManager(String details) {
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
        // uses a HashMap to store the fields of the income
        HashMap<String,String> incomeFields = new HashMap<>();

        String[] parts = details.split("/description|/date|/amount");


        if(parts.length != 4) {
            throw new KaChinnnngException("Missing fields detected");
        }
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, parts[1].trim());
        incomeFields.put(IncomeParser.DATE_FIELD, parts[2].trim());
        incomeFields.put(IncomeParser.AMOUNT_FIELD, parts[3].trim());

        return incomeFields;
    }
}
