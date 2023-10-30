package seedu.cafectrl.storage;

import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Decoder class offers methods to interpret string representations from text files,
 * decoding them into appropriate data structures. It includes methods to decode a Menu,
 * Pantry stock, and OrderList, allowing retrieval of data stored in a file.
 */
public class Decoder {
    private static final Ui ui = new Ui();
    public static Pantry decodePantryStockData(ArrayList<String> encodedPantryStock) {
        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        for (String encodedData : encodedPantryStock) {
            String[] decodedData = encodedData.split(" ");
            if (decodedData.length != 3) {
                ui.showToUser("Error in pantry stock data file! Skipping this particular ingredient!");
            } else if (isValidQuantityFormat(decodedData[1])) {
                Ingredient ingredient = new Ingredient(decodedData[0],
                        Integer.parseInt(decodedData[1]), decodedData[2]);
                pantryStock.add(ingredient);
            }
        }
        return new Pantry(ui, pantryStock);
    }

    private static boolean isValidQuantityFormat(String quantityInString) {
        try {
            Integer.parseInt(quantityInString);
        } catch (NumberFormatException e) {
            ui.showToUser("Error in pantry stock data file! Skipping this particular ingredient!");
            return false;
        }
        return true;
    }

    /**
     * Decodes a list of order data and constructs an OrderList object.
     *
     * @param textLines List of order strings in the format "dishName|quantity|totalOrderCost".
     * @param menu Menu instance to retrieve Dish objects based on dishName.
     * @return OrderList containing Order objects decoded from the provided strings.
     */
    public static OrderList decodeOrderList(ArrayList<String> textLines, Menu menu) {
        ArrayList<Order> orderListArray = new ArrayList<>();
        for (String order : textLines) {
            String[] orderData = order.split("\\|");
            String dishName = orderData[0].trim();
            String quantity = orderData[1].trim();
            String totalOrderCost = orderData[2].trim();
            System.out.println(dishName);
            Order orderedDish = new Order(menu.getDishFromName(dishName),
                    Integer.parseInt(quantity), Float.parseFloat(totalOrderCost));
            orderListArray.add(orderedDish);
        }
        return new OrderList(orderListArray);
    }
}
