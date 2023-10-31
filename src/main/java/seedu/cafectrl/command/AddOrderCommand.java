package seedu.cafectrl.command;

import seedu.cafectrl.data.Chef;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Ui;

import java.text.DecimalFormat;

public class AddOrderCommand extends Command {
    public static final String COMMAND_WORD = "add_order";
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add order to orders\n"
            + "Parameters: DISH_NAME, DISH_QTY\n"
            + "Example: " + COMMAND_WORD
            + " name/chicken rice qty/2";

    protected Pantry pantry;
    protected OrderList orderList;
    private final Ui ui;
    private final Order order;

    public AddOrderCommand(Order order, Ui ui, Pantry pantry, OrderList orderList) {
        this.order  = order;
        this.ui = ui;
        this.pantry = pantry;
        this.orderList = orderList;
    }
    @Override
    public void execute() {
        orderList.addOrder(order);
        Chef chef = new Chef(order, pantry, ui);
        chef.cookDish();
        //pantry.printPantryStock();
        if (order.getIsComplete()) {
            orderList.addCost(order);
        }
        ui.showTotalCost(dollarValue.format(orderList.getTotalOrderListCost()));
        //orderList.printOrderList();
    }
}
