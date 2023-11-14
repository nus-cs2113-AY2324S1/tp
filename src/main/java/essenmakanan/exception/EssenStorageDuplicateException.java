package essenmakanan.exception;

public class EssenStorageDuplicateException extends EssenException {

    public void handleException(String data) {
        System.out.println("Duplicated data founded: " + data + ". Skipping data.");
    }
}
