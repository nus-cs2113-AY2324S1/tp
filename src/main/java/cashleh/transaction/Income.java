package cashleh.transaction;

public class Income extends Transaction {

    public Income(String description, double amount) {
        super(description, amount);
    }
    @Override
    public String toString() {
        return getDescription() + " (amount: " + getAmount() + ")";
    }
}
