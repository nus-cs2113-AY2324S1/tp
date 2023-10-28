package fittrack.parser;

public class NumberFormatException extends ParseException {
    public NumberFormatException() {
        super("Argument is not an integer.");
    }
}
