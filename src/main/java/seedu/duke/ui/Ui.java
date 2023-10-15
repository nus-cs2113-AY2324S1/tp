package seedu.duke.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a UI instance with a Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String receiveUserInput() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(UserOutput.WELCOME_MESSAGE.message);
    }

    public void showGoodbye() {
        System.out.println(UserOutput.GOODBYE_MESSAGE.message);
    }

    /**
     * Displays an error message.
     * We will probably use the function when we perform
     * error handling to display the messages to the user.
     * Though it may not be used in V0.1, I think there's
     * no harm adding it in for now! :)
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
