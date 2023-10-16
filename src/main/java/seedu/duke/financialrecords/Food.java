package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.expensetypes.MealType;
import java.time.LocalDate;

// Transport class extending Expense
public class Food extends Expense {
    private MealType mealType;

    public Food(String description, LocalDate date, double amount, MealType mealType) throws KaChinnnngException {
        super(description, date, amount);
        this.mealType = mealType;
    }

    // Getter for transportation type
    public MealType getMealType() {
        return mealType;
    }

    @Override
    public String toString() {
        return "Food Expense: " + getDescription() +
                " | Date: " + getDateString() + " | Amount: $" + String.format("%.2f", getAmount());
    }
}
