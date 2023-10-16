package seedu.duke.ui;

/**
 * Enumerates messages to be displayed to users.
 */
public enum UserOutput {
    WELCOME_MESSAGE("Hello! Welcome to CafeCTRL!"),
    GOODBYE_MESSAGE("Goodbye <3 Have a great day ahead!"),
    ADD_DISH_MESSAGE("You have added the following dish...");

    public final String message;
    UserOutput(String message) {
        this.message = message;
    }
}
