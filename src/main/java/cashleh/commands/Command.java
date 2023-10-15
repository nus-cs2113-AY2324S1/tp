package cashleh.commands;

import cashleh.Income;
import cashleh.IncomeStatement;
import cashleh.Expense;
import cashleh.ExpenseStatement;

import java.util.logging.Logger;

public class Command {
    protected static Logger logger = Logger.getLogger("CommandLogger");
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

    public void execute() {}
    public void setIncomeStatement(IncomeStatement incomeStatement) {
        this.incomeStatement = incomeStatement;
    }
    public void setExpenseStatement(ExpenseStatement expenseStatement) {
        this.expenseStatement = expenseStatement;
    }
}
