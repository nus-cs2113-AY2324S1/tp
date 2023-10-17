package seedu.financialplanner.list;

public class Income extends Cashflow{
    public Income(double value, String type, int recur) {
        super(value, type, recur);
        addIncomeValue(value);
    }

    private void addIncomeValue(double value) {
        balance += value;
    }

    @Override
    public String formatString() {
        return "I | " + super.formatString();
    }

    @Override
    public String toString() {
        return "Income | " + super.toString();
    }
}
