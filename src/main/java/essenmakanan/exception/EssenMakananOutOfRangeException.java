package essenmakanan.exception;

public class EssenMakananOutOfRangeException extends Exception {
    public void handleException() {
        System.out.println("Your input does not exist in our database!");
    }
}
