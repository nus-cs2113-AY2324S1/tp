package seedu.financialplanner.list;

public class Income extends Cashflow{
    public Income(double value, String type, int recur) {
        super(value, type, recur);
        addIncome(value);
    }

    private void addIncome(double value) {
        balance += value;
    }
}
