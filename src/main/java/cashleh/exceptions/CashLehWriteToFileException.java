package cashleh.exceptions;

public class CashLehWriteToFileException extends CashLehException {
    /**
     * Constructs a new <code>CashLehWriteToFileException</code> with the specified error message to be displayed.
     */
    public CashLehWriteToFileException() {
        super("Walao, file saving error! Comfirm got something wrong. Check your files again!");
    }
}
