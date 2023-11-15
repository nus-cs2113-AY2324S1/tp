package essenmakanan.exception;

public class EssenException extends Exception {

    public EssenException() {
        getMessage();
    }
    public EssenException(String message) {
        System.out.println("EssenMakanan Exception! " + message);
    }

    @Override
    public String getMessage() {
        return ("EssenMakanan Exception!");
    }

    public void handleException() {
        System.out.println("EssenMakanan Exception!");
    }
}
