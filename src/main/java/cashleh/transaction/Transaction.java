package cashleh.transaction;

import cashleh.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class providing common structure for financial transactions,
 * including a description, an amount, and an optional date.
 */
public abstract class Transaction {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String description;
    private double amount;
    private LocalDate date = null;
    private Categories category = null;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
        this.date = LocalDate.now();
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

    /*
     * Returns the amount of the transaction
     * @return Amount of the transaction
     */
    public double getAmount() {
        return amount;
    }

    /*
     * Returns the description of the transaction
     * @return Description of the transaction
     */
    public String getDescription() {
        return description;
    }

    /*
     * Returns the date of the transaction
     * @return Date of the transaction
     */
    public LocalDate getDate() {
        return date;
    }

    /*
     * Returns the string representation of the date of the transaction in the format dd/MM/yyyy
     * @return String representation of the date of the transaction in the format dd/MM/yyyy
     */
    public String getDateString() {
        return Ui.getDateString(date);
    }

    /*
     * Returns the category of the transaction
     * @return Category of the transaction
     */
    public Categories getCategory() {
        return category;
    }

    /*
     * Sets the amount of the transaction
     * @param amount Amount of the transaction
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /*
     * Sets the description of the transaction
     * @param description Description of the transaction
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * Sets the date of the transaction
     * @param date Date of the transaction
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /*
     * Sets the category of the transaction
     * @param category Category of the transaction
     */
    public void setCategory(Categories category) {
        this.category = category;
    }

    /*
     * Returns the string representation of the transaction
     * @return String representation of the transaction
     */
    public abstract String toString();
}
