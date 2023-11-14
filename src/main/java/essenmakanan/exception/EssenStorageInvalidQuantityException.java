package essenmakanan.exception;

public class EssenStorageInvalidQuantityException extends EssenException {

    public void handleException(String data) {
        System.out.println("Invalid quantity detected on data : " + data);
    }
}
