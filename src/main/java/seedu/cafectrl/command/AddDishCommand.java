package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;
//@@author DextheChik3n
/**
 * Adds a menu item to the user
 */
public class AddDishCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "To add a new dish to the menu: \n"
            + COMMAND_WORD + " name/DISH_NAME price/DISH_PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY"
            + "[, ingredient/INGREDIENT2_NAME, qty/INGREDIENT2_QTY...]\n"
            + "Example:"
            + COMMAND_WORD + " name/chicken rice price/3.00 ingredient/rice qty/200g, ingredient/chicken qty/100g";
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    protected Menu menu;
    protected Ui ui;

    private final Dish dish;
    public AddDishCommand(Dish dish, Menu menu, Ui ui) {
        this.dish = dish;
        this.menu = menu;
        this.ui = ui;
    }
    @Override
    public void execute() {
        logger.info("Executing AddDishCommand...");
        menu.addDish(dish);
        ui.printAddDishMessage(dish);
    }
}
