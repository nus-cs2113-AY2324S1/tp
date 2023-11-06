package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.time.LocalDate;

public class Income extends Cashflow{
    protected IncomeType type;

    public Income(double amount, IncomeType type, int recur, String description) throws FinancialPlannerException {
        super(amount, recur, description);
        this.type = type;
        addIncomeValue();
    }

    public Income(double amount, IncomeType type, int recur, String description, LocalDate date, boolean hasRecurred)
            throws FinancialPlannerException {
        super(amount, recur, description, date, hasRecurred);
        this.type = type;
        addIncomeValue();
    }

    public Income(Income income) throws FinancialPlannerException {
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

    private void addIncomeValue() throws FinancialPlannerException {
        double tempBalance = balance + this.amount;

        if (tempBalance > MAX_AMOUNT) {
            throw new FinancialPlannerException("Balance exceeded maximum value this program can hold." +
                    " Please add a different income.");
        }

        balance = tempBalance;
        incomeBalance += this.amount;
    }

    @Override
    public void deleteCashflowValue() {
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
