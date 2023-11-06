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
        StringBuilder result = new StringBuilder();
        result.append("Income: ").append(getDescription());
        result.append(" (Amount: ").append(getAmount());

        if (getDate() != null) {
            result.append(", Date: ").append(getDateString());
        }
        if (getCategory() != null) {
            result.append(", Category: ").append(getCategory());
        }
        result.append(")");

        return result.toString();
    }
}
