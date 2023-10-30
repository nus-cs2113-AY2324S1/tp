package seedu.cafectrl.storage;

import seedu.cafectrl.Order;
import seedu.cafectrl.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;

import java.util.ArrayList;

public class Encoder {

    private static final String DIVIDER = " | ";

    public static ArrayList<String> encodePantryStock(Pantry pantry) {
        return null;
    }

    /**
     * Encodes a list of orders into a storage-friendly format.
     * Each order is represented as a string in the format "dishName|quantity|totalOrderCost".
     * If the provided OrderList is empty, an empty ArrayList is returned.
     *
     * @param ordersList The OrderList to be encoded.
     * @return ArrayList of strings representing encoded orders.
     */
    public static ArrayList<String> encodeOrderList(OrderList ordersList) {
        ArrayList<String> encodedList = new ArrayList<>();
        ArrayList<Order> orderList = ordersList.getOrderList();
        if(orderList.isEmpty()){
            return encodedList;
        }

        for (Order order : orderList) {
            StringBuilder orderString = new StringBuilder();
            orderString.append(order.getDishName() + DIVIDER);
            orderString.append(order.getQuantity() + DIVIDER);
            orderString.append(order.totalOrderCost());
            encodedList.add(String.valueOf(orderString));
        }

        return encodedList;
    }

}
