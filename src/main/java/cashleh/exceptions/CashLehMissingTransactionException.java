package cashleh.exceptions;

public class CashLehMissingTransactionException extends CashLehException {
    /**
     * Constructs a new <code>CashLehMissingTransactionException</code>
     * with the specified error message to be displayed.
     *
     */
    public CashLehMissingTransactionException() {
        super("No such transaction recorded leh!");
    }
}
