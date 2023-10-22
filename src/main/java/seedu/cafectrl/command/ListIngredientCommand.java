package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

/**
 * Lists all ingredients used in the selected dish to the user.
 */
public class ListIngredientCommand extends Command {
    public static final String COMMAND_WORD = "list_ingredients";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists out the ingredients needed along with the quantity for a specific dish.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    public ListIngredientCommand(int listIndex) {
        this.index = listIndex;
    }

    @Override
    public void execute(Menu menu, Ui ui) {
        try {
            Dish selectedDish = menu.getMenuItemsList().get(index - Ui.OFFSET_LIST_INDEX);
            ui.printIngredients(selectedDish);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(Messages.INVALID_DISH_INDEX);
        }
    }
}
