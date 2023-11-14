package essenmakanan.exception;

/**
 * Indicates an error that a input's format is incorrect.
 */
public class EssenFormatException extends EssenException {

    /**
     * Sends out a message that the format is incorrect.
     */
    public void handleException() {
        System.out.println("Format is incorrect.");
    }
}
