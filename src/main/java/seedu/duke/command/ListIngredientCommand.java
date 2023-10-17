package seedu.duke.command;

import seedu.duke.data.Menu;
import seedu.duke.ui.Ui;

/**
 * Lists all ingredients used in the selected dish to the user.
 */
public class ListIngredientCommand extends Command{
    public static final String COMMAND_WORD = "list_ingredients";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists out the ingredients needed along with the quantity for a specific dish.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    @Override
    public void execute(Menu menu, Ui ui) {
        ui.printIngredients(menu, index);
    };
}
