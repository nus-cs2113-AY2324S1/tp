package cashleh;

import java.time.LocalDate;

public class Income {
    protected double amount;
    protected static double totalIncome = 0.0; // sum of all incomes
    protected String description;
//    protected String currency; // SGD by default
//    protected LocalDate date;
//    protected boolean isOneTimeIncome; // true by default
    public Income(String description, double amount) {
        this.amount = amount;
        this.description = description;
//        this.date = date;
//        this.currency = "S$";
//        this.isOneTimeIncome = true;
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
//    public LocalDate getDate() {
//        return this.date;
//    }
//    public void setDate(LocalDate date) {
//        this.date = date;
//    }
//    public String isOneTimeIncome() {
//        return (isOneTimeIncome ? "One time" : "Recurring");
//    }
//    public void setOneTimeIncome(boolean isOneTimeIncome) {
//        this.isOneTimeIncome = isOneTimeIncome;
//    }
//    public String getCurrency() {
//        return this.currency;
//    }
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
    @Override
    public String toString() {
        return description + " (amount: " + amount + ")";
    }
}
