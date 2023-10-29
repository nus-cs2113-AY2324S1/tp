package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

/**
 * Edit the price of a dish of a certain index
 */
public class EditPriceCommand extends Command {
    public static final String COMMAND_WORD = "edit_price";

    protected Menu menu;
    protected Ui ui;
    private final int menuID;
    private final float newPrice;

    public EditPriceCommand(int menuID, float newPrice, Menu menu, Ui ui) {
        this.menuID = menuID;
        this.newPrice = newPrice;
        this.menu = menu;
        this.ui = ui;
    }

    /**
     * Set new price of the dish and show edit price message
     */
    public void execute() {
        Dish dish = menu.getDishFromId(this.menuID - Ui.OFFSET_LIST_INDEX);
        dish.setPrice(this.newPrice);

        ui.showEditPriceMessage(dish.toString());
    }
}
