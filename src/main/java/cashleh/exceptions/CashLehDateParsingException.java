package cashleh.exceptions;

public class CashLehDateParsingException extends CashLehParsingException {
    /**
     * Constructs a new <code>CashLehDateParsingException</code> with error
     * message explaining date parsing format.
     *
     */
    public CashLehDateParsingException() {
        super("Date format is invalid leh! Use DD/MM/YYYY can or not?");
    }
}
