package cashleh.commands;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.transaction.IncomeStatement;
import cashleh.Ui;

import java.util.logging.Level;

public class DeleteIncome extends Command {
    private final int incomeIndex;
    private final IncomeStatement incomeStatement;
    public DeleteIncome(int incomeIndex, IncomeStatement incomeStatement) {
        this.incomeIndex = incomeIndex;
        this.incomeStatement = incomeStatement;
    }
    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            int numberOfEntriesBeforeDeletion = incomeStatement.getNumberOfIncomes();
            String incomeBeingDeleted = incomeStatement.getIncome(incomeIndex - 1).toString();
            incomeStatement.deleteIncome(incomeIndex - 1);
            int numberOfEntriesAfterDeletion = incomeStatement.getNumberOfIncomes();
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
