package seedu.financialplanner.list;

public class Income extends Cashflow{
    public Income(double value, String type, int recur) {
        super(value, type, recur);
        addIncomeValue(value);
    }

    private void addIncomeValue(double value) {
        balance += value;
    }
}
