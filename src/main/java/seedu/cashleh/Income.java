package seedu.cashleh;

import java.time.LocalDate;

public class Income {
    protected int amount;
    protected int totalIncome = 0; // sum of all incomes
    protected String description;
    protected String currency; // SGD by default
    protected LocalDate date;
    protected boolean isOneTimeIncome; // true by default
    public Income(int amount, String description, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.currency = "S$";
        this.isOneTimeIncome = true;
        this.totalIncome += amount;
    }
    public int getAmount() {
        return this.amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getTotalIncome() {
        return this.totalIncome;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String isOneTimeIncome() {
        return (isOneTimeIncome ? "One time" : "Recurring");
    }
    public void setOneTimeIncome(boolean isOneTimeIncome) {
        this.isOneTimeIncome = isOneTimeIncome;
    }
    public String getCurrency() {
        return this.currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    @Override
    public String toString() {
        return String.format("\t+ %s %d: %s (date: %s, %s)", currency, amount, description, date, isOneTimeIncome());
    }
}
