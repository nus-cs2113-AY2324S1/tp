package essenmakanan.exception;

/**
 * Indicates an error caused by non-existing recipe/ingredient .
 */
public class EssenDoesNotExistException extends EssenException {

    /**
     * Sends out a message that the recipe/ingredient does not exist.
     */
    public void handleException() {
        System.out.println("Recipe/Ingredient does not exist.");
    }
}
