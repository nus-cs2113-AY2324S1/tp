package seedu.duke.utils.exceptions;

//@@author ryanlohyr
/**
 * This class represents a custom exception that is thrown when an invalid module is encountered.
 * An invalid module that has illegal characters.
 */
public class InvalidModuleCodeException extends Exception {
    public InvalidModuleCodeException() {
        super("Invalid Module Name");
    }

}
