package seedu.stocker.exceptions;

/**
 * Signals that the laod function cannot read the serialized drug.
 */
public class InvalidDrugFormatException extends Exception {
    public InvalidDrugFormatException(String message) {
        super(message);
    }
}
