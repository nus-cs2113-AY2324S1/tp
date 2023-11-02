package essenmakanan.exception;

public class EssenNullInputException extends EssenException {

    private final String errorMessage = " The input can't be empty, please type something"
        + System.lineSeparator();

    public EssenNullInputException() {
        System.out.println(getMessage() + errorMessage);
    }

    public EssenNullInputException(String message) {
        super(message);
    }
}
