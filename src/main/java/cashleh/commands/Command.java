package cashleh.commands;
import cashleh.exception.CashLehException;
import cashleh.*;

public class Command {
    protected IncomeStatement incomeStatement;
    protected ExpenseStatement expenseStatement;
    private int index;
    public Command(int index) {
        this.index = index;
    }
    public Command() {
    }
    public int getIndex() {
        return this.index;
    }
    public Income getIncome() {
        return incomeStatement.get(getIndex());
    }

    public Expense getExpense() {
        return expenseStatement.getExpense(getIndex());
    }

    public IncomeStatement getIncomeStatement() {
        return incomeStatement;
    }
    public ExpenseStatement getExpenseStatement() {
        return expenseStatement;
    }
    public double getIncomeSum() {
        return incomeStatement.getSumOfEntries();
    }

    public void execute() throws CashLehException {}
    public void setIncomeStatement(IncomeStatement incomeStatement) {
        this.incomeStatement = incomeStatement;
    }
    public void setExpenseStatement(ExpenseStatement expenseStatement) {
        this.expenseStatement = expenseStatement;
    }
}
