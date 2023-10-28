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

    protected Ui ui;
    protected Pantry pantry;
    protected OrderList orderList;

    Order order;
    public AddOrderCommand(Order order, Ui ui, Pantry pantry, OrderList orderList) {
        this.order  = order;
        this.ui = ui;
        this.pantry = pantry;
        this.orderList = orderList;
    }
    @Override
    public void execute() {
        orderList.addOrder(order); //To be implemented for finance tracking
        Chef chef = new Chef(order, pantry, ui);
        chef.cookDish();
        //pantry.printPantryStock();
        if (order.isComplete()) {
            orderList.addCost(order);
        }
        ui.showTotalCost(dollarValue.format(orderList.getTotalOrderListCost()));
    }
}
