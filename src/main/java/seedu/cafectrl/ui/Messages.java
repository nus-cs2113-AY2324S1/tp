package seedu.cafectrl.ui;

public class Messages {

    /** Greeting messages */
    public static final String LINE_STRING = "------------------------------------------------------------------------";
    public static final String EQUAL_LINE_STRING = "= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = "
            + "= = = =";

    public static final String GOODBYE_MESSAGE = "Goodbye <3 Have a great day ahead!";

    public static final String LOGO =
            "     _/_/_/                _/_/              _/_/_/    _/                _/   \n" +
            "  _/          _/_/_/    _/        _/_/    _/        _/_/_/_/  _/  _/_/  _/    \n" +
            " _/        _/    _/  _/_/_/_/  _/_/_/_/  _/          _/      _/_/      _/     \n" +
            "_/        _/    _/    _/      _/        _/          _/      _/        _/      \n" +
            " _/_/_/    _/_/_/    _/        _/_/_/    _/_/_/      _/_/  _/        _/       ";

    public static final String WELCOME_MESSAGE = "Hello! Welcome to \n" + LOGO;

    /** Messages for edit price command */
    public static final String PRICE_MODIFIED_MESSAGE = "Price modified for the following dish: ";

    /** Messages for list menu command */
    public static final String LIST_MENU_MESSAGE = "|        Ah, behold, the grand menu of delights!        |";
    public static final String MENU_EMPTY_MESSAGE = "It seems our menu is currently taking a break. "
            + "Let's give it a wake-up call and fill 'er up with delectable delights, shall we?";
    public static final String MENU_END_CAP = "+-------------------------------------------------------+";
    public static final String MENU_CORNER = "+----------------------------------------+--------------+";
    public static final String MENU_TITLE = "| Dish Name                              |  Price       |";

    /** Messages for add dish command */
    public static final String ADD_DISH_MESSAGE = "You have added the following dish...";

    /** Messages for view stock command */
    public static final String EMPTY_STOCK = "Sorry! Pantry is currently empty!";
    public static final String VIEW_STOCK_MESSAGE = "| You have the following ingredients in pantry:         |";
    public static final String VIEW_STOCK_TITLE_MESSAGE = "| Ingredients                            |  Qty         |";

    /** Messages for help command */
    public static final String LIST_OF_COMMANDS = "These are all the commands I recognise: ";
    public static final String INSTRUCTION_ON_COMMAND_FORMAT = "- Words in UPPER_CASE are "
            + "the parameters to be supplied by the user.\n"
            + " e.g. in add name/NAME, NAME is a parameter that can be used as add name/Chicken.\n"
            + "- Parameters in [] are optional.";

    /** Messages for show_sales command */
    public static final String SHOW_SALES_DAY_PART_1 = "| Day ";
    public static final String SHOW_SALES_DAY_PART_2 = ":                          "
            + "                                          |";
    public static final String SHOW_SALES_TITLE = "| Dish Name                              "
            + "|  Dish Qty    |  Total Cost Price |";
    public static final String SHOW_SALES_END_CAP = "+---------------------------------------"
            + "------------------------------------+";
    public static final String SHOW_SALES_CORNER = "+----------------------------------------"
            + "+--------------+-------------------+";

    /** Messages for order command */
    public static final String CHEF_MESSAGE = "I'm busy crafting your selected dish "
            + "in the virtual kitchen of your dreams. Bon appétit!";
    public static final String PREVIOUS_DAY_TIME_TRAVEL = "Whoa there, time traveler! " +
            "Unfortunately, the DeLorean can't take us back to the previous day because it's already Day 1, " +
            "and there's no rewind button in this space-time continuum!";
    public static final String PREVIOUS_DAY_COMMAND_MESSAGE = "Sure thing! "
            + "Let's rev up the virtual DeLorean and take a spin to the previous day. "
            + "Buckle up, it's time to hit that rewind button!";
    public static final String NEXT_DAY_COMMAND_MESSAGE = "Prepare for liftoff! "
            + "We're about to fast-forward to the next day. "
            + "Hold onto your hats; here we go!";
    public static final String INITIALISE_STORAGE_MESSAGE = "...Downloading data...";

    /** Messages for restocking ingredients */
    public static final String AVAILABLE_DISHES = "Listed below are the availability of the dishes for the next order!";
    public static final String COMPLETE_ORDER = "Order is ready!";
    public static final String INCOMPLETE_ORDER = "Please restock ingredients before preparing the order :) ";
    public static final String RESTOCK_END_CAP = "+-----------------------------------------"
            + "-----------------------------+";
    public static final String RESTOCK_CORNER =  "+----------------------------------------+"
            + "--------------+--------------+";
    public static final String RESTOCK_TITLE =   "| Restock                                | "
            + "Current      | Needed       |";

    /** Messages for list ingredients */
    public static final String INGREDIENTS_END_CAP = "+-------------------------------------------------------+";
    public static final String INGREDIENTS_CORNER =  "+----------------------------------------+--------------+";
    public static final String INGREDIENTS_TITLE =   "| Ingredient                             + Quantity     |";
    /** Messages for decoder **/
    public static final String INVALID_DISH = " does not exist in our menu. \n"
            + "You might have tempered with the file and added in a non existing dish.\n"
            + "Don't worry :D , we will continue operations without ";

    public static final String SAVE_FILE_TAMPER_DETECTED = "Well, well, well, "
            + "looks like someone's been playing with the save files!\n"
            + "Let's keep it classy and use the prescribed format below.\n";

    public static final String SAVE_FILE_FORMAT_MENU = "Format for Menu.txt: \n"
            + "{Dish Name} | {Dish Price} | {Ingredient Name} - {Ingredient Qty} - {Ingredient Unit} |";
    public static final String SAVE_FILE_FORMAT_ORDERS = "Format for Orders.txt: \n"
            + "{Order Day} | {Dish Name} | {Dish Order Qty} | {Dish Price} | {Order Complete Status}";
    public static final String SAVE_FILE_FORMAT_PANTRY_STOCK = "Format for Pantry_stock.txt: \n"
            + "{Ingredient Name} | {Ingredient Qty} | {Ingredient Unit}";
    public static final String SALES_LAST_DAY_TEXT_TAMPERED = "Unfortunately, any empty order lists after "
            + "the latest valid order have been wiped from my memory banks."
            + " Poof!" ;

    public static final String SALES_ORDER_TEXT_TAMPERED = "Unfortunately, any orders not following the format "
            + "and invalid have been wiped from my memory banks. Poof!" ;

    public static final String HASH_STRING_TAMPERED = "Alert! It appears the hash string "
            + "got a makeover without permission.";
    public static final String HASH_STRING_MESSAGE = "Please resist the temptation to play Picasso with this string.";

    public static final String DONE_LOADING_MENU = "Done loading menu.txt!";
    public static final String DONE_LOADING_PANTRY_STOCK = "Done loading pantry_stock.txt!";
    public static final String DONE_LOADING_SALES = "Done loading orders.txt!";
}
