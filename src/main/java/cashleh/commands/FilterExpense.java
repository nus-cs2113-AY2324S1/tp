package cashleh.commands;

import cashleh.Ui;
import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.ExpenseStatement;

import java.util.logging.Level;

public class FilterExpense extends Command {
    private final FindParser expenseToFind;
    private final ExpenseStatement expenseStatement;

    public FilterExpense(FindParser expenseToFind, ExpenseStatement expenseStatement) {
        this.expenseToFind = expenseToFind;
        this.expenseStatement = expenseStatement;
    }
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            expenseStatement.findExpense(expenseToFind.getDescription(), expenseToFind.getAmount(), expenseToFind.getDate());
            logger.log(Level.INFO, "expense entry was successfully filtered");
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }


}
