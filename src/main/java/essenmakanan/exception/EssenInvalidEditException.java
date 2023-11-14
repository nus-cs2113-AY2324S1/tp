package essenmakanan.exception;

public class EssenInvalidEditException extends EssenException {
    /**
     * Handles the exception when the edit command is invalid
     */
    public void handleException() {
        System.out.println("Details to edit is invalid.");
    }
}
