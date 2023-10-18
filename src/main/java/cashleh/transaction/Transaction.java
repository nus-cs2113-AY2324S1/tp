package cashleh.transaction;

import java.time.LocalDate;

public abstract class Transaction {

    private String description;
    private double amount;

    private LocalDate date;

    /*
     * Constructor for Transaction - set date to current date if not specified
     * @param description Description of the transaction
     * @param amount Amount of the transaction
     */
    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    /*
     * Constructor for Transaction
     * @param description Description of the transaction
     * @param amount Amount of the transaction
     * @param date Date of the transaction
     */
    public Transaction(String description, double amount, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public abstract String toString();
}
