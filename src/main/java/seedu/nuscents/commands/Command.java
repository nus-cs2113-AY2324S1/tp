package seedu.nuscents.commands;

import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.exception.NuscentsException;

public abstract class Command {
    /**
     * Executes the command.
     */
    public abstract void execute(TransactionList transactionList) throws NuscentsException;
}
