package essenmakanan.exception;

import essenmakanan.ui.Ui;

public class EssenShortcutException extends EssenException {

    public void handleException() {
        Ui.drawDivider();
        System.out.println("Shortcut cannot be created for a non-existing ingredient or an ingredient that has"
                + " a shortcut assigned to it.");
        Ui.drawDivider();
    }
}
