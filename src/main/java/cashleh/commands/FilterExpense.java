package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.parser.FindParser;
import cashleh.transaction.ExpenseStatement;

import java.util.logging.Level;

/**
 * The FilterExpense class represents a command to filter expenses in the CashLeh application.
 * It allows users to filter expense transactions based on various criteria such as description,
 * amount, date, and category.
 */
public class FilterExpense extends Command {
    private final FindParser expenseToFind;
    private final ExpenseStatement expenseStatement;

    /**
     * Constructs a new FilterExpense command with the specified filtering criteria and the expenseStatement.
     * @param expenseToFind The criteria used for filtering expenses.
     * @param expenseStatement The expense statement to filter expenses from.
     */
    public FilterExpense(FindParser expenseToFind, ExpenseStatement expenseStatement) {
        this.expenseToFind = expenseToFind;
        this.expenseStatement = expenseStatement;
    }

    /**
     * Executes the filtering operation to find and display matching expense transactions.
     * @throws CashLehMissingTransactionException if no matching transactions are found.
     */
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            assert expenseStatement != null;
            expenseStatement.findExpense(expenseToFind.getDescription(), expenseToFind.getAmount(),
                    expenseToFind.getDate(), expenseToFind.getCategory());
            logger.log(Level.INFO, "expense entry was successfully filtered");
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }


}
