package essenmakanan.exception;

public class EssenDoesNotExistException extends EssenException {
    public void handleException() {
        System.out.println("Recipe/Ingredient does not exist.");
    }
}
