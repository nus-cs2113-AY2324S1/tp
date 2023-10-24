package cashleh.exceptions;

public class CashLehReadFromFileException extends CashLehException{
    /**
     * Constructs a new <code>CashLehReadFromFileException</code> with the specified error message to be displayed.
     */
    public CashLehReadFromFileException() {
        super("Alamak! Something went wrong, try again lah.");
    }
}
