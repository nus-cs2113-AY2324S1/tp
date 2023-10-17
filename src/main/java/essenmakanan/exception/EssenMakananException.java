package essenmakanan.exception;

public class EssenMakananException extends Exception {
    public EssenMakananException(String message) {
        super("EssenMakanan Exception! " + message);
    }
}
