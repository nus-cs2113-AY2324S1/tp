package seedu.nuscents.data.transaction;

import java.util.Date;

public class Allowance extends Transaction {
    public Allowance(float amount, Date date, String description, String additionalInfo, AllowanceCategory category) {
        super(amount, date, description, additionalInfo, category);
    }
}
