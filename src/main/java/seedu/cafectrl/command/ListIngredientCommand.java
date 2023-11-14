package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lists all ingredients used in the selected dish to the user.
 */
public class ListIngredientCommand extends Command {
    public static final String COMMAND_WORD = "list_ingredients";
    public static final String MESSAGE_USAGE = "To list out the ingredients needed "
            + "along with the quantity for a specific dish:\n"
            + "Parameters: dish/DISH_INDEX\n"
            + "Example: " + COMMAND_WORD + " dish/1";

    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    protected Ui ui;
    protected Menu menu;

    public ListIngredientCommand(int listIndex, Menu menu, Ui ui) {
        this.index = listIndex;
        this.menu = menu;
        this.ui = ui;
    }

    @Override
    public void execute() {
        logger.info("Executing ListIngredientCommand...");
        try {
            Dish selectedDish = menu.getMenuItemsList().get(index - Ui.OFFSET_LIST_INDEX);
            ui.showListIngredientsMessage(selectedDish);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "ListIngredientCommand unsuccessful: " + e.getMessage(), e);
            throw new IllegalArgumentException(ErrorMessages.UNLISTED_DISH);
        }
    }

}
