package essenmakanan.exception;

public class EssenShortcutException extends Exception {

    public void handleException() {
        System.out.println("Shortcut cannot be created for a non-existing ingredient");
    }
}
