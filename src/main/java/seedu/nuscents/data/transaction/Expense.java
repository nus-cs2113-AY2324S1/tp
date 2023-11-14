package seedu.nuscents.data.transaction;

import java.util.Date;

public class Expense extends Transaction {

    public Expense(float amount, Date date, String description, String additionalInfo, ExpenseCategory category) {
        super(amount, date, description, additionalInfo, category);
    }
}
