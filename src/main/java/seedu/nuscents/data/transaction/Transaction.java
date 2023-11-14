package seedu.nuscents.data.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Transaction in the TransactionList
 */
public class Transaction {
    protected float amount;
    protected Date date;
    protected String description;
    protected String additionalInfo;
    protected TransactionCategory category;

    public Transaction(String description) {
        this.description = description;
    }

    public Transaction(float amount, Date date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Transaction(float amount, Date date, String description, String additionalInfo,
                       TransactionCategory category) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.additionalInfo = additionalInfo;
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy");
        return formatter.format(date);
    }

    public String getDescription() {
        return description;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public String toString() {
        return getAmount() + " | " + getFormattedDate() + " | " + getDescription() + " | " + getAdditionalInfo()
                + " | " + getCategory();
    }
}
