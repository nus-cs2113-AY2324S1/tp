package seedu.cafectrl.command;

import seedu.cafectrl.Chef;
import seedu.cafectrl.Order;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Ui;

public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add_order";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add order to orders\n"
            + "Parameters: DISH_NAME, DISH_QTY\n"
            + "Example: " + COMMAND_WORD
            + " name/chicken rice qty/2";
    Order order;
    public AddOrderCommand(Order order) {
        this.order  = order;
    }
    @Override
    public void execute(Menu menu, Ui ui, Pantry pantry) {
        Chef chef = new Chef(order, pantry);
        chef.cookDish();
    }
}
