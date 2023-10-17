package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.transaction.ExpenseStatement;
import cashleh.Ui;

import java.util.logging.Level;

public class DeleteExpense extends Command {
    private final int expenseIndex;
    private final ExpenseStatement expenseStatement;
    public DeleteExpense(int expenseIndex, ExpenseStatement expenseStatement) {
        this.expenseIndex = expenseIndex;
        this.expenseStatement = expenseStatement;
    }
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
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }

    }
}
