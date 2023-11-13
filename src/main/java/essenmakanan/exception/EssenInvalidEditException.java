package essenmakanan.exception;

public class EssenInvalidEditException extends EssenException {
    public void handleException() {
        System.out.println("Details to edit is invalid.");
    }
}
