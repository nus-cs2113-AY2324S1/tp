package seedu.cafectrl.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

/**
 * The Sales class represents sales data over a period of time, maintaining a collection of order lists.
 */
public class Sales {
    public static final int DAY_DISPLAY_OFFSET = 1;
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    private static ArrayList<OrderList> orderLists;
    private int daysAccounted;

    public Sales() {
        this.orderLists = new ArrayList<>();
        orderLists.add(new OrderList());
        this.daysAccounted = 0;
    }

    public Sales(ArrayList<OrderList> orderLists) {
        this.orderLists = orderLists;
        this.daysAccounted = orderLists.size() - DAY_DISPLAY_OFFSET;
    }

    public Sales(OrderList orderList) {
        this.orderLists = new ArrayList<>();
        orderLists.add(orderList);
        this.daysAccounted = orderLists.size() - DAY_DISPLAY_OFFSET;
    }

    public void addOrderList(OrderList orderList) {
        orderLists.add(orderList);
    }

    public void nextDay() {
        this.daysAccounted += DAY_DISPLAY_OFFSET;
    }

    public int getDaysAccounted() {
        return daysAccounted;
    }

    public ArrayList<OrderList> getOrderLists() {
        return orderLists;
    }

    public OrderList getOrderList(int index) {
        return orderLists.get(index);
    }

    //@@author NaychiMin
    /**
     * Prints all sales data, organized by day, including dish names, quantities, and total cost prices.
     *
     * @param ui   The Ui object for user interface interactions.
     */
    public void printSales(Ui ui) {
        if(isOrderListsEmpty()) {
            logger.info("Printing empty sales...");
            ui.showToUser("No sales made.");
            return;
        }

        for (int day = 0; day < orderLists.size(); day++) {
            logger.info("Printing sales for day " + day + "...");
            OrderList orderList = orderLists.get(day);

            if (orderList.isEmpty() || !orderList.hasCompletedOrders()) {
                ui.showToUser("No sales for day " + (day + DAY_DISPLAY_OFFSET) + ".");
                continue;
            }

            ui.showSalesTop(day + DAY_DISPLAY_OFFSET);
            orderList.printOrderList(ui);
        }
    }

    /**
     * Prints sales data for a specific day, including dish names, quantities, and total cost prices.
     *
     * @param ui  The Ui object for user interface interactions.
     * @param day The day for which sales data is to be printed.
     */
    public void printSaleByDay(Ui ui, int day) {
        logger.info("Printing sales by day...");
        int orderListIndex = day - DAY_DISPLAY_OFFSET;
        try {
            OrderList orderList = orderLists.get(orderListIndex);
            if (orderList.isEmpty() || !orderList.hasCompletedOrders()) {
                ui.showToUser("No sales for this day.");
                return;
            }

            ui.showSalesTop(day);
            orderList.printOrderList(ui);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to print sales for day " + day + "\n" + e.getMessage(), e);
            ui.showToUser(ErrorMessages.INVALID_SALE_DAY);
        }
    }

    public boolean isOrderListsEmpty() {
        for (OrderList orderList : orderLists) {
            if (!orderList.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
