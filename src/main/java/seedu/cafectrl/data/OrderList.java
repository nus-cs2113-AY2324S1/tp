package seedu.cafectrl.data;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.ui.Ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The OrderList class represents a list of orders for a specific day.
 * It manages the collection of orders and calculates the total cost for the day.
 */
public class OrderList {
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    private static final String HEADER_FORMAT = "%-20s %-10s %-20s\n";
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    private ArrayList<Order> orderList;
    private float totalOrderListCost;

    /**
     * Constructs an empty OrderList with no orders and zero total order cost.
     */
    public OrderList() {
        this.orderList = new ArrayList<>();
        this.totalOrderListCost = 0;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }
    public int getSize() {
        return orderList.size();
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void addCost(Order order) {
        float orderCost = order.getTotalOrderCost();
        totalOrderListCost += orderCost;
    }

    //@@author NaychiMin
    /**
     * Prints the order list for a specific day, including dish names, quantities, and total cost prices.
     *
     * @param menu The Menu object representing the cafe's menu.
     */
    public void printOrderList(Menu menu, Ui ui) {
        logger.info("Printing order list...");
        ArrayList<Order> aggregatedOrders = menu.getAggregatedOrders();

        for (Order order : orderList) {
            aggregateOrder(order, aggregatedOrders);
        }

        for (Order aggregatedOrder : aggregatedOrders) {
            if (aggregatedOrder.getQuantity() == 0) {
                continue;
            }

            ui.showSalesAll(aggregatedOrder.getDishName(),
                    aggregatedOrder.getQuantity(),
                    dollarValue.format(aggregatedOrder.getTotalOrderCost()));
        }

        ui.showSalesBottom();
        ui.showSalesCost("Total for day: ", "$" + dollarValue.format(calculateTotalCost(aggregatedOrders)));
    }

    /**
     * Aggregates orders by updating quantities and total order costs for the same dish.
     *
     * @param order           The Order object to be aggregated.
     * @param aggregatedOrders The ArrayList of aggregated orders.
     */
    private void aggregateOrder(Order order, ArrayList<Order> aggregatedOrders) {
        logger.info("Aggregating order...");
        if (order.getIsComplete()) {
            int index = getIndexByDishName(aggregatedOrders, order.getDishName());
            aggregatedOrders.get(index)
                    .setQuantity(aggregatedOrders.get(index).getQuantity() + order.getQuantity());
            aggregatedOrders.get(index)
                    .setTotalOrderCost(aggregatedOrders.get(index).getTotalOrderCost() + order.getTotalOrderCost());
        }
    }

    /**
     * Finds the index of an order in the aggregated orders list based on the dish name.
     *
     * @param aggregatedOrders The ArrayList of aggregated orders.
     * @param dishName         The dish name to search for.
     * @return The index of the order with the specified dish name, or -1 if not found.
     */
    private int getIndexByDishName(ArrayList<Order> aggregatedOrders, String dishName) {
        for (int i = 0; i < aggregatedOrders.size(); i++) {
            Order order = aggregatedOrders.get(i);
            String orderDishName = order.getDishName().trim();
            dishName = dishName.trim();
            if (orderDishName.equalsIgnoreCase(dishName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Calculates the total cost of all orders for a specific day.
     *
     * @param orders The ArrayList of orders.
     * @return The total cost of all orders for the day.
     */
    private float calculateTotalCost(ArrayList<Order> orders) {
        logger.info("Calculating total cost...");
        float totalCost = 0;
        for (Order order : orders) {
            totalCost += order.getTotalOrderCost();
            logger.info("Total cost: " + totalCost);
        }
        return totalCost;
    }
    public boolean isEmpty() {
        return orderList.isEmpty();
    }

    public boolean hasCompletedOrders() {
        for (Order order : orderList) {
            if (order.getIsComplete()) {
                return true;
            }
        }
        return false;
    }
}
