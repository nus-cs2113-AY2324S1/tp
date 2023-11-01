package seedu.cafectrl.parser.exception;
/**
 * Represents a parse error encountered by parser.
 */
public class ParserException extends Exception {
    /**
     * @param errorMessage contains relevant information on failed constraint(s)
     */
    public ParserException(String errorMessage) {
        super(errorMessage);
    }
}
