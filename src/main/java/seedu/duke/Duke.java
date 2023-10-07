package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " _____ _ _   _   _ _   _ ____  \n"
                + "|  ___(_) |_| \\ | | | | / ___| \n"
                + "| |_  | | __|  \\| | | | \\___ \\ \n"
                + "|  _| | | |_| |\\  | |_| |___) |\n"
                + "|_|   |_|\\__|_| \\_|\\___/|____/ \n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (!userInput.equals("exit")) {
            System.out.println("You said: " + userInput);
            userInput = in.nextLine();
        }
        System.out.println("Goodbye now!");
    }
}
