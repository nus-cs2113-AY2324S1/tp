package cashleh.transaction;
public class Expense extends Transaction {
    public Expense(String description, double amount) {
        super(description, amount);
    }

    public String toString() {
        return getDescription() + " (amount: " + getAmount() + ")";
    }

}
