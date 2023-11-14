package seedu.nuscents.commands;

import seedu.nuscents.data.TransactionList;
import seedu.nuscents.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TransactionList tasks) {
        Ui.showTransactionList(tasks);
        Ui.showBudgetExpense(tasks);
    }
}
