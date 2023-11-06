package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.time.LocalDate;

public class Expense extends Cashflow {
    protected ExpenseType type;

    public Expense(double amount, ExpenseType type, int recur, String description) throws FinancialPlannerException {
        super(amount, recur, description);
        this.type = type;
        addExpenseValue();
    }

    public Expense(double amount, ExpenseType type, int recur,
                   String description, LocalDate date, boolean hasRecurred) throws FinancialPlannerException {
        super(amount, recur, description, date, hasRecurred);
        this.type = type;
        addExpenseValue();
    }

    public Expense(Expense expense) throws FinancialPlannerException {
        this.amount = expense.getAmount();
        this.recur = expense.getRecur();
        this.description = expense.getDescription();
        this.date = expense.getDate();
        this.type = expense.getExpenseType();
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

    private void addExpenseValue() throws FinancialPlannerException {
        double tempBalance = balance - this.amount;

        if (tempBalance < -MAX_AMOUNT) {
            throw new FinancialPlannerException("Balance exceeded minimum value this program can hold." +
                    " Please add a different expense.");
        }

        balance = tempBalance;
        expenseBalance += this.amount;
    }

    @Override
    public void deleteCashflowValue() {
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
