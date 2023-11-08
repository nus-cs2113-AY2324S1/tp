package seedu.cafectrl.data;

import java.util.ArrayList;

public class CurrentDate {
    private int currentDay;

    public CurrentDate() {
        currentDay = 0;
    }
    public CurrentDate(int day) {
        currentDay = day - 1;
    }
    public CurrentDate(Sales sales) {
        setDate(sales);
    }

    public void nextDay() {
        currentDay += 1;
    }

    public void previousDay() {
        currentDay -= 1;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * Sets the current date to the latest date the user left off
     *
     * @param sales Used to access the number of orderlist created
     */
    public void setDate(Sales sales) {
        ArrayList<OrderList> orderLists = sales.getOrderLists();
        int orderListsSize = orderLists.size();
        currentDay = orderListsSize - 1;
    }
}
