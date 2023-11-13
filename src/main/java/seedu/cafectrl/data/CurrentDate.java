package seedu.cafectrl.data;

import java.util.ArrayList;

public class CurrentDate {
    private static final int MIN_ORDER_LIST_SIZE = 0;
    private static final int DAY_BASE_NUMBER = 0;
    private static final int DAY_OFFSET = 1;
    private static final int ORDER_LIST_SIZE_OFFSET = 1;
    private int currentDay;

    public CurrentDate() {
        currentDay = DAY_BASE_NUMBER;
    }
    public CurrentDate(int day) {
        currentDay = day - DAY_OFFSET;
    }
    public CurrentDate(Sales sales) {
        setDate(sales);
    }

    public void nextDay() {
        currentDay += DAY_OFFSET;
    }

    public void previousDay() {
        currentDay -= DAY_OFFSET;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    /**
     * Sets the current date to the latest date the user left off
     *
     * @param sales Used to access the number of order list created
     */
    public void setDate(Sales sales) {
        ArrayList<OrderList> orderLists = sales.getOrderLists();
        int orderListsSize = orderLists.size();

        if (orderListsSize > MIN_ORDER_LIST_SIZE) {
            currentDay = orderListsSize - ORDER_LIST_SIZE_OFFSET;
        } else {
            currentDay = DAY_BASE_NUMBER;
        }
    }
}
