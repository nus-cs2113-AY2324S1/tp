package seedu.cafectrl.storage;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.data.Menu;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * The Encoder class provides methods to encode various data structures into string representations to
 * be written into their respective text files.
 * It includes methods to encode a Menu, Pantry stock, and OrderList,
 * making the data suitable for saving to a file.
 */
public class Encoder {
    public static final String NULL_ORDER_DAY = "the last day has no orders but please account for it";
    private static final String DIVIDER = " | ";
    private static final String INGREDIENT_DIVIDER = " - ";
    private static final Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    public static final String LINE_BREAK = "\n";
    public static final String EMPTY_STRING = "";
    public static final String CARRIAGE_RETURN = "\r";
    public static final String TWO_DECIMAL_PLACE_FORMAT = "%.2f";

    //@@author Cazh1
    /**
     * Generates a hash for the content to be saved into text save file
     *
     * @param stringArrayList The arraylist of String to be saved into text save file
     * @return arraylist of String with the generated hash
     */
    private static ArrayList<String> hashEncoding(ArrayList<String> stringArrayList) {
        String stringArrayListAsString = String.join(", ", stringArrayList).trim();

        //The generated String has line breaks, this removes line breaks
        String stringArrayListAsStringInOneLine = stringArrayListAsString
                .replace(LINE_BREAK, EMPTY_STRING)
                .replace(CARRIAGE_RETURN, EMPTY_STRING);

        //Generate Hash from content
        int stringArrayListHash = stringArrayListAsStringInOneLine.hashCode();
        String stringArrayListHashAsString = String.valueOf(stringArrayListHash);

        //Adds generated Hash into the original ArrayList<String>
        stringArrayList.add(stringArrayListHashAsString);
        return stringArrayList;
    }

    //@@author ShaniceTang
    /**
     * Encodes a Menu object into a list of strings representing its contents, suitable for saving to a file.
     *
     * @param menu The Menu object to encode into a string representation.
     * @return An ArrayList of strings, where each string represents a Dish in the Menu.
     */
    public static ArrayList<String> encodeMenu(Menu menu) {
        logger.info("Encoding Menu to menu.txt...");
        ArrayList<String> menuStringList = new ArrayList<>();
        ArrayList<Dish> menuDishList = menu.getMenuItemsList();
        for(Dish dish : menuDishList) {
            StringBuilder dishString = new StringBuilder();
            dishString.append(dish.getName() + DIVIDER);
            dishString.append(dish.getPrice());
            dishString.append(encodeIngredientList(dish.getIngredients()));
            dishString.append(System.lineSeparator());
            menuStringList.add(String.valueOf(dishString));
            logger.info("Encoded dish: " + dishString);
        }
        ArrayList<String> menuStringListHashed = hashEncoding(menuStringList);
        return menuStringListHashed;
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
            ingredientListString.append(DIVIDER);
            ingredientListString.append(ingredient.getName() + INGREDIENT_DIVIDER);
            ingredientListString.append(ingredient.getQty() + INGREDIENT_DIVIDER);
            ingredientListString.append(ingredient.getUnit());
        }
        return ingredientListString;
    }

    //@@author ziyi105
    /**
     * Encodes the pantry stock into format ingredient name | quantity | unit for storage
     *
     * @param pantry the pantry from current session
     * @return an arrayList of string of ecoded pantry stock
     */
    public static ArrayList<String> encodePantryStock(Pantry pantry) {
        // Convert pantry stock to a list of String
        ArrayList<String> pantryStockInString = new ArrayList<>();
        ArrayList<Ingredient> pantryStock = pantry.getPantryStock();

        for (Ingredient ingredient : pantryStock) {
            StringBuilder encodedIngredient = new StringBuilder();
            encodedIngredient.append(ingredient.getName().trim());
            encodedIngredient.append(DIVIDER);
            encodedIngredient.append(ingredient.getQty());
            encodedIngredient.append(DIVIDER);
            encodedIngredient.append(ingredient.getUnit());
            encodedIngredient.append(System.lineSeparator());
            pantryStockInString.add(encodedIngredient.toString());
            logger.info("Encoded ingredient: " + ingredient.getName());
        }
        ArrayList<String> pantryStockInStringHashed = hashEncoding(pantryStockInString);
        return pantryStockInStringHashed;
    }

    //@@author NaychiMin
    /**
     * Encodes a Sales object into a list of strings for storage.
     * Each string represents an order, including day, dish name, quantity, and total cost.
     *
     * @param sales The Sales object to be encoded.
     * @return An ArrayList of strings representing the encoded sales data.
     */
    public static ArrayList<String> encodeSales(Sales sales) {
        logger.info("Encoding Sales to orders.txt...");
        ArrayList<String> encodedList = new ArrayList<>();
        ArrayList<OrderList> orderLists = sales.getOrderLists();

        for (int day = 0; day < orderLists.size(); day++) {
            logger.info("Encoding sales of day " + day);

            //get orderList for each day from list of sales
            OrderList orderList = sales.getOrderList(day);

            //get order from each orderList obtained
            for (Order order : orderList.getOrderList()) {
                StringBuilder orderString = new StringBuilder();

                //day of each orderList is index + 1
                float orderedDishPrice = order.getOrderedDish().getPrice();
                String orderedDishPriceString = String.format(TWO_DECIMAL_PLACE_FORMAT, orderedDishPrice);

                orderString.append((day + 1) + DIVIDER);
                orderString.append(order.getDishName() + DIVIDER);
                orderString.append(order.getQuantity() + DIVIDER);
                orderString.append(orderedDishPriceString + DIVIDER);
                orderString.append(order.getIsComplete());
                orderString.append(System.lineSeparator());
                encodedList.add(String.valueOf(orderString));
                logger.info("Encoded order: " + orderString);
            }

            if (day == sales.getDaysAccounted()) {
                encodedList = encodeLastSalesDay(encodedList, orderList, day);
            }
        }
        ArrayList<String> encodedListHashed = hashEncoding(encodedList);
        return encodedListHashed;
    }

    //@@author Cazh1
    /**
     * Checks if the last day accessed has valid orders added
     *
     * @param encodedList An ArrayList of strings representing the encoded sales data.
     * @param orderList An ArrayList of Orders of the last day accessed
     * @param day The last day accessed
     * @return encodedList with specific String added at the end if no valid orders were detected
     */
    private static ArrayList<String> encodeLastSalesDay(ArrayList<String> encodedList, OrderList orderList, int day) {
        if (orderList.getSize() == 0) {
            StringBuilder orderString = new StringBuilder();

            //day of each orderList is index + 1
            orderString.append((day + 1) + DIVIDER);
            orderString.append(NULL_ORDER_DAY);
            orderString.append(System.lineSeparator());
            encodedList.add(String.valueOf(orderString));
        }
        return encodedList;
    }
}
