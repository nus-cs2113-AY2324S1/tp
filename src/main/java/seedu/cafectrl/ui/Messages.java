package seedu.cafectrl.ui;

public class Messages {

    /** Greeting messages */
    public static final String LINE_STRING = "-----------------------------------------------------";
    public static final String WELCOME_MESSAGE = "Hello! Welcome to CafeCTRL!";
    public static final String GOODBYE_MESSAGE = "Goodbye <3 Have a great day ahead!";

    /** Messages for edit price command */
    public static final String PRICE_MODIFIED_MESSAGE = "Price modified for the following dish: ";

    /** Messages for list menu command */
    public static final String LIST_MENU_MESSAGE = "| Ah, behold, the grand menu of delights! |";
    public static final String MENU_EMPTY_MESSAGE = "It seems our menu is currently taking a break. "
            + "Let's give it a wake-up call and fill 'er up with delectable delights, shall we?";
    public static final String MENU_END_CAP = "+-----------------------------------------+";
    public static final String MENU_CORNER = "+--------------------------+--------------+";
    public static final String MENU_TITLE = "| Dish Name                |  Price       |";

    /** Messages for add dish command */
    public static final String ADD_DISH_MESSAGE = "You have added the following dish...";
    public static final String REPEATED_DISH_MESSAGE = "Sorry, this dish name already exists.";

    /** Messages for view stock command */
    public static final String VIEW_STOCK = "You have the following ingredients in pantry:"
            + "\nIngredients\t\tQty";

    /** Messages for help command */
    public static final String LIST_OF_COMMANDS = "These are all the commands I recognise: ";
    public static final String INSTRUCTION_ON_COMMAND_FORMAT = "- Words in UPPER_CASE are "
            + "the parameters to be supplied by the user.\n"
            + " e.g. in add name/NAME, NAME is a parameter that can be used as add name/Chicken.\n"
            + "- Parameters in [] are optional.";

    /** Messages for order command */
    public static final String CHEF_MESSAGE = "I'm busy crafting your selected dish "
            + "in the virtual kitchen of your dreams. Bon appétit!";
    public static final String PREVIOUS_DAY_TIME_TRAVEL = "Whoa there, time traveler! " +
            "Unfortunately, the DeLorean can't take us back to the previous day because it's already Day 0, " +
            "and there's no rewind button in this space-time continuum!";
    public static final String PREVIOUS_DAY_COMMAND_MESSAGE = "Sure thing! "
            + "Let's rev up the virtual DeLorean and take a spin to the previous day. "
            + "Buckle up, it's time to hit that rewind button!";
    public static final String NEXT_DAY_COMMAND_MESSAGE = "Prepare for liftoff! "
            + "We're about to fast-forward to the next day. "
            + "Hold onto your hats; here we go!";
    public static final String INITIALISE_STORAGE_MESSAGE = "...Downloading data...";

}
