package essenmakanan.exception;

public class EssenMakananException extends Exception {

    public EssenMakananException() {
        getMessage();
    }
    public EssenMakananException(String message) {
        System.out.println("EssenMakanan Exception! " + message);
    }

    @Override
    public String getMessage() {
        return ("EssenMakanan Exception!");
    }
}
