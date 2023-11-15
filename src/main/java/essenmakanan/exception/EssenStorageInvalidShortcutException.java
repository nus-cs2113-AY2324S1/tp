package essenmakanan.exception;

public class EssenStorageInvalidShortcutException extends EssenException {

    public void handleException(String data) {
        System.out.println("Invalid shortcut due to no matching ingredient in data: " + data);
    }
}
