package seedu.cafectrl.storage;


import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.parser.Parser;
import seedu.cafectrl.parser.exception.ParserException;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Decoder class offers methods to interpret string representations from text files,
 * decoding them into appropriate data structures. It includes methods to decode a Menu,
 * Pantry stock, and OrderList, allowing retrieval of data stored in a file.
 */
public class Decoder {

    private static final String DIVIDER = "\\| ";
    private static final String INGREDIENT_DIVIDER = " - ";
    private static final Ui ui = new Ui();
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());

    //@@author ShaniceTang
    /**
     * Decodes an ArrayList of string lines into a Menu object, reconstructing its content.
     *
     * @param textLines An ArrayList of strings representing the encoded Menu data.
     * @return A Menu object containing the decoded Menu data.
     */
    public static Menu decodeMenuData(ArrayList<String> textLines) {
        logger.info("Decoding menu.txt to Menu...");
        ArrayList<Dish> menuDishList = new ArrayList<>();

        for(String dishString : textLines) {
            logger.info("Line to decode: " + dishString);
            decodeDishString(dishString, menuDishList);
        }

        return new Menu(menuDishList);
    }

    /**
     * Decodes a string representation of a dish and adds it to the menu's list of dishes.
     *
     * @param dishString      The string containing dish information.
     * @param menuDishList    The list to which the decoded dish will be added.
     */
    private static void decodeDishString(String dishString, ArrayList<Dish> menuDishList) {
        String dishName = "";
        try {
            String[] dishStringArray = dishString.split(DIVIDER);
            dishName = dishStringArray[0].trim().toLowerCase();
            checkNameValidity(dishName);
            float dishPrice = Parser.parsePriceToFloat(dishStringArray[1]);
            String[] ingredientStringArray = Arrays.copyOfRange(dishStringArray, 2, dishStringArray.length);
            ArrayList<Ingredient> ingredientsList = decodeIngredientData(ingredientStringArray);
            menuDishList.add(new Dish(dishName, ingredientsList, dishPrice));
        } catch (ParserException e) {
            logger.log(Level.WARNING, "Dish has invalid price: " + e.getMessage(), e);
            ui.showToUser(ErrorMessages.INVALID_MENU_DATA + dishString);
        } catch (RuntimeException e) {
            logger.log(Level.WARNING, "Dish has no ingredients: " + e.getMessage(), e);
            ui.showToUser(e.getMessage() + dishName);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Line corrupted: " + e.getMessage(), e);
            ui.showToUser(ErrorMessages.INVALID_MENU_DATA + dishString);
        }
    }

    private static void checkNameValidity(String name) throws Exception {
        if (Parser.isNameLengthInvalid(name) || name.isEmpty() || Parser.containsSpecialChar(name)) {
            throw new Exception();
        }
    }

    /**
     * Decodes an array of strings representing ingredient data into a list of Ingredient objects.
     *
     * @param ingredientsStringArray An array of strings containing encoded ingredient data.
     * @return An ArrayList of Ingredient objects containing the decoded ingredient information.
     */
    private static ArrayList<Ingredient> decodeIngredientData(String[] ingredientsStringArray) throws Exception {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        if (ingredientsStringArray.length < 1) {
            throw new RuntimeException(ErrorMessages.MISSING_INGREDIENT_MENU_DATA);
        }

        for(String ingredientString : ingredientsStringArray) {
            logger.info("Ingredient to decode: " + ingredientString);
            String[] array = ingredientString.split(INGREDIENT_DIVIDER);
            String name = array[0].trim().toLowerCase();
            checkNameValidity(name);
            int qty = Integer.parseInt(array[1].trim());
            checkQtyValidity(qty);
            String unit = array[2].trim();
            checkUnitValidity(unit);
            ingredientList.add(new Ingredient(name, qty, unit));
        }
        return ingredientList;
    }

    private static void checkQtyValidity(int qty) throws Exception {
        if (Parser.isInvalidQty(qty)) {
            throw new Exception();
        }
    }

    private static void checkUnitValidity(String unit) throws Exception {
        if (!Parser.isValidUnit(unit) || Parser.isEmptyUnit(unit)) {
            throw new Exception();
        }
    }

    //@@author ziyi105
    /**
     * Decodes raw string from pantry stock data file and create ingredient object from the data
     *
     * @param encodedPantryStock raw string from pantry stock data file
     * @return a new pantry object with data from the pantry stock data file
     */
    public static Pantry decodePantryStockData(ArrayList<String> encodedPantryStock) {
        logger.info("Decoding Pantry_stock.txt to PantryStock...");
        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        Ingredient ingredient;

        if (encodedPantryStock.isEmpty()) {
            return new Pantry(ui);
        }
        for (String encodedData : encodedPantryStock) {
            logger.info("Line to decode: " + encodedData);
            String[] decodedData = encodedData.split(DIVIDER);
            if (!isValidPantryStockFormat(decodedData)) {
                ui.showToUser(ErrorMessages.ERROR_IN_PANTRY_STOCK_DATA + encodedData);
                continue;
            }
            String ingredientName = decodedData[0].trim();
            String qtyText = decodedData[1].trim();
            String unit = decodedData[2].trim();

            // Check whether qty is an integer
            int qty;
            try {
                qty = Integer.parseInt(qtyText);
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Line corrupted: " + e.getMessage(), e);
                ui.showToUser(ErrorMessages.ERROR_IN_PANTRY_STOCK_DATA + encodedData);
                continue;
            }

            // Check whether the parameters are correct
            if (isValidIngredientName(ingredientName, pantryStock)
                    && !Parser.isInvalidQty(qty)
                    && isValidUnit(unit)) {
                ingredient = new Ingredient(ingredientName, qty, unit);
                pantryStock.add(ingredient);
            } else {
                logger.info(ErrorMessages.ERROR_IN_PANTRY_STOCK_DATA + encodedData);
                ui.showToUser(ErrorMessages.ERROR_IN_PANTRY_STOCK_DATA + encodedData);
            }
        }

        return new Pantry(ui, pantryStock);
    }

    /**
     * Checks whether the ingredient name is valid in terms of length, containment of
     * special character and whether it is a repeated ingredient
     * @param ingredientName name of the ingredient
     * @param pantryStock pantry stock with data from previous lines in the text file
     * @return true if the name is valid, false otherwise
     */
    private static boolean isValidIngredientName(String ingredientName, ArrayList<Ingredient> pantryStock) {
        return !Parser.containsSpecialChar(ingredientName)
                && !Parser.isNameLengthInvalid(ingredientName)
                && !Parser.isRepeatedIngredientName(ingredientName, pantryStock);
    }

    private static boolean isValidUnit(String unit) {
        return !Parser.isEmptyUnit(unit) && Parser.isValidUnit(unit);
    }

    /**
     * Checks whether the pantry stock is in the format of ingredient name | quantity (int) | unit
     *
     * @param decodedPantryStock string array of the raw data string from pantry stock data file
     *                           split with "|"
     * @return true if the format is correct, false otherwise
     */
    private static boolean isValidPantryStockFormat(String[] decodedPantryStock) {
        if (decodedPantryStock.length != 3) {
            return false;
        } else {
            try {
                Integer.parseInt(decodedPantryStock[1].trim());
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    //@@author NaychiMin
    /**
     * Decodes a list of order data and constructs a Sales object using an array of OrderList objects.
     *
     * @param textLines List of order strings in the format "dishName|quantity|totalOrderCost".
     * @param menu Menu instance to retrieve Dish objects based on dishName.
     * @return Sales object containing OrderList objects decoded from the provided strings.
     */
    public static Sales decodeSales(ArrayList<String> textLines, Menu menu) {
        logger.info("Decoding orders.txt to Sales...");
        boolean salesOrderTextTamperDetectionMessagePrinted = false;
        ArrayList<OrderList> orderLists = new ArrayList<>();

        if (textLines.isEmpty()) {
            return new Sales();
        }

        //for each 'order' in text file
        for (String line : textLines) {
            logger.info("Line to decode: " + line);
            if (line.isEmpty()) {
                continue;
            }
            decodeSalesData(line, orderLists, menu);
        }

        if (orderLists.isEmpty()) {
            return new Sales();
        }
        return new Sales(orderLists);
    }

    /**
     * Decodes the sales data from a single order line and adds it to the list of OrderList objects.
     *
     * @param orderLine   The order line in the format "day|dishName|quantity|totalOrderCost|isComplete".
     * @param orderLists  The list of OrderList objects to which the decoded order will be added.
     * @param menu        Menu instance to retrieve Dish objects based on dishName.
     */
    private static void decodeSalesData(String orderLine, ArrayList<OrderList> orderLists, Menu menu) {
        try {
            String[] orderData = orderLine.split(DIVIDER);
            int day = Integer.parseInt(orderData[0].trim()) - Sales.DAY_DISPLAY_OFFSET;
            String dishName = orderData[1].trim();

            //@@author Cazh1
            //keeps track of the number of days cafe has been operating for
            if (dishName.equals(Encoder.NULL_ORDER_DAY)) {
                fillOrderListSize(orderLists, day);
                return;
            }
            //@@author

            int quantity = Integer.parseInt(orderData[2].trim());
            float decodedDishPrice = Float.parseFloat(orderData[3].trim());
            String completeStatus = orderData[4].trim();
            float totalOrderCost = quantity * decodedDishPrice;

            checkNameValidity(dishName);
            boolean isDataAccurate = isCompleteStatusAccurate(orderLine, completeStatus)
                    && isValidQty(orderLine, quantity)
                    && isValidPrice(orderLine, decodedDishPrice);
            if (!isDataAccurate) {
                return;
            }

            Dish dishToAdd = new Dish(dishName, decodedDishPrice);
            //creates new order and adds to orderList for specific day
            boolean isComplete = Boolean.parseBoolean(completeStatus.toLowerCase());
            Order orderedDish = new Order(dishToAdd, quantity, totalOrderCost, isComplete);
            fillOrderListSize(orderLists, day);
            orderLists.get(day).addOrder(orderedDish);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Line corrupted: " + e.getMessage(), e);
            ui.showToUser(ErrorMessages.INVALID_SALES_DATA + orderLine);
        }
    }

    private static boolean isCompleteStatusAccurate(String orderLine, String completeStatus) {
        if (completeStatus.equalsIgnoreCase("true")
                || completeStatus.equalsIgnoreCase("false")) {
            return true;
        }
        ui.showToUser(ErrorMessages.INVALID_ORDER_STATUS + orderLine);
        return false;
    }

    private static boolean isValidPrice(String orderLine, Float decodedDishPrice) {
        if (decodedDishPrice >= 0) {
            return true;
        }
        ui.showToUser(ErrorMessages.INVALID_DISH_PRICE + orderLine);
        return false;
    }

    private static boolean isValidQty(String orderLine, int quantity) {
        if (quantity > 0) {
            return true;
        }
        ui.showToUser(ErrorMessages.INVALID_ORDER_QTY + orderLine);
        return false;
    }

    //@@author Cazh1
    /**
     * Increases the size of the orderlist when there is gap between the previous order and the next
     *
     * @param orderLists The current partially filled ArrayList of OrderList
     * @param day The day of the next order
     */
    private static void fillOrderListSize(ArrayList<OrderList> orderLists, int day) {
        while (orderLists.size() <= day) {
            orderLists.add(new OrderList());
        }
    }
}
