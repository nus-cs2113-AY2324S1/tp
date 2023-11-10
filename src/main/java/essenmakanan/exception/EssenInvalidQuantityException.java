package essenmakanan.exception;

public class EssenInvalidQuantityException extends Exception {

    public static void handleException() {
        System.out.println("Please put a non-zero positive number as the quantity");
    }
}
