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

    public Income(double amount, IncomeType type, int recur, String description, LocalDate date, boolean hasRecurred) {
        super(amount, recur, description, date, hasRecurred);
        this.type = type;
        addIncomeValue();
    }

    public Income(Income income) {
        this.amount = income.getAmount();
        this.recur = income.getRecur();
        this.description = income.getDescription();
        this.date = income.getDate();
        this.type = income.getIncomeType();
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
