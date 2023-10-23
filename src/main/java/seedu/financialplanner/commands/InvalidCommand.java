package seedu.financialplanner.commands;

import seedu.financialplanner.utils.Ui;

public class InvalidCommand extends Command {
    public InvalidCommand() {
    }

    @Override
    public void execute() {
        Ui.getInstance().showMessage("Unknown command. Please try again.");
    }
}
