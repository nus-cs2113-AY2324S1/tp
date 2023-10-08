package seedu.financialplanner.list;

public class Expense extends Cashflow {
    public Expense(double value, String type, int recur) {
        super(value, type, recur);
        addIncomeValue(value);
    }

    private void addIncomeValue(double value) {
        balance -= value;
    }
}
