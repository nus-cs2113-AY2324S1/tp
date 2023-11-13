package essenmakanan.exception;

public class EssenCommandException extends EssenException {
    public void handleException() {
        System.out.println("Invalid command type. Please refer to user guide for full list of commands.");
    }
}
