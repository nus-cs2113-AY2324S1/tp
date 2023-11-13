package essenmakanan.exception;

import essenmakanan.ui.Ui;

public class EssenInvalidQuantityException extends Exception {

    public static void handleException() {
        Ui.drawDivider();
        System.out.println("Please put a non-zero positive number as the quantity");
        Ui.drawDivider();
    }
}
