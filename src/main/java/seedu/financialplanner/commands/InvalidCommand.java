package seedu.financialplanner.commands;

import seedu.financialplanner.utils.Ui;

public class InvalidCommand extends AbstractCommand {
    public InvalidCommand() {
    }

    @Override
    public void execute() {
        Ui.getInstance().showMessage("Unknown command. Please try again.");
    }
}
