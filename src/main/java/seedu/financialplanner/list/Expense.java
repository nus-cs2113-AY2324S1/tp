package seedu.financialplanner.list;

public class Expense extends Cashflow {
    public Expense(double value, String type, int recur) {
        super(value, type, recur);
        addIncomeValue(value);
    }

    private void addIncomeValue(double value) {
        balance -= value;
    }

    @Override
    public String formatString() {
        return "E | " + super.formatString();
    }

    //temp
    @Override
    public String toString() {
        return "Expense: " + super.toString();
    }
}
