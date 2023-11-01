package seedu.cafectrl.storage;

import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.data.Menu;

import java.util.ArrayList;

/**
 * The Encoder class provides methods to encode various data structures into string representations to
 * be written into their respective text files.
 * It includes methods to encode a Menu, Pantry stock, and OrderList,
 * making the data suitable for saving to a file.
 */
public class Encoder {
    private static final String DIVIDER = " | ";

    /**
     * Encodes a Menu object into a list of strings representing its contents, suitable for saving to a file.
     *
     * @param menu The Menu object to encode into a string representation.
     * @return An ArrayList of strings, where each string represents a Dish in the Menu.
     */
    public static ArrayList<String> encodeMenu(Menu menu) {
        ArrayList<String> menuStringList = new ArrayList<>();
        ArrayList<Dish> menuDishList = menu.getMenuItemsList();
        for(Dish dish : menuDishList) {
            StringBuilder dishString = new StringBuilder();
            dishString.append(dish.getName() + DIVIDER);
            dishString.append(dish.getPrice() + DIVIDER);
            dishString.append(encodeIngredientList(dish.getIngredients()));
            dishString.append(System.lineSeparator());
            menuStringList.add(String.valueOf(dishString));
        }
        return menuStringList;
    }

    /**
     * Encodes a list of ingredients into a StringBuilder for inclusion in the menu encoding.
     *
     * @param ingredientList The list of Ingredient objects to encode.
     * @return A StringBuilder containing the encoded representation of the ingredient list.
     */
    private static StringBuilder encodeIngredientList(ArrayList<Ingredient> ingredientList) {
        StringBuilder ingredientListString = new StringBuilder();
        for(Ingredient ingredient : ingredientList) {
            ingredientListString.append(ingredient.getName() + " ");
            ingredientListString.append(ingredient.getQty() + " ");
            ingredientListString.append(ingredient.getUnit() + DIVIDER);
        }
        return ingredientListString;
    }

    //@@author ziyi105
    public static ArrayList<String> encodePantryStock(Pantry pantry) {
        // Convert pantry stock to a list of String
        ArrayList<String> pantryStockInString = new ArrayList<>();
        ArrayList<Ingredient> pantryStock = pantry.getPantryStock();
        for (Ingredient ingredient : pantryStock) {
            String encodedIngredient = ingredient.getName() + " "
                    + ingredient.getQty() + " " + ingredient.getUnit();
            pantryStockInString.add(encodedIngredient);
        }
        return pantryStockInString;
    }

    //@@NaychiMin
    /**
     * Encodes a Sales object into a list of strings for storage.
     * Each string represents an order, including day, dish name, quantity, and total cost.
     *
     * @param sales The Sales object to be encoded.
     * @return An ArrayList of strings representing the encoded sales data.
     */
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
                orderString.append(order.totalOrderCost() + DIVIDER);
                orderString.append(order.getIsComplete());
                orderString.append(System.lineSeparator());
                encodedList.add(String.valueOf(orderString));
            }
        }
        return encodedList;
    }
    //@@author
}
