package seedu.cafectrl.command;

import seedu.cafectrl.Order;
import seedu.cafectrl.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add_order";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add dish to menu\n"
            + "Parameters: DISH_NAME, PRICE, INGREDIENT1_NAME, INGREDIENT1_QTY "
            + "[,INGREDIENT1_NAME, INGREDIENT1_QTY...] \n"
            + "Example: " + COMMAND_WORD
            + " name/chicken rice price/3.00 ingredient/rice qty/1 cup, ingredient/chicken qty/100g";
    Order order;
    public AddOrderCommand(Order order) {
        this.order  = order;
    }
    /*@Override
    public void execute(OrderList orderList, Ui ui) {
        orderList.addOrder(order);
        ui.printAddDishMessage(dish);
    }*/
    @Override
    public void execute(Menu menu, Ui ui) {

    }
}
