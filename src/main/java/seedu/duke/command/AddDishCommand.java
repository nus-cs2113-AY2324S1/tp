package seedu.duke.command;

import seedu.duke.data.Menu;
import seedu.duke.data.dish.Dish;
import seedu.duke.ui.Ui;

/**
 * Adds a menu item to the user
 */
public class AddDishCommand extends Command {
    public static final String COMMAND_WORD = "add";
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
