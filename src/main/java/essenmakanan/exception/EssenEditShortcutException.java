package essenmakanan.exception;

import essenmakanan.ui.Ui;

public class EssenEditShortcutException extends Exception {

    private String scenario;

    public EssenEditShortcutException(String scenario) {
        this.scenario = scenario;
    }

    public void handleException() {
        Ui.drawDivider();

        switch (scenario) {
        case "same name":
            System.out.println("You can't edit shortcut's name with the same name.");
            break;
        case "same quantity":
            System.out.println("You can't edit shortcut's quantity with the same quantity.");
            break;
        case "usage":
            System.out.println("You can't edit a shortcut's property more than once in a single line.");
            break;
        default:
            System.out.println("Invalid scenario for exception!");
        }

        Ui.drawDivider();
    }
}
