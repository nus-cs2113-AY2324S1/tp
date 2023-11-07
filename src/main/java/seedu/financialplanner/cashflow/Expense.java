package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.time.LocalDate;

/**
 * A cashflow object that represents an expense.
 */
public class Expense extends Cashflow {
    protected ExpenseType type;

    /**
     * Constructor for an expense.
     *
     * @param amount The value of the expense.
     * @param type The type of the expense, using the values in the enum of ExpenseType.
     * @param recur The number of days before the next automatic addition of the expense.
     * @param description The description of the expense.
     * @throws FinancialPlannerException if the balance exceeds the minimum value of -999,999,999,999.99.
     */
    public Expense(double amount, ExpenseType type, int recur, String description) throws FinancialPlannerException {
        super(amount, recur, description);
        this.type = type;
        addExpenseValue();
    }

    /**
     * Constructor for an expense.
     *
     * @param amount The value of the expense.
     * @param type The type of the expense, using the values in the enum of ExpenseType.
     * @param recur The number of days before the next automatic addition of the expense.
     * @param description The description of the expense.
     * @param date The date that the expense is added.
     * @param hasRecurred Whether the expense has recurred.
     * @throws FinancialPlannerException if the balance exceeds the minimum value of -999,999,999,999.99.
     */
    public Expense(double amount, ExpenseType type, int recur,
                   String description, LocalDate date, boolean hasRecurred) throws FinancialPlannerException {
        super(amount, recur, description, date, hasRecurred);
        this.type = type;
        addExpenseValue();
    }

    /**
     * Constructor for an expense.
     *
     * @param expense An expense object to be copied.
     * @throws FinancialPlannerException if the balance exceeds the minimum value of -999,999,999,999.99.
     */
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

    /**
     * Deletes the value of an expense from the balance.
     */
    @Override
    public void deleteCashflowValue() {
        balance += this.amount;
    }

    /**
     * Formats the expense into an easy-to-read format to be output to the user.
     *
     * @return The formatted expense.
     */
    @Override
    public String toString() {
        return "Expense" + System.lineSeparator() +
                "   Type: " + capitalize(type.toString().toLowerCase()) + System.lineSeparator() + super.toString();
    }

    /**
     * Formats the expense into a standard format to be saved into a text file.
     *
     * @return The formatted expense.
     */
    @Override
    public String formatString() {
        return "E | " + this.amount + " | " + this.type + super.formatString();
    }

}
