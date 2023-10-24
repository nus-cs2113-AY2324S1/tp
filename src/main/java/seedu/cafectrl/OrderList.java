package seedu.cafectrl;

import java.util.ArrayList;

public class OrderList {
    protected ArrayList<Order> orderList;
    public OrderList() {
        this.orderList = new ArrayList<Order>();
    }
    public OrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
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
}
