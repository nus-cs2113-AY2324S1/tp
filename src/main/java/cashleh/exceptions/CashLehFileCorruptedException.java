package cashleh.exceptions;

public class CashLehFileCorruptedException extends CashLehException {
    /**
     * Constructs a new <code>CashLehFileCorruptedException</code> with the specified error message to be displayed.
     *
     * @param message the error message associated with the exception.
     */
    public CashLehFileCorruptedException(String message) {
        super(message);
    }
}
