package essenmakanan.exception;

public class EssenStorageFormatException extends EssenException {

    public void handleException(String dataString) {
        System.out.println("Invalid data format found in text file. This data will be skipped: '" + dataString + "'");
    }
}
