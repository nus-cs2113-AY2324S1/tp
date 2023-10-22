package seedu.cafectrl.ui;

/**
 * Enumerates messages to be displayed to users.
 */
public enum UserOutput {
    WELCOME_MESSAGE("Hello! Welcome to CafeCTRL!"),
    GOODBYE_MESSAGE("Goodbye <3 Have a great day ahead!"),
    LIST_MESSAGE("Ah, behold, the grand menu of delights!"),
    MENU_EMPTY("It seems our menu is currently taking a break. "
            + "Let's give it a wake-up call and fill 'er up with delectable delights, shall we?"),
    MENU_TOP("+-----------------------------------------+"),
    MENU_CORNER("+--------------------------+--------------+"),
    MENU_TITLE("| Dish Name                |  Price       |"),
    LIST_MESSAGE_MENU("| Ah, behold, the grand menu of delights! |"),
    ADD_DISH_MESSAGE("You have added the following dish..."),
    UNKNOWN_COMMAND_MESSAGE("Your command has left me scratching my virtual head. Let's try that again, shall we?");

    public final String message;
    UserOutput(String message) {
        this.message = message;
    }
}
