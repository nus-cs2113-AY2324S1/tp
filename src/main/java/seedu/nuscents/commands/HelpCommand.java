package seedu.nuscents.commands;


import seedu.nuscents.data.TransactionList;
import seedu.nuscents.ui.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(TransactionList tasks) {
        Ui.showHelpMenu();
    }
}
