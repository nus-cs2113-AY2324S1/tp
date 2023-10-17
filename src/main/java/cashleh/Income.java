package cashleh;

public class Income {
    protected static double totalIncome = 0; // sum of all incomes
    protected double amount;
    protected String description;
    protected String currency; // SGD by default
    protected boolean isOneTimeIncome; // true by default
    public Income(String description, double amount) {
        this.amount = amount;
        this.description = description;
        this.currency = "S$";
        this.isOneTimeIncome = true;
        totalIncome += amount;
    }
    public double getAmount() {
        assert this.amount >= 0;
        return this.amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public boolean isOneTimeIncome() {
        return this.isOneTimeIncome;
    }
    public boolean setOneTimeIncome(boolean isOneTimeIncome) {
        return this.isOneTimeIncome = isOneTimeIncome;
    }
    public double getTotalIncome() {
        assert totalIncome >= 0;
        return totalIncome;
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
