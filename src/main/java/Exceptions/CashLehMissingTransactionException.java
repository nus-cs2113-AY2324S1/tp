package Exceptions;

public class CashLehMissingTransactionException extends CashLehException {
    /**
     * Constructs a new <code>CashLehMissingTransactionException</code> with the specified error message to be displayed.
     *
     * @param message the error message associated with the exception.
     */
    public CashLehMissingTransactionException() {
        super("No such transaction recorded leh!");
    }
}
