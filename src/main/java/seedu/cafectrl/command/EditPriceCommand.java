package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;

//@@author ziyi105
/**
 * Edit the price of a dish of a certain index
 */
public class EditPriceCommand extends Command {
    public static final String COMMAND_WORD = "edit_price";
    public static final String MESSAGE_USAGE = "To edit price of a menu item: \n"
            + "edit_price dish/DISH_INDEX price/NEW_PRICE\n"
            + "Example: edit_price dish/1 price/4.50";

    private static final Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    protected Menu menu;
    protected Ui ui;
    private final int menuID;
    private final float newDishPrice;

    public EditPriceCommand(int menuID, float newDishPrice, Menu menu, Ui ui) {
        this.menuID = menuID;
        this.newDishPrice = newDishPrice;
        this.menu = menu;
        this.ui = ui;
    }

    //@@author ziyi105
    /**
     * Set new price of the dish and show edit price message
     */
    public void execute() {
        logger.info("Executing EditPriceCommand...");
        Dish dish = menu.getDishFromId(menuID - Ui.OFFSET_LIST_INDEX);

        // Checks for original price
        if (dish.comparePrice(newDishPrice) == 0) {
            ui.showToUser(ErrorMessages.EDIT_SAME_PRICE);
        } else {
            dish.setPrice(newDishPrice);
            ui.showEditPriceMessage(dish.toString());
        }
    }
}
