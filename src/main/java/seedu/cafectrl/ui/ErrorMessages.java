package seedu.cafectrl.ui;

import seedu.cafectrl.command.EditPriceCommand;

public class ErrorMessages {
    /** Error messages */
    public static final String INVALID_ADD_DISH_FORMAT_MESSAGE = "Error: Incorrect format for the add command.\n";
    public static final String NULL_NAME_DETECTED_MESSAGE = "Error: Null dish name detected";
    public static final String LARGE_PRICE_MESSAGE = "Wow! This dish must taste heavenly "
            + "to cost that much money! But it might be too expensive for normal people like me :("
            + "Maybe you should keep it below $1000000 instead?";
    public static final String NEGATIVE_PRICE_MESSAGE = "Negative price? We can't be paying "
            + "the customers for eating in our cafe right, "
            + "the minimum price acceptable is $0.00!";
    public static final String INVALID_DISH_NAME_LENGTH_MESSAGE = "Error: Your dish name length is too long!";
    public static final String INVALID_INGREDIENT_NAME_LENGTH_MESSAGE = "Error: Your dish name length is too long!";
    public static final String MISSING_ARGUMENT_FOR_EDIT_PRICE = "Error: Missing arguments "
            + "for edit price command.\n"
            + EditPriceCommand.MESSAGE_USAGE;
    public static final String MISSING_ARGUMENT_FOR_LIST_INGREDIENTS = "Error: Missing arguments "
            + "for list ingredients command.";
    public static final String MISSING_ARGUMENT_FOR_DELETE = "Error: Missing arguments "
            + "for delete command.";
    public static final String MISSING_ARGUMENT_FOR_BUY_INGREDIENT = "Error: Missing arguments "
            + "for buy ingredient command.";
    public static final String WRONG_DISH_INDEX_TYPE_FOR_EDIT_PRICE = "Error: "
            + "Invalid argument type. \n "
            + "Make sure dish index is of type int";
    public static final String WRONG_PRICE_TYPE_FOR_EDIT_PRICE = "Error: "
            + "Invalid price! \n "
            + "Price can only have 2 decimal place and it must be "
            + "within the range of 0.00 to 1000000!";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Error: Unknown command. "
            + "Type 'help' to view the accepted list of commands";
    public static final String INVALID_DISH_INDEX = "Do we even have this dish? "
            + "Double check the index of the dish you wanna modify!";
    public static final String INVALID_ARGUMENT_FOR_BUY_INGREDIENT = "Error: Invalid arguments "
            + "for buy ingredient command.";
    public static final String INVALID_ADD_ORDER_FORMAT_MESSAGE = "Error: Incorrect format "
            + "for the add order command.";
    public static final String DATA_FOLDER_NOT_FOUND_MESSAGE = "Data Folder was not found!\nIt's ok... "
            + "a new data folder has been created.";
    public static final String DISH_NOT_FOUND = "I'm sorry, but it appears that dish is so exclusive "
            + "it hasn't even made it to our menu yet!";
    public static final String ERROR_IN_PANTRY_STOCK_DATA = "Error in pantry stock data file! "
            + "Skipping this particular ingredient!";
    public static final String UNIT_NOT_MATCHING = "Sorry, you have used a "
            + "different unit for this ingredient!";
    public static final String MENU_FILE_NOT_FOUND_MESSAGE = "Menu data was not found!\n"
            + "No worries, new menu has been created";
    public static final String PANTRY_FILE_NOT_FOUND_MESSAGE = "Pantry stock data was not found!\n"
            + "No worries, new pantry has been created";
    public static final String ORDER_LIST_FILE_NOT_FOUND_MESSAGE = "Order list data was not found!\n"
            + "No worries, new order list has been created";
    public static final String INVALID_SHOW_SALE_DAY_FORMAT_MESSAGE = "Error: "
            + "Incorrect format for the show_sale command.\n";
    public static final String INVALID_DAY_FORMAT = "Sorry, please enter a valid integer "
            + "for the 'day' field!";
    public static final String EDIT_SAME_PRICE = "New price is exactly the same as old price,"
            + " is that what you want?";
}
