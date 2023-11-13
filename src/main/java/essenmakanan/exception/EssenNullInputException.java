package essenmakanan.exception;

public class EssenNullInputException extends EssenException {

    private final String errorMessage = "Something is missing, please check your input"
        + System.lineSeparator();

    public EssenNullInputException() {
        System.out.println(getMessage() + errorMessage);
    }

    public EssenNullInputException(String message) {
        super(message);
    }
}
