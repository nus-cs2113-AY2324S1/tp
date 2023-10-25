package essenmakanan.exception;

public class EssenMakananNullInputException extends EssenMakananException{

    private final String NULL_INPUT_ERROR_MESSAGE = " The input can't be empty, please type something"
        + System.lineSeparator();

    public EssenMakananNullInputException() {
        System.out.println(getMessage() + NULL_INPUT_ERROR_MESSAGE);
    }

    public EssenMakananNullInputException(String message) {
        super(message);
    }
}
