package essenmakanan.exception;

public class EssenMakananCommandException extends EssenMakananException {
    public void handleException() {
        System.out.println("Invalid command type");
    }
}
