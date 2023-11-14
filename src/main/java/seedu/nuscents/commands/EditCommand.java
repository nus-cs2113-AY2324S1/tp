package seedu.nuscents.commands;

import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.transaction.Transaction;
import seedu.nuscents.data.exception.NuscentsException;
import seedu.nuscents.ui.Ui;

public class EditCommand extends Command {
    private int index;
    private Transaction transaction;

    public EditCommand(Transaction transaction, int index) {
        this.transaction = transaction;
        this.index = index;
    }

    @Override
    public void execute(TransactionList transactionList) throws NuscentsException {
        transactionList.editTransaction(this.index, this.transaction);
        Ui.showTransactionList(transactionList);
    }
}
