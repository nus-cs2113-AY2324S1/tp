package seedu.financialplanner.list;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;

public class Expense extends Cashflow {
    protected ExpenseType type;

    public Expense(double amount, ExpenseType type, int recur) {
        super(amount, recur);
        this.type = type;
        addExpenseValue();
    }

    @Override
    public ExpenseType getExpenseType() {
        return type;
    }

    @Override
    public IncomeType getIncomeType() {
        return null;
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
        return "Expense" + System.lineSeparator() +
                "   Type: " + capitalize(type.toString().toLowerCase()) + System.lineSeparator() + super.toString();
    }

    @Override
    public String formatString() {
        return "E | " + this.amount + " | " + this.type + super.formatString();
    }

}
