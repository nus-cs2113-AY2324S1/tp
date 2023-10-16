package cashleh.commands;

import exceptions.CashLehException;
import cashleh.IncomeStatement;
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

    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) throws CashLehException {}
}
