package seedu.cafectrl.ui;

public class Messages {
    public static final String LINE_STRING = "-----------------------------------------------------";
    /**
     * Greeting messages
     */
    public static final String WELCOME_MESSAGE = "Hello! Welcome to CafeCTRL!";

    public static final String GOODBYE_MESSAGE = "Goodbye <3 Have a great day ahead!";

    /**
     * Messages for edit price command
     */
    public static final String PRICE_MODIFIED_MESSAGE = "Price modified for the following dish: ";

    /**
     * Messages for list menu command
     */
    public static final String LIST_MENU_MESSAGE = "| Ah, behold, the grand menu of delights! |";
    public static final String MENU_EMPTY_MESSAGE = "It seems our menu is currently taking a break. "
            + "Let's give it a wake-up call and fill 'er up with delectable delights, shall we?";
    public static final String MENU_TOP = "+-----------------------------------------+";
    public static final String MENU_CORNER = "+--------------------------+--------------+";
    public static final  String MENU_TITLE = "| Dish Name                |  Price       |";

    /**
     * Messages for add dish command
     */
    public static final String ADD_DISH_MESSAGE = "You have added the following dish...";

    /**
     * Messages for help command
     */
    public static final String LIST_OF_COMMANDS = "These are all the commands I recognise: ";
    public static final String INSTRUCTION_ON_COMMAND_FORMAT = "(- Words in UPPER_CASE are "
            + "the parameters to be supplied by the user.\n"
            + "e.g. in add name/NAME, NAME is a parameter that can be used as add name/Chicken.\n"
            + "- Parameters in [] are optional.)";
    public static final String ADD_DISH_GUIDE = "To add a new dish to the menu: "
            + "add name/NAME price/PRICE ingredient/INGREDIENT1_NAME qty/INGREDIENT1_QTY"
            + "[, ingredient/INGREDIENT2_NAME qty/INGREDIENT2_QTY\n"
            + "Example: add name/chicken rice price/3 ingredient/rice qty/100, ingredient/chicken qty/100";
    public static final String LIST_MENU_GUIDE = "To list out all dishes on the menu: list_menu";
    public static final String LIST_INGREDIENTS_GUIDE = "To list out the ingredients needed "
            + "for a specific dish: list_ingredients DISH_INDEX\n"
            + "Example: list_ingredients 1";
    public static final String DELETE_GUIDE = "To delete a menu item "
            + "delete DISH_INDEX\n"
            + "Example: delete 1";
    public static final String EDIT_PRICE_GUIDE = "To edit price of a menu item: "
            + "edit_price index/DISH_INDEX price/NEW_PRICE\n"
            + "Example: edit_price index/1 price/4.50";

    /**
     * Error messages
     */
    public static final String MISSING_ARGUMENT_FOR_EDIT_PRICE = "Error: Missing arguments "
            + "for edit price command.";
    public static final String MISSING_ARGUMENT_FOR_LIST_INGREDIENTS = "Error: Missing arguments "
            + "for list ingredients command.";
    public static final String MISSING_ARGUMENT_FOR_DELETE = "Error: Missing arguments "
            + "for delete command.";
    public static final String WRONG_ARGUMENT_TYPE_FOR_EDIT_PRICE = "Error: Invalid dish index. \n "
            + "Make sure dish index is "
            + "of type int and price is of type float!";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Error: Unknown command. "
            + "Type 'help' to view the accepted list of commands";
    public static final String INVALID_DISH_INDEX = "Error: Invalid dish index.";
}
