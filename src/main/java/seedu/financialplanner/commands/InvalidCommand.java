package seedu.financialplanner.commands;

import seedu.financialplanner.utils.Ui;

/**
 * Represents a command for an invalid input.
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {
    }

    /**
     * Executes the command to display an error message when an
     * invalid input is received.
     */
    @Override
    public void execute() {
        Ui.getInstance().showMessage("Unknown command. Please try again.");
    }
}
