package essenmakanan.exception;

public class EssenInvalidEnumException extends Exception {

    public static void handleException(String dataString) {
        System.out.println("Invalid enum at: " + dataString);
    }
}
