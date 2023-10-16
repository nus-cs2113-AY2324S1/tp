package cashleh.transaction;

public abstract class Transaction {

    private String description;
    private double amount;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract String toString();
}
