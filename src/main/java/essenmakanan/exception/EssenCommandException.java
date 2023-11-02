package essenmakanan.exception;

public class EssenCommandException extends EssenException {
    public void handleException() {
        System.out.println("Invalid command type");
    }
}
