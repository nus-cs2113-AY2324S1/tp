package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

/**
 * Adds a menu item to the user
 */
public class AddDishCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add dish to menu\n"
            + "Parameters: DISH_NAME, PRICE, INGREDIENT1_NAME, INGREDIENT1_QTY "
            + "[,INGREDIENT1_NAME, INGREDIENT1_QTY...] \n"
            + "Example: " + COMMAND_WORD
            + " name/chicken rice price/3 ingredient/rice qty/1 cup, ingredient/chicken qty/100g";
    Dish dish;
    public AddDishCommand(Dish dish) {
        this.dish = dish;
    }
    @Override
    public void execute(Menu menu, Ui ui) {
        menu.addDish(dish);
        ui.printAddDishMessage(dish);
    }
}
