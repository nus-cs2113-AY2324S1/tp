package cashleh.transaction;

import java.time.LocalDate;
/**
 * The Income class extends the Transaction class and is used to represent income transactions.
 * It provides constructors for creating income transactions with or without a specified date.
 * Additionally, it overrides the `toString` method to provide a custom string representation of the income.
 */
public class Income extends Transaction {

    public Income(String description, double amount) {
        super(description, amount);
    }

    public Income(String description, double amount, LocalDate date) {
        super(description, amount, date);
    }
    @Override
    public String toString() {
        return getDescription() + " (amount: " + getAmount() + ", date: " + getDate() + ")";
    }
}
