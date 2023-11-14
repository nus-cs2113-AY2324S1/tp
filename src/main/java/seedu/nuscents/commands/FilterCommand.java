package seedu.nuscents.commands;

import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.transaction.TransactionCategory;

public class FilterCommand extends Command {
    private TransactionCategory category;

    public FilterCommand(TransactionCategory category) {
        this.category = category;
    }

    @Override
    public void execute(TransactionList transactions) {
        transactions.filterTransaction(category);
    }
}
