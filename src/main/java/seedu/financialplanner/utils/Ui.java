package seedu.financialplanner.utils;

import java.util.Scanner;

public class Ui {
    private Scanner in = new Scanner(System.in);
    public Ui() {
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void welcomeMessage() {
        showMessage("Welcome to your Financial Planner. Type something to get started.");
    }

    public void exitMessage() {
        showMessage("Exiting Financial Planner. Goodbye.");
    }

    public String input() {
        return in.nextLine().trim();
    }
}
