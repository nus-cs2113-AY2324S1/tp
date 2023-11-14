package essenmakanan.exception;

import essenmakanan.ui.Ui;

/**
 * Indicates an error caused by invalid quantity.
 */
public class EssenInvalidQuantityException extends Exception {

    /**
     * Sends out a message that the quantity is invalid.
     */
    public static void handleException() {
        Ui.drawDivider();
        System.out.println("Please put a non-zero positive number as the quantity");
        Ui.drawDivider();
    }
}
