package essenmakanan.exception;

import essenmakanan.EssenMakanan;

public class EssenMakananException extends Exception {
    public EssenMakananException(String message) {
        super("EssenMakanan Exception! " + message);
    }
}
