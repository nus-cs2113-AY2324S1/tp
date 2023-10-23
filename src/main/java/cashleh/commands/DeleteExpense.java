package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.transaction.ExpenseStatement;
import cashleh.Ui;

import java.util.logging.Level;
/**
 * This class extends the Command class and is
 * used to encapsulate the action of deleting an expense from the application's expense statement.
 * When executed, it removes the specified expense, logs the operation, and prints a confirmation message.
 */
public class DeleteExpense extends Command {
    private final int expenseIndex;
    private final ExpenseStatement expenseStatement;
    public DeleteExpense(int expenseIndex, ExpenseStatement expenseStatement) {
        this.expenseIndex = expenseIndex;
        this.expenseStatement = expenseStatement;
    }

    /**
     * Executes the command to delete the specified expense from the expense statement,
     * logs the operation, and prints a confirmation message.
     * @throws CashLehMissingTransactionException If the specified expense is not found.
     */
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            int numberOfEntriesBeforeDeletion = expenseStatement.getNumberOfExpenses();
            String expenseBeingDeleted = expenseStatement.getExpense(expenseIndex - 1).toString();
            expenseStatement.deleteExpense(expenseIndex - 1);
            int numberOfEntriesAfterDeletion = expenseStatement.getNumberOfExpenses();
            assert numberOfEntriesBeforeDeletion == numberOfEntriesAfterDeletion + 1;
            Ui.printMultipleText(new String[] {
                "Noted! CashLeh has removed the following expense:",
                expenseBeingDeleted
            });
            logger.log(Level.INFO, "expense entry was successfully deleted");
            Ui.printHorizontalLine();
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
