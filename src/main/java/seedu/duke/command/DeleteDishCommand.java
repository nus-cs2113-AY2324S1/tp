package seedu.duke.command;

import seedu.duke.data.Menu;
import seedu.duke.ui.Ui;

/**
 * Deletes a menu item identified using it's last displayed index from the menu.
 */
public class DeleteDishCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the menu item identified by the index number used in the last menu listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    @Override
    public void execute(Menu menu, Ui ui) {

    };
}
