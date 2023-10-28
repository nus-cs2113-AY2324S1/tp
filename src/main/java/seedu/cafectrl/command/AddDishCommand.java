package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

/**
 * Adds a menu item to the user
 */
public class AddDishCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "Command Format:\n"
            + COMMAND_WORD + " name/DISH_NAME price/DISH_PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY"
            + "[, ingredient/INGREDIENT2_NAME, qty/INGREDIENT2_QTY...]\n"
            + "(Items in square brackets [] are optional)\n"
            + "Example:\n"
            + COMMAND_WORD + " name/chicken rice price/3.00 ingredient/rice qty/200g, ingredient/chicken qty/100g";

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
        menu.addDish(dish);
        ui.printAddDishMessage(dish);
    }
}
