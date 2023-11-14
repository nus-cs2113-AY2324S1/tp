package seedu.nuscents.commands;

import seedu.nuscents.data.TransactionList;
import seedu.nuscents.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TransactionList tasks) {
        Ui.showGoodbyeMessage();
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
