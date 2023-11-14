package essenmakanan.exception;

/**
 * Indicates an error caused by invalid command being inputted.
 */
public class EssenCommandException extends EssenException {

    /**
     * Sends out a message that the command type is invalid.
     */
    public void handleException() {
        System.out.println("Invalid command type. Please refer to user guide for full list of commands.");
    }
}
