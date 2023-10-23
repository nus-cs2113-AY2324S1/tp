package cashleh.transaction;

import java.time.LocalDate;

/**
 * Abstract class providing common structure for financial transactions,
 * including a description, an amount, and an optional date.
 */
public abstract class Transaction {

    private String description;
    private double amount;
    private LocalDate date = null;
    private Categories category = null;

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

    /*
     * Constructor for Transaction
     * @param description Description of the transaction
     * @param amount Amount of the transaction
     * @param category Category of the transaction
     */
    public Transaction(String description, double amount, Categories category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    /*
     * Constructor for Transaction
     * @param description Description of the transaction
     * @param amount Amount of the transaction
     * @param date Date of the transaction
     * @param category Category of the transaction
     */
    public Transaction(String description, double amount, LocalDate date, Categories category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
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

    public Categories getCategory() {
        return category;
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

    public void setCategory(Categories category) {
        this.category = category;
    }
    
    public abstract String toString();
}
