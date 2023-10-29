package seedu.cafectrl.data;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sales {
    private static final DecimalFormat dollarValue = new DecimalFormat("0.00");
    protected ArrayList<OrderList> orderLists;
    protected int daysAccounted;

    public Sales() {
        this.orderLists = new ArrayList<>();
        orderLists.add(new OrderList());
        this.daysAccounted = 0;
    }

    public Sales(ArrayList<OrderList> orderLists) {
        this.orderLists = orderLists;
        this.daysAccounted = 0;
    }

    public Sales(OrderList orderList) {
        this.orderLists = new ArrayList<>();
        orderLists.add(orderList);
        this.daysAccounted = 0;
    }

    public void addOrderList(OrderList orderList) {
        orderLists.add(orderList);
    }

    public void removeOrderList(int orderListId) {
        orderLists.remove(orderListId);
    }

    public String getTotalSales() {
        float totalSales = 0;
        for (int i = 0; i < orderLists.size(); i++) {
            totalSales += orderLists.get(i).getTotalOrderListCost();
        }
        return dollarValue.format(totalSales);
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
}
