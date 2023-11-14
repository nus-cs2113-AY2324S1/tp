package essenmakanan.exception;

import essenmakanan.ui.Ui;

/**
 * Indicates an error during editing a shortcut.
 */
public class EssenEditShortcutException extends Exception {

    private String scenario;

    /**
     * Creates the exception.
     *
     * @param scenario The type of scenario.
     */
    public EssenEditShortcutException(String scenario) {
        this.scenario = scenario;
    }

    /**
     * Sends out the message based on the scenario.
     */
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
