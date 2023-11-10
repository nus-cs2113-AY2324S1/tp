package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author ShaniceTang
/**
 * Deletes a menu item identified using it's last displayed index from the menu.
 */
public class DeleteDishCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "To delete a menu item:\n"
            + COMMAND_WORD
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    protected Menu menu;
    protected Ui ui;

    public DeleteDishCommand(int listIndex, Menu menu, Ui ui) {
        this.index = listIndex;
        this.menu = menu;
        this.ui = ui;
    }

    @Override
    public void execute() {
        logger.info("Executing DeleteDishCommand...");
        try {
            int dishIndexToBeDeleted = index - Ui.OFFSET_LIST_INDEX;
            Dish selectedDish = menu.getMenuItemsList().get(dishIndexToBeDeleted);
            ui.printDeleteMessage(selectedDish);
            menu.removeDish(dishIndexToBeDeleted);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "DeleteDishCommand unsuccessful: " + e.getMessage(), e);
            ui.showToUser(ErrorMessages.INVALID_DISH_INDEX);
            throw new IndexOutOfBoundsException();
        }
    }
}
