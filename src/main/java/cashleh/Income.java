package cashleh;

public class Income {
    protected static double totalIncome = 0.0; // sum of all incomes
    protected double amount;
    protected String description;
    public Income(String description, double amount) {
        this.amount = amount;
        this.description = description;
        totalIncome += amount;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return description + " (amount: " + amount + ")";
    }
}
