package seedu.financialplanner.list;

public class Budget extends Cashflow {
    private static final int MONTH = 30;
    public Budget(double value) {
        super(value, null, MONTH);
    }

    //add storage for budget
}
