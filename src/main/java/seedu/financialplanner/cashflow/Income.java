package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;

import java.time.LocalDate;

public class Income extends Cashflow{
    protected IncomeType type;

    public Income(double amount, IncomeType type, int recur, String description) {
        super(amount, recur, description);
        this.type = type;
        addIncomeValue();
    }

    public Income(double amount, IncomeType type, int recur, String description, LocalDate date) {
        super(amount, recur, description, date);
        this.type = type;
        addIncomeValue();
    }

    @Override
    public IncomeType getIncomeType() {
        return type;
    }

    @Override
    public ExpenseType getExpenseType() {
        return null;
    }

    private void addIncomeValue() {
        balance += this.amount;
    }

    @Override
    public void deleteCashflowvalue() {
        balance -= this.amount;
    }

    @Override
    public String toString() {
        return "Income" + System.lineSeparator() +
                "   Type: " + capitalize(type.toString().toLowerCase()) + System.lineSeparator() + super.toString();
    }

    @Override
    public String formatString() {
        return "I | " + this.amount + " | " + this.type + super.formatString();
    }

}
