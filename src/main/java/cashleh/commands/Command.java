package cashleh.commands;

import cashleh.IncomeStatement;
import cashleh.ExpenseStatement;
import cashleh.exceptions.CashLehException;

import java.util.logging.Logger;

public class Command {
    protected static Logger logger = Logger.getLogger("CommandLogger");
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
