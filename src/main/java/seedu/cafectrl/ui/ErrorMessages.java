package seedu.cafectrl.ui;

public class ErrorMessages {
    /** Error messages */
    public static final String INVALID_ADD_DISH_FORMAT_MESSAGE = "Error: Incorrect format for the add command.\n";
    public static final String NULL_DISH_NAME_MESSAGE = "Error: Null dish name detected";
    public static final String INVALID_PRICE_MESSAGE = "Error: Price value entered is too big!";
    public static final String MISSING_ARGUMENT_FOR_EDIT_PRICE = "Error: Missing arguments "
            + "for edit price command.";
    public static final String MISSING_ARGUMENT_FOR_LIST_INGREDIENTS = "Error: Missing arguments "
            + "for list ingredients command.";
    public static final String MISSING_ARGUMENT_FOR_DELETE = "Error: Missing arguments "
            + "for delete command.";
    public static final String MISSING_ARGUMENT_FOR_BUY_INGREDIENT = "Error: Missing arguments "
            + "for buy ingredient command.";
    public static final String WRONG_ARGUMENT_TYPE_FOR_EDIT_PRICE = "Error: Invalid dish index. \n "
            + "Make sure dish index is "
            + "of type int and price is of type float!";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Error: Unknown command. "
            + "Type 'help' to view the accepted list of commands";
    public static final String INVALID_DISH_INDEX = "Error: Invalid dish index.";
    public static final String INVALID_ARGUMENT_FOR_BUY_INGREDIENT = "Error: Invalid arguments "
            + "for buy ingredient command.";
    public static final String INVALID_ADD_ORDER_FORMAT_MESSAGE = "Error: Incorrect format for the add order command.";
    public static final String DATA_FOLDER_NOT_FOUND_MESSAGE = "Data Folder was not found!\nIt's ok... "
            + "a new data folder has been created.";
    public static final String DATA_FILE_NOT_FOUND_MESSAGE = "text file was not found!\nIt's ok... "
            + "a new data file has been created.";
    public static final String DISH_NOT_FOUND = "I'm sorry, but it appears that dish is so exclusive "
            + "it hasn't even made it to our menu yet!";
    public static final String ERROR_IN_PANTRY_STOCK_DATA = "Error in pantry stock data file! Skipping this " +
            "particular ingredient!";
    public static final String UNIT_NOT_MATCHING = "Sorry, you have used a different unit for this ingredient!";
    public static final String INVALID_SHOW_SALE_DAY_FORMAT_MESSAGE = "Error: Incorrect format for the show_sale " +
            "command.\n";
    public static final String INVALID_DAY_FORMAT = "Sorry, please enter a valid integer for the 'day' field!";
}
