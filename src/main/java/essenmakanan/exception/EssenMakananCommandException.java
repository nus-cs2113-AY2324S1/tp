package essenmakanan.exception;

public class EssenMakananCommandException extends Exception {
    public void handleException() {
        System.out.println("Invalid command type");
    }
}
