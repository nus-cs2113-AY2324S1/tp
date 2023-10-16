package seedu.duke.command;

import seedu.duke.data.Menu;
import seedu.duke.ui.Ui;

/**
 * Represents an executable command.
 */
public class Command {
    /**
     * Executes the command and returns the result.
     */
    public void execute(Menu menu, Ui ui) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };
}
