package seedu.cafectrl.ui;

public class Messages {

    // Error messages
    public static final String MISSING_ARGUMENT_FOR_EDIT_PRICE = "Error: Missing arguments "
                                                                 + "for edit price command.";

    public static final String MISSING_ARGUMENT_FOR_LIST_INGREDIENTS = "Error: Missing arguments "
                                                                 + "for list ingredients command.";

    public static final String MISSING_ARGUMENT_FOR_DELETE = "Error: Missing arguments "
                                                                 + "for delete command.";

    public static final String MISSING_ARGUMENT_FOR_BUY_INGREDIENT = "Error: Missing arguments "
                                                                 + "for buy ingredient command.";

    public static final String INVALID_ARGUMENT_FOR_BUY_INGREDIENT = "Error: Invalid arguments "
                                                                 + "for buy ingredient command.";

    public static final String INVALID_DISH_INDEX = "Error: Invalid dish index.";
    public static final String WRONG_ARGUMENT_TYPE_FOR_EDIT_PRICE = "Error: Invalid dish index. \n "
                                                                    + "Make sure dish index is "
                                                                    + "of type int and price is of type float!";

    public static final String UNKNOWN_COMMAND_MESSAGE = "Error: Unknown command. "
                                                        + "Type 'help' to view the accepted list of commands";

    // Ui messages
    public static final String PRICE_MODIFIED_MESSAGE = "Price modified for the following dish: ";

    public static final String WELCOME_MESSAGE = "Hello! Welcome to CafeCTRL!";

    public static final String GOODBYE_MESSAGE = "Goodbye <3 Have a great day ahead!";

    public static final String LIST_MENU_MESSAGE = "| Ah, behold, the grand menu of delights! |";
    public static final String MENU_EMPTY_MESSAGE = "It seems our menu is currently taking a break. "
                       + "Let's give it a wake-up call and fill 'er up with delectable delights, shall we?";
    public static final String MENU_TOP = "+-----------------------------------------+";
    public static final String MENU_CORNER = "+--------------------------+--------------+";
    public static final String MENU_TITLE = "| Dish Name                |  Price       |";

    public static final String ADD_DISH_MESSAGE = "You have added the following dish...";

    public static final String VIEW_STOCK = "You have the following ingredients in pantry:";

}
