package seedu.duke.data.exception;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends IncorrectFormatException {

    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalValueException(String message) {
        super(message);
    }
}
