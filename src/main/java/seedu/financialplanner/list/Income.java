package seedu.financialplanner.list;

public class Income extends Cashflow{
    public Income(double amount, String type, int recur) {
        super(amount, type, recur);
        addIncomeValue(amount);
    }

    private void addIncomeValue(double value) {
        balance += value;
    }
    @Override
    public String toString() {
        return "Income" + System.lineSeparator() + super.toString();
    }
    @Override
    public String formatString() {
        return "I | " + super.formatString();
    }
}
