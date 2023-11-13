package essenmakanan.exception;

public class EssenStorageDuplicateException extends Exception {

    public void handleException(String data) {
        System.out.println("Duplicated data founded: " + data + ". Skipping data.");
    }
}
