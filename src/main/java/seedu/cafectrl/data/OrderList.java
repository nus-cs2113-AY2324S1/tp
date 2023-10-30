package seedu.cafectrl.data;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderList {
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    private ArrayList<Order> orderList;
    private float totalOrderListCost;
    public OrderList() {
        this.orderList = new ArrayList<>();
        this.totalOrderListCost = 0;
    }
    public OrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
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

    public void printOrderList() {
        if (orderList.isEmpty()) {
            return;
        }
        System.out.println("\nPrinting Orders");
        for (int i = 0; i < getSize(); i++) {
            Order order = getOrder(i);
            String orderString = order.toString();
            System.out.println(orderString);
        }
        System.out.println("\nTotal Order cost: $" + dollarValue.format(getTotalOrderListCost()));
    }
}
