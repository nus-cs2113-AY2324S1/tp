package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Chef;

import seedu.cafectrl.ui.Ui;

public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add_order";
    public static final String MESSAGE_USAGE = "To add a new order: \n"
            + COMMAND_WORD
            + " name/DISH_NAME qty/QUANTITY\n"
            + "Example: " + COMMAND_WORD
            + "name/chicken rice qty/2";

    protected Pantry pantry;
    protected OrderList orderList;
    protected Menu menu;
    private final Ui ui;
    private final Order order;

    public AddOrderCommand(Order order, Ui ui, Pantry pantry, OrderList orderList, Menu menu) {
        this.order  = order;
        this.ui = ui;
        this.pantry = pantry;
        this.orderList = orderList;
        this.menu = menu;
    }
    @Override
    public void execute() {
        orderList.addOrder(order);
        Chef chef = new Chef(order, pantry, ui, menu);
        chef.cookDish();
        //pantry.printPantryStock();
        if (order.getIsComplete()) {
            orderList.addCost(order);
        }

    }
}
