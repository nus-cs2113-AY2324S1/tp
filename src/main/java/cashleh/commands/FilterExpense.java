package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.ExpenseStatement;

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
            expenseStatement.findExpense(expenseToFind.getDescription(), expenseToFind.getAmount());
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }


}
