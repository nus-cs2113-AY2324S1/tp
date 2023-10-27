package essenmakanan.exception;

public class EssenOutOfRangeException extends EssenException {
    public void handleException() {
        System.out.println("Your input does not exist in our database!");
    }
}
