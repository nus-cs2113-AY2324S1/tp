package seedu.financialplanner.ui;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void welcomeMessage() {
        showMessage("\tWelcome to your Financial Planner. Type something to get started.");
    }

    public void exitMessage() {
        showMessage("\tExiting Financial Planner. Goodbye.");
    }

    public String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }
}
