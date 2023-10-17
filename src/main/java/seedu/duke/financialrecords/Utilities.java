package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.expensetypes.UtilityType;
import java.time.LocalDate;

// Transport class extending Expense
public class Utilities extends Expense {
    private UtilityType utilityType;

    public Utilities(String description, LocalDate date, double amount,
                     UtilityType utilityType) throws KaChinnnngException {
        super(description, date, amount);
        this.utilityType = utilityType;
    }

    // Getter for transportation type
    public UtilityType getUtilityType() {
        return utilityType;
    }

    @Override
    public String toString() {
        return "Utilities Expense: " + getDescription() +
                " | Date: " + getDateString() + " | Amount: $" + String.format("%.2f", getAmount());
    }
}
