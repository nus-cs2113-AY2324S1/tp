package seedu.nuscents.commands;


import seedu.nuscents.data.TransactionList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand (int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TransactionList tasks) {
        tasks.deleteTransaction(taskIndex);
    }
}
