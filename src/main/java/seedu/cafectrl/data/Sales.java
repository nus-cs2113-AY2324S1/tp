package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Dish;

import java.text.DecimalFormat;
import java.util.ArrayList;
import seedu.cafectrl.ui.Ui;

/**
 * The Sales class represents sales data over a period of time, maintaining a collection of order lists.
 */
public class Sales {
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    private static ArrayList<OrderList> orderLists;
    private int daysAccounted;

    /**
     * Constructs a Sales object with an empty list of order lists and zero days accounted.
     */
    public Sales() {
        this.orderLists = new ArrayList<>();
        orderLists.add(new OrderList());
        this.daysAccounted = 0;
    }

    /**
     * Constructs a Sales object with the provided list of order lists and zero days accounted.
     *
     * @param orderLists The initial list of order lists to set.
     */
    public Sales(ArrayList<OrderList> orderLists) {
        this.orderLists = orderLists;
        this.daysAccounted = 0;
    }

    //TODO: @Zhong Heng, Remove this method if not used
    public Sales(OrderList orderList) {
        this.orderLists = new ArrayList<>();
        orderLists.add(orderList);
        this.daysAccounted = 0;
    }

    /**
     * Adds an order list to the collection of order lists.
     *
     * @param orderList The order list to add.
     */
    public void addOrderList(OrderList orderList) {
        orderLists.add(orderList);
    }

    //TODO: @Zhong Heng, Remove this method if not used
    public void removeOrderList(int orderListId) {
        orderLists.remove(orderListId);
    }

    /**
     * Calculates and returns the total sales over all order lists, formatted as a dollar value.
     *
     * @return The total sales as a formatted string.
     */
    public String getTotalSales() {
        float totalSales = 0;
        for (int i = 0; i < orderLists.size(); i++) {
            totalSales += orderLists.get(i).getTotalOrderListCost();
        }
        return dollarValue.format(totalSales);
    }

    /**
     * Advances the number of days accounted by one.
     */
    public void nextDay() {
        this.daysAccounted += 1;
    }

    /**
     * Retrieves the number of days accounted.
     *
     * @return The number of days accounted.
     */
    public int getDaysAccounted() {
        return daysAccounted;
    }

    /**
     * Retrieves the collection of order lists.
     *
     * @return The list of order lists.
     */
    public ArrayList<OrderList> getOrderLists() {
        return orderLists;
    }

    /**
     * Retrieves an order list from the collection based on its index.
     *
     * @param index The index of the order list to retrieve.
     * @return The order list at the specified index.
     */
    public OrderList getOrderList(int index) {
        return orderLists.get(index);
    }

    /**
     * Prints the sales data for all days, including dish names, quantities, and total cost prices.
     */
    public void printSales(Ui ui, Menu menu) {
        if (orderLists.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (int day = 0; day < orderLists.size(); day++) {
            OrderList orderList = orderLists.get(day);

            System.out.println("Day " + (day+1) + ":");
            System.out.printf("%-20s %-10s %-20s\n", "Dish Name", "Dish Qty", "Total Cost Price");
            System.out.println("entered loop list");
            orderList.printOrderList(menu);

            System.out.println();
        }
    }

    public void printSaleByDay(Ui ui, Menu menu, int day){
        int orderListIndex = day - 1;
        try {
            OrderList orderList = orderLists.get(orderListIndex);
            System.out.println("Day " + (day) + ":");
            System.out.printf("%-20s %-10s %-20s\n", "Dish Name", "Dish Qty", "Total Cost Price");

            orderList.printOrderList(menu);

            System.out.println();
        } catch (Exception e) {
            ui.showToUser("An error occurred while printing sales for the specified day.");
        }
    }
}
