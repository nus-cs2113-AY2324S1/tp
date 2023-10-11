package cashleh;
public class Expense {
    private String description;
    private double amount;
    private static double totalExpense = 0;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
        totalExpense += amount;
    }
    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public static double getTotalExpense() {
        return totalExpense;
    }

    public String toString() {
        return description + " (amount: " + amount + ")";
    }

}
