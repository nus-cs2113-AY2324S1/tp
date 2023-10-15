package seedu.financialplanner.list;

public class Expense extends Cashflow {
    public Expense(double amount, String type, int recur) {
        super(amount, type, recur);
        addIncomeValue(amount);
    }

    private void addIncomeValue(double value) {
        balance -= value;
    }

    @Override
    public String toString() {
        return "Expense" + System.lineSeparator() + super.toString();
    }

    @Override
    public String formatString() {
        return "E | " + super.formatString();
    }
}
