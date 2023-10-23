package fittrack.parser;

public class IndexOutOfBoundsException extends ParseException{
    public IndexOutOfBoundsException() {
        super("Index is out of range.");
    }
}
