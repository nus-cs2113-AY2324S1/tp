package seedu.stocker.exceptions;

/**
 * Signals that the drug has not been found in a specific list.
 */
public class DrugNotFoundException extends Exception {
    public DrugNotFoundException(String message) {
        super(message);
    }
}
