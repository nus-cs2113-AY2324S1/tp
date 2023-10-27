package essenmakanan.exception;

public class EssenMakananOutOfRangeException extends EssenMakananException {
    public void handleException() {
        System.out.println("Your input does not exist in our database!");
    }
}
