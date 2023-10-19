package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.transaction.IncomeStatement;
import cashleh.Ui;

import java.util.logging.Level;
/**
 * This class extends the Command class and is used to
 * encapsulate the action of deleting an income from the application's income statement.
 * When executed, it removes the specified income, logs the operation, and prints a confirmation message.
 */
public class DeleteIncome extends Command {
    private final int incomeIndex;
    private final IncomeStatement incomeStatement;
    public DeleteIncome(int incomeIndex, IncomeStatement incomeStatement) {
        this.incomeIndex = incomeIndex;
        this.incomeStatement = incomeStatement;
    }

    /**
     * Executes the command to delete the specified income from the income statement,
     * logs the operation, and prints a confirmation message.
     * @throws CashLehMissingTransactionException If the specified income is not found.
     */
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            int numberOfEntriesBeforeDeletion = incomeStatement.getNumberOfEntries();
            String incomeBeingDeleted = incomeStatement.getIncome(incomeIndex - 1).toString();
            incomeStatement.deleteIncome(incomeIndex - 1);
            int numberOfEntriesAfterDeletion = incomeStatement.getNumberOfEntries();
            assert numberOfEntriesBeforeDeletion == numberOfEntriesAfterDeletion + 1;
            Ui.printMultipleText(new String[] {
                "Noted! CashLeh has removed the following income:",
                incomeBeingDeleted
            });
            logger.log(Level.INFO, "income entry was successfully deleted");
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
