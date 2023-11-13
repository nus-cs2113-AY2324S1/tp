package seedu.cafectrl.ui;

import seedu.cafectrl.command.EditPriceCommand;
import seedu.cafectrl.command.ListTotalSalesCommand;

public class ErrorMessages {
    public static final String INVALID_ADD_DISH_FORMAT = "Error: Incorrect format for the add command.\n";
    public static final String INVALID_INGREDIENT_QTY_FORMAT = "Ingredient Qty is in the wrong format, "
            + "please use the format qty/<VALUE><UNIT> where VALUE is an integer";
    public static final String NULL_NAME_DETECTED_MESSAGE = "Error: Null dish name detected";
    public static final String REPEATED_DISH_MESSAGE = "Sorry, this dish name already exists.";
    public static final String REPEATED_NAME_ARGUMENT = "Error: multiple 'name/' argument detected";
    public static final String REPEATED_PRICE_ARGUMENT = "Error: multiple 'price/' argument detected";
    public static final String REPEATED_INGREDIENT_ARGUMENT = "Error: multiple 'ingredient/' argument detected, "
            + "perhaps you forgot to put a ',' somewhere";
    public static final String REPEATED_QTY_ARGUMENT = "Error: multiple 'qty/' argument detected, "
            + "perhaps you forgot to put a ',' somewhere";
    public static final String MISSING_DISH_NAME = "Seems like you forgot to add the dish name";
    public static final String MISSING_INGREDIENT_NAME = "Seems like you forgot to add the ingredient name "
            + "for the ingredient(s)";
    public static final String LARGE_PRICE_MESSAGE = "Wow! This dish must taste heavenly "
            + "to cost that much money!\nBut it might be too expensive for normal people like me :(\n"
            + "Maybe you should keep it below $1000000 instead?";
    public static final String NEGATIVE_PRICE_MESSAGE = "Negative price? We can't be paying "
            + "the customers for eating in our cafe right, "
            + "the minimum price acceptable is $0.00!";
    public static final String INVALID_DISH_NAME_LENGTH_MESSAGE = "Error: Your dish name length is too long!\n"
            + "Please ensure your dish name is less than 35 characters.";
    public static final String INVALID_INGREDIENT_NAME_LENGTH_MESSAGE = "Error: Your ingredient name length is "
            + "too long!\nPlease ensure your ingredient name is less than 35 characters.";
    public static final String MISSING_ARGUMENT_FOR_EDIT_PRICE = "Error: Missing arguments "
            + "for edit price command.\n"
            + EditPriceCommand.MESSAGE_USAGE;
    public static final String MISSING_ARGUMENT_FOR_LIST_INGREDIENTS = "Error: Missing arguments "
            + "for list ingredients command.";
    public static final String MISSING_ARGUMENT_FOR_DELETE = "Error: Missing arguments "
            + "for delete command.";
    public static final String WRONG_DISH_INDEX_TYPE_FOR_EDIT_PRICE = "Something is wrong with "
            + "the arguments! The types for dish and price are integer and float respectively, \n"
            + "and do not type in duplicated arguments at one time!";
    public static final String WRONG_PRICE_TYPE_FOR_EDIT_PRICE = "Error: Invalid price!\n"
            + "Price must be a float and within the range of 0.00 to 1000000.00 with up to 2 decimal place. \n"
            + "Special characters such as $ are not allowed!";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Error: Unknown command. "
            + "Type 'help' to view the accepted list of commands";
    public static final String INVALID_DISH_INDEX = "Do we even have this dish? "
            + "Double check the index of the dish you wanna modify!";
    public static final String INVALID_ADD_ORDER_FORMAT_MESSAGE = "Error: Incorrect format "
            + "for the add order command.";
    public static final String DATA_FOLDER_NOT_FOUND_MESSAGE = "Data Folder was not found!\nIt's ok... "
            + "a new data folder has been created.";
    public static final String DISH_NOT_FOUND = "I'm sorry, but it appears that dish is so exclusive "
            + "it hasn't even made it to our menu yet!";
    public static final String ERROR_IN_PANTRY_STOCK_DATA = "pantry_stock.txt: Invalid format, "
            + "this pantry stock will be removed -> ";
    public static final String UNIT_NOT_MATCHING = ": Sorry, you have used a "
            + "different unit for this ingredient!"
            + "\nUnit used previously: ";
    public static final String IGNORE_REMAINING_INGREDIENTS = "\nRemaining ingredients after this not added";
    public static final String RETYPE_COMMAND_MESSAGE = "\nPlease re-enter command with appropriate units.";
    public static final String MENU_FILE_NOT_FOUND_MESSAGE = "Menu data was not found!\n"
            + "No worries, new menu has been created";
    public static final String PANTRY_FILE_NOT_FOUND_MESSAGE = "Pantry stock data was not found!\n"
            + "No worries, new pantry has been created";
    public static final String ORDER_LIST_FILE_NOT_FOUND_MESSAGE = "Order list data was not found!\n"
            + "No worries, new order list has been created";
    public static final String INVALID_SHOW_SALE_DAY_FORMAT_MESSAGE = "Error: " +
            "Incorrect format for the show_sale command.\n";
    public static final String INVALID_DAY_FORMAT = "Sorry, please enter a valid integer(>0)"
            + "for the 'day' field!";
    public static final String EDIT_SAME_PRICE = "New price is exactly the same as old price, "
            + "is that what you want?";
    public static final String INVALID_DISH_INDEX_TO_LIST = "Please enter a valid integer (>0) "
            + "for the 'index' field.";
    public static final String UNLISTED_DISH = "Oh no, this dish does not exist! \n"
            + "Please run the command list_menu to view the existing dishes.";
    public static final String INVALID_SALE_DAY = "Oh no, we have not reached the day entered!";
    public static final String EMPTY_UNIT_MESSAGE = "Unit cannot be empty! Please use either g or ml :)";
    public static final String INVALID_UNIT_MESSAGE = "Invalid unit! Please use either g or ml :)";
    public static final String INVALID_INGREDIENT_ARGUMENTS = "Invalid arguments for ingredients!\n"
            + "Ingredient format: ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY"
            + "[, ingredient/INGREDIENT2_NAME, qty/INGREDIENT2_QTY...]\n"
            + "Example: ingredient/milk qty/200ml, ingredient/chicken qty/100g";
    public static final String INVALID_INGREDIENT_QTY = "Quantity out of range! Quantity range is 1 to 1000000 :)";
    public static final String MISSING_PRICE = "Did you forget to include price? Just a reminder: "
            + "price can only have up to 2 decimal place!";
    public static final String MISSING_DISH_IN_EDIT_PRICE = "Sorry, I didnt catch the dish index, "
            + "did you forget to include it in your command?";
    public static final String INVALID_MENU_DATA = "menu.txt: Invalid format, this dish will be removed -> ";
    public static final String NAME_CANNOT_CONTAIN_SPECIAL_CHAR = "Is there a special character in the name?\n"
            + "I have poor memory and am unable to remember names with special characters, could you remove them?";
    public static final String REPEATED_INGREDIENT_NAME = "Error: there's a repeat in ingredient name "
            + "for the add command input";
    public static final String NULL_STRING_IN_REPEAT_ARGUMENT = "Null string detected in isRepeatedArgument function";
    public static final String INVALID_SALES_DATA = "orders.txt: Invalid format, this order will be removed -> ";
    public static final String INVALID_ORDER_STATUS = "orders.txt: Invalid status, this order will be removed -> ";
    public static final String INVALID_ORDER_QTY = "orders.txt: Invalid quantity (order quantity has to be more than 0)"
            + ", this order will be removed -> ";
    public static final String INVALID_DISH_PRICE = "orders.txt: Invalid dish price, this order will be removed -> ";
    public static final String WRONG_LIST_TOTAL_SALES_FORMAT = "Invalid list_total_sales command format!\n"
            + ListTotalSalesCommand.MESSAGE_USAGE;
    public static final String MISSING_FILEPATH = "Error in FileManager: No File Path entered";
    public static final String MISSING_INGREDIENT_MENU_DATA = "menu.txt: Missing ingredients, "
            + "this dish will be removed -> ";
    public static final String DISH_INDEX_NOT_INT = "Sorry! Dish index has to be an int!";
    public static final String EXCEED_MAX_ORDER_QTY = "Order quantity too high! "
            + "(order quantity has to be between 1 - 10000)";
    public static final String BELOW_MIN_ORDER_QTY = "Order quantity cannot be less than 1! "
            + "(order quantity has to be between 1 - 10000)";
    public static final String MISSING_ORDER_QTY = "Seems like you forgot to add the order quantity";
    public static final String INVALID_INT_ORDER_QTY = "Order quantity is invalid! "
            + "(order quantity has to be between 1 - 10000)";
}
