package seedu.financialplanner.list;

public class Budget extends Cashflow {
    private static final int MONTH = 30;
    private static double currentBudget = 0;
    public Budget(double value) {
        super(value, null, MONTH);
    }

    @Override
    public String formatString() {
        return "B | " + this.value;
    }
}
