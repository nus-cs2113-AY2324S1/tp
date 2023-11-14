package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Chef;

import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;
import java.text.DecimalFormat;

public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add_order";
    public static final String MESSAGE_USAGE = "To add a new order: \n"
            + COMMAND_WORD
            + " name/DISH_NAME qty/QUANTITY\n"
            + "Example: " + COMMAND_WORD
            + "name/chicken rice qty/2";
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    protected Pantry pantry;
    protected OrderList orderList;
    protected Menu menu;
    private final Ui ui;
    private final Order order;
    private final DecimalFormat dollarValue = new DecimalFormat("0.00");

    public AddOrderCommand(Order order, Ui ui, Pantry pantry, OrderList orderList, Menu menu) {
        this.order  = order;
        this.ui = ui;
        this.pantry = pantry;
        this.orderList = orderList;
        this.menu = menu;
    }
    @Override
    public void execute() {
        logger.info("Executing AddOrderCommand...");
        orderList.addOrder(order);
        Chef chef = new Chef(order, pantry, ui);
        chef.cookDish();
        if (order.getIsComplete()) {
            orderList.addCost(order);
            String totalCost = dollarValue.format(order.getTotalOrderCost());
            ui.showOrderStatus(totalCost);
            pantry.calculateDishAvailability(menu, order);
        } else {
            //pass in dish only and not entire menu
            Dish orderedDish = order.getOrderedDish();
            pantry.calculateMaxDishes(orderedDish, menu, order);
            ui.showIncompleteOrder();
        }

    }

}
