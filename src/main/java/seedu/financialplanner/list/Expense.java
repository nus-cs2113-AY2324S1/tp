package seedu.financialplanner.list;

public class Expense extends Cashflow {
    public Expense(double amount, String type, int recur) {
        super(amount, type, recur);
        addExpenseValue();
    }

    private void addExpenseValue() {
        balance -= this.amount;
    }

    @Override
    public void deleteCashflowvalue() {
        balance += this.amount;
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
