package essenmakanan.exception;

/**
 * Indicates an error caused by invalid enum.
 */
public class EssenInvalidEnumException extends Exception {

    /**
     * Sends out a message that the data detail cannot be made into an enum.
     *
     * @param dataString The data with invalid enum.
     */
    public static void handleException(String dataString) {
        System.out.println("Invalid enum at: " + dataString);
    }
}
