package seedu.nuscents.commands;

import seedu.nuscents.data.TransactionList;

public class ViewCommand extends Command {
    private int taskIndex;

    public ViewCommand (int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TransactionList tasks) {
        tasks.viewTransaction(taskIndex);
    }
}
