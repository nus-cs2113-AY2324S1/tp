package cashleh.transaction;

import java.time.LocalDate;
/**
 * The Income class extends the Transaction class and is used to represent income transactions.
 * It provides constructors for creating income transactions with or without a specified date.
 * Additionally, it overrides the `toString` method to provide a custom string representation of the income.
 */
public class Income extends Transaction {

    /**
     * Constructs an Income object with specified description and amount.
     * @param description Description of income.
     * @param amount      Amount of income.
     */
    public Income(String description, double amount) {
        super(description, amount);
    }

    /**
     * Constructs an Income object with specified description, amount and date.
     * @param description Description of income.
     * @param amount      Amount of income.
     * @param date        Date of income.
     */
    public Income(String description, double amount, LocalDate date) {
        super(description, amount, date);
    }

    /**
     * Constructs an Income object with specified description, amount and date.
     * @param description Description of income.
     * @param amount      Amount of income.
     * @param category    Category of income.
     */
    public Income(String description, double amount, Categories category) {
        super(description, amount, category);
    }

    /**
     * Constructs an Income object with specified description, amount and date.
     * @param description Description of income.
     * @param amount      Amount of income.
     * @param date        Date of income.
     * @param category    Category of income.
     */

    public Income(String description, double amount, LocalDate date, Categories category) {
        super(description, amount, date, category);
    }

    @Override
    public String toString() {
        if (getDate() == null && getCategory() == null) {
            return getDescription() + " (amount: " + getAmount() + ")";
        } else if (getDate() == null) {
            return getDescription() + " (amount: " + getAmount() + ", category: " + getCategory() + ")";
        } else if (getCategory() == null) {
            return getDescription() + " (amount: " + getAmount() + ", date: " + getDate() + ")";
        }
        return getDescription() + " (amount: " + getAmount()
                + ", date: " + getDate() + ", category: " + getCategory() + ")";
    }
}
