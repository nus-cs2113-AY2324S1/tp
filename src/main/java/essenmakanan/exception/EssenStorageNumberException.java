package essenmakanan.exception;

public class EssenStorageNumberException extends EssenException {

    public static void handleException(String data) {
        System.out.println("Quantity cannot be converted in: " + data);
    }
}
