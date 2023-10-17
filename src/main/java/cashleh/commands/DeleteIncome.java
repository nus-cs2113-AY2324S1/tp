package cashleh.commands;

import cashleh.exception.CashLehException;

import java.util.logging.Level;

public class DeleteIncome extends Command {
    public static final String COMMAND = "deleteIncome";
    public DeleteIncome(int incomeIndexToDelete) throws CashLehException {
        super(incomeIndexToDelete);
    }
    @Override
    public void execute() throws CashLehException {
        int numberOfEntriesBeforeDeletion = incomeStatement.getNumberOfEntries();
        String incomeBeingDeleted = getIncome().toString();
        incomeStatement.delete(getIndex());
        int numberOfEntriesAfterDeletion = incomeStatement.getNumberOfEntries();
        assert numberOfEntriesBeforeDeletion == numberOfEntriesAfterDeletion + 1;
        System.out.println("The following income was deleted:\n" + incomeBeingDeleted);
        logger.log(Level.INFO, "income entry was successfully deleted");
    }
}
