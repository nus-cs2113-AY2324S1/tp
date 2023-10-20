package seedu.stocker.common;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_WELCOME = "Welcome to your Stocker!";
    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_LOGIN_WELCOME = "Welcome! \n"
            + "Key in register or login based on your needs \n"
            + "1.Register user \n"
            + "2.Login";
    public static final String MESSAGE_USERNAME_INPUT = "Enter your username:";
    public static final String MESSAGE_PASSWORD_INPUT = "Enter Your Password:";
    public static final String MESSAGE_SUCCESSFUL_REGISTRATION = "Registration successful.";
    public static final String MESSAGE_SUCCESSFUL_LOGIN = "Login successful.";
    public static final String MESSAGE_INVALID_CHOICE = "Invalid Input, enter register or login only!";
    public static final String MESSAGE_USER_ALREADY_EXIST = "User already exists. Please make user with "
            + "different name or login instead";
    public static final String MESSAGE_INVALID_USERNAME_OR_PASSWORD = "Invalid username or password. Please try again.";
    public static final String MESSAGE_NO_BLANK_NAME_ALLOWED = "No blank name allowed. "
            + "Enter your desired username again";
    public static final String MESSAGE_NO_BLANK_PASSWORD_ALLOWED = "No blank password allowed. "
            + "Enter your desired password again";
}
