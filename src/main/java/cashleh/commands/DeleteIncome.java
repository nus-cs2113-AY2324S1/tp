package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.ExpenseStatement;
import cashleh.IncomeStatement;
import cashleh.Ui;

import java.util.logging.Level;

public class DeleteIncome extends Command {
    private final int incomeIndex;
    private final Ui ui = new Ui();

    public DeleteIncome(int incomeIndex) {
        this.incomeIndex = incomeIndex;
    }
    @Override
    public void execute(
        ExpenseStatement expenseStatement,
        IncomeStatement incomeStatement
    ) throws CashLehMissingTransactionException {
        try {
            int numberOfEntriesBeforeDeletion = incomeStatement.getNumberOfEntries();
            String incomeBeingDeleted = incomeStatement.getIncome(incomeIndex - 1).toString();
            incomeStatement.deleteIncome(incomeIndex - 1);
            int numberOfEntriesAfterDeletion = incomeStatement.getNumberOfEntries();
            assert numberOfEntriesBeforeDeletion == numberOfEntriesAfterDeletion + 1;
            ui.printMultipleText(new String[] {
                "Noted! CashLeh has removed the following income:",
                incomeBeingDeleted
            });
            logger.log(Level.INFO, "income entry was successfully deleted");
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }
    }
}
