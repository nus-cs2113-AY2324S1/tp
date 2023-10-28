package essenmakanan.exception;

public class EssenNullInputException extends EssenException {

    private final String NULL_INPUT_ERROR_MESSAGE = " The input can't be empty, please type something"
        + System.lineSeparator();

    public EssenNullInputException() {
        System.out.println(getMessage() + NULL_INPUT_ERROR_MESSAGE);
    }

    public EssenNullInputException(String message) {
        super(message);
    }
}
