package cashleh.commands;

import Exceptions.CashLehException;
import cashleh.Income;
import cashleh.IncomeStatement;
import cashleh.Expense;
import cashleh.ExpenseStatement;

public class Command {
    private int index;
    public Command(int index) {
        this.index = index;
    }
    public Command() {
    }
    public int getIndex() {
        return this.index;
    }
//    public Income getIncome() {
//        return incomeStatement.get(getIndex());
//    }
//
//    public Expense getExpense() {
//        return expenseStatement.getExpense(getIndex());
//    }

//    public IncomeStatement getIncomeStatement() {
//        return incomeStatement;
//    }
//    public ExpenseStatement getExpenseStatement() {
//        return expenseStatement;
//    }
//    public double getIncomeSum() {
//        return incomeStatement.getSumOfEntries();
//    }

    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) throws CashLehException {}
//    public void setIncomeStatement(IncomeStatement incomeStatement) {
//        this.incomeStatement = incomeStatement;
//    }
//    public void setExpenseStatement(ExpenseStatement expenseStatement) {
//        this.expenseStatement = expenseStatement;
//    }
}
