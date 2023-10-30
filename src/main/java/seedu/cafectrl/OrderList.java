package seedu.cafectrl;

import java.util.ArrayList;

public class OrderList {
    protected ArrayList<Order> orderList;
    protected float totalOrderListCost;
    public OrderList() {
        this.orderList = new ArrayList<Order>();
        this.totalOrderListCost = 0;
    }

    public OrderList(ArrayList<Order> decodedOrderList){
        this.orderList = decodedOrderList;

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
    public boolean isEmpty() {
        return orderList.isEmpty();
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void addCost(Order order) {
        float orderCost = order.totalOrderCost;
        totalOrderListCost += orderCost;
    }

    public float getTotalOrderListCost() {
        return totalOrderListCost;
    }
}
