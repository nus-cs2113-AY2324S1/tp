package essenmakanan.exception;

public class EssenInvalidUnitException extends Exception {

    public static void handleException(String unit, String dataString) {
        System.out.println("Invalid unit enum: " + unit);
        System.out.println("Located at: " + dataString);
    }
}
