package seedu.financialplanner.cashflow;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.time.LocalDate;

/**
 * A cashflow object that represents an income.
 */
public class Income extends Cashflow{
    protected IncomeType type;

    /**
     * Constructor for an income.
     *
     * @param amount The value of the income.
     * @param type The type of the income, using the values in the enum of IncomeType.
     * @param recur The number of days before the next automatic addition of the income.
     * @param description The description of the income.
     * @throws FinancialPlannerException if the balance exceeds the maximum value of 999,999,999,999.99.
     */
    public Income(double amount, IncomeType type, int recur, String description) throws FinancialPlannerException {
        super(amount, recur, description);
        this.type = type;
        addIncomeValue();
    }

    /**
     * Constructor for an income
     *
     * @param amount The value of the income.
     * @param type The type of the income, using the values in the enum of IncomeType.
     * @param recur The number of days before the next automatic addition of the income.
     * @param description The description of the income.
     * @param date The date that the income is added.
     * @param hasRecurred Whether the income has recurred.
     * @throws FinancialPlannerException if the balance exceeds the maximum value of 999,999,999,999.99.
     */
    public Income(double amount, IncomeType type, int recur, String description, LocalDate date, boolean hasRecurred)
            throws FinancialPlannerException {
        super(amount, recur, description, date, hasRecurred);
        this.type = type;
        addIncomeValue();
    }

    /**
     * Constructor for an income.
     *
     * @param income An income object to be copied.
     * @throws FinancialPlannerException if the balance exceeds the maximum value of 999,999,999,999.99.
     */
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

    /**
     * Deletes the value of an income from the balance.
     */
    @Override
    public void deleteCashflowValue() {
        balance -= this.amount;
    }

    /**
     * Formats the income into an easy-to-read format to be output to the user.
     *
     * @return The formatted income.
     */
    @Override
    public String toString() {
        return "Income" + System.lineSeparator() +
                "   Type: " + capitalize(type.toString().toLowerCase()) + System.lineSeparator() + super.toString();
    }

    /**
     * Formats the income into a standard format to be saved into a text file.
     *
     * @return The formatted income.
     */
    @Override
    public String formatString() {
        return "I | " + this.amount + " | " + this.type + super.formatString();
    }

}
