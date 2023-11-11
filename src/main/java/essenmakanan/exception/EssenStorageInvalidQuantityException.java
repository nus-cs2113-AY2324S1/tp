package essenmakanan.exception;

public class EssenStorageInvalidQuantityException extends Exception {

    public void handleException(String data) {
        System.out.println("Invalid quantity detected on data : " + data);
    }
}
