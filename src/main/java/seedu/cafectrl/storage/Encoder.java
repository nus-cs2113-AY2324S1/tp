package seedu.cafectrl.storage;

import seedu.cafectrl.data.*;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;

import java.util.ArrayList;

public class Encoder {

    private static final String DIVIDER = " | ";

    public static ArrayList<String> encodePantryStock(Pantry pantry) {
        return null;
    }

    public static ArrayList<String> encodeSales(Sales sales) {
        ArrayList<String> encodedList = new ArrayList<>();
        ArrayList<OrderList> orderLists = sales.getOrderLists();

        for (int day = 0; day < orderLists.size(); day++) {
            //get orderList for each day from list of sales
            OrderList orderList = sales.getOrderList(day);
            //get order from each orderList obtained
            for (Order order : orderList.getOrderList()) {
                StringBuilder orderString = new StringBuilder();
                //day of each orderList is index + 1
                orderString.append((day + 1) + DIVIDER);
                orderString.append(order.getDishName() + DIVIDER);
                orderString.append(order.getQuantity() + DIVIDER);
                orderString.append(order.totalOrderCost());
                encodedList.add(String.valueOf(orderString));
            }
        }
        return encodedList;
    }

}
