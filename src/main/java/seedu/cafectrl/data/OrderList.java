package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Dish;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The OrderList class represents a list of orders for a specific day.
 * It manages the collection of orders and calculates the total cost for the day.
 */
public class OrderList {
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
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
    public Order getOrder(int orderID) {
        return orderList.get(orderID);
    }
    public void removeOrder(int orderID) {
        orderList.remove(orderID);
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void addCost(Order order) {
        float orderCost = order.getTotalOrderCost();
        totalOrderListCost += orderCost;
    }

    public float getTotalOrderListCost() {
        return totalOrderListCost;
    }

    private float calculateTotalCost(ArrayList<Order> orders) {
        float totalCost = 0;
        for (Order order : orders) {
            totalCost += order.getTotalOrderCost();
        }
        return totalCost;
    }

    public void printOrderList(Menu menu) {
        ArrayList<Order> aggregatedOrders = menu.getAggregatedOrders();
        if (!orderList.isEmpty()) {
            for (Order order : getOrderList()) {
                aggregateOrder(order, aggregatedOrders);
            }
            for (Order aggregatedOrder : aggregatedOrders) {
                    System.out.printf("%-20s %-10d $%-20.2f\n",
                            aggregatedOrder.getDishName(), aggregatedOrder.getQuantity(), aggregatedOrder.totalOrderCost());

            }
            System.out.printf("Total for day: $%-20.2f\n", calculateTotalCost(aggregatedOrders));
        } else {
            System.out.println("No orders for this day.");
        }
    }

    private void aggregateOrder(Order order, ArrayList<Order> aggregatedOrders) {
        if (order.getIsComplete()) {
            int index = getIndexByDishName(aggregatedOrders, order.getDishName());
            aggregatedOrders.get(index).setQuantity(aggregatedOrders.get(index).getQuantity() + order.getQuantity());
            aggregatedOrders.get(index).setTotalOrderCost(aggregatedOrders.get(index).getTotalOrderCost() + order.getTotalOrderCost());
        }
    }


    private int getIndexByDishName(ArrayList<Order> aggregatedOrders, String dishName) {
        for (int i = 0; i < aggregatedOrders.size(); i++) {
            Order order = aggregatedOrders.get(i);
            if (order.getDishName().equalsIgnoreCase(dishName.trim())) {
                return i;
            }
        }
        return -1;
    }

}
