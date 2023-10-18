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

    public String toString() {
        return getDescription() + " (amount: " + getAmount() + ", date: " + getDate() + ")";
    }

}
