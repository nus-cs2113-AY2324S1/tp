package cashleh.transaction;

import java.time.LocalDate;

public class Income extends Transaction {

    public Income(String description, double amount) {
        super(description, amount);
    }

    public Income(String description, double amount, LocalDate date) {
        super(description, amount, date);
    }
    @Override
    public String toString() {
        return getDescription() + " (amount: " + getAmount() + ")";
    }
}
