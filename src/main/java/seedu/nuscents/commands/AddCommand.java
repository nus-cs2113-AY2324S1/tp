package seedu.nuscents.commands;

import seedu.nuscents.data.transaction.Transaction;
import seedu.nuscents.data.TransactionList;

public class AddCommand extends Command {
    private Transaction transaction;

    public AddCommand(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void execute(TransactionList transactions) {
        transactions.addTransaction(transaction);
    }
}
