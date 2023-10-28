package seedu.cafectrl;

import seedu.cafectrl.data.OrderList;

import java.util.ArrayList;

public class Sales {
    protected ArrayList<OrderList> orderLists;
    protected float totalSales;
    protected int daysAccounted;
    protected boolean newOrderList;
    protected boolean isDateChange;

    public Sales() {
        this.orderLists = new ArrayList<>();
        orderLists.add(new OrderList());
        this.totalSales = 0;
        this.daysAccounted = 0;
        this.newOrderList = false;
        this.isDateChange = false;
    }

    public Sales(ArrayList<OrderList> orderLists) {
        this.orderLists = orderLists;
        this.totalSales = 0;
        this.daysAccounted = 0;
        this.newOrderList = false;
        this.isDateChange = false;
    }

    public Sales(OrderList orderList) {
        this.orderLists = new ArrayList<>();
        orderLists.add(orderList);
        this.totalSales = 0;
        this.daysAccounted = 0;
        this.newOrderList = false;
        this.isDateChange = false;
    }

    public Sales(ArrayList<OrderList> orderLists, float totalSales) {
        this.orderLists = orderLists;
        this.totalSales = totalSales;
        this.daysAccounted = 0;
        this.newOrderList = false;
        this.isDateChange = false;
    }

    public Sales(ArrayList<OrderList> orderLists, float totalSales, int daysAccounted) {
        this.orderLists = orderLists;
        this.totalSales = totalSales;
        this.daysAccounted = daysAccounted;
        this.newOrderList = false;
        this.isDateChange = false;
    }

    public void addOrderList(OrderList orderList) {
        orderLists.add(orderList);
        addCost(orderList);
    }

    public void removeOrderList(int orderListId) {
        orderLists.remove(orderListId);
    }

    public void setOrderLists(int orderListId, OrderList orderList) {
        orderLists.set(orderListId, orderList);
    }

    public void addCost(OrderList orderList) {
        float totalOrderListCost = orderList.getTotalOrderListCost();
        totalSales += totalOrderListCost;
    }

    public float getTotalSales() {
        return totalSales;
    }

    public void nextDay() {
        this.daysAccounted += 1;
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

    public int getOrderListSize() {
        return orderLists.size();
    }

    public void setNewOrderList() {
        this.newOrderList = true;
    }
    public void resetNewOrderList() {
        this.newOrderList= false;
    }
    public boolean isNewOrderList() {
        return newOrderList;
    }
}
