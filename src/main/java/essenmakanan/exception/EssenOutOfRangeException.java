package essenmakanan.exception;

public class EssenOutOfRangeException extends EssenException {
    public void handleException() {
        System.out.println("Please enter a valid input that exist in our database!");
    }
}
