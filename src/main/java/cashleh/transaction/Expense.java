package cashleh.transaction;

import java.time.LocalDate;
/**
 * The Expense class extends the Transaction class and is used to represent expense transactions.
 * It provides constructors for creating expense transactions with or without a specified date.
 * Additionally, it overrides the `toString` method to provide a custom string representation of the expense.
 */
public class Expense extends Transaction {

    /**
     * Constructs an Expense object with specified description and amount.
     * @param description Description of expense.
     * @param amount      Amount of expense.
     */
    public Expense(String description, double amount) {
        super(description, amount);
    }

    /**
     * Constructs an Expense object with specified description, amount and date.
     * @param description Description of expense.
     * @param amount      Amount of expense.
     * @param date        Date of expense.
     */
    public Expense(String description, double amount, LocalDate date) {
        super(description, amount, date);
    }

    /**
     * Constructs an Expense object with specified description, amount and date.
     * @param description Description of expense.
     * @param amount      Amount of expense.
     * @param category    Category of expense.
     */
    public Expense(String description, double amount, Categories category) {
        super(description, amount, category);
    }

    /**
     * Constructs an Expense object with specified description, amount and date.
     * @param description Description of expense.
     * @param amount      Amount of expense.
     * @param date        Date of expense.
     * @param category    Category of expense.
     */
    public Expense(String description, double amount, LocalDate date, Categories category) {
        super(description, amount, date, category);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Expense: ").append(getDescription());
        result.append(" (Amount: ").append(getAmount());

        if (getDate() != null) {
            result.append(", Date: ").append(getDate());
        }
        if (getCategory() != null) {
            result.append(", Category: ").append(getCategory());
        }
        result.append(")");

        return result.toString();
    }
}
