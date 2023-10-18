package cashleh.transaction;

import java.time.LocalDate;

public class Expense extends Transaction {
    public Expense(String description, double amount) {
        super(description, amount);
    }

    public Expense(String description, double amount, LocalDate date) {
        super(description, amount, date);
    }

    public String toString() {
        return getDescription() + " (amount: " + getAmount() + ")";
    }

}
