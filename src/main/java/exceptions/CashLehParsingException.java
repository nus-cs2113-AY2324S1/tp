package exceptions;

public class CashLehParsingException extends CashLehException {
    /**
     * Constructs a new <code>CashLehParsingException</code> with the specified error message to be displayed.
     *
     * @param message the error message associated with the exception.
     */
    public CashLehParsingException(String message) {
        super(message);
    }
}
