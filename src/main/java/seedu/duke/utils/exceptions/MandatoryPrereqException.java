package seedu.duke.utils.exceptions;

//@@author SebasFok
/**
 * Custom exception to indicate that a module, X, is a mandatory prerequisite for another module, Y.
 * This exception should be thrown when attempting an action that causes the module X to move to the same
 * semester or after module Y.
 *
 */
public class MandatoryPrereqException extends Exception{
    public MandatoryPrereqException(String message) {
        super(message);
    }
}
