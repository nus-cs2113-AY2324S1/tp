package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        greetUser();
        String name = in.nextLine().strip();
        System.out.println("Hi, " + name + ", what would you like to do?");
        String userInput = in.nextLine().strip().toLowerCase();

        while (!userInput.equals("exit")) {
            if (userInput.equals("hello")) {
                System.out.println("Hello to you as well, " + name + "!");
            } else {
                System.out.println(name + " said: " + userInput);
            }
            userInput = in.nextLine().strip().toLowerCase();
        }
        System.out.println("Goodbye now!");
    }

    public static void greetUser() {
        String logo = " _____ _ _   _   _ _   _ ____  \n"
                + "|  ___(_) |_| \\ | | | | / ___| \n"
                + "| |_  | | __|  \\| | | | \\___ \\ \n"
                + "|  _| | | |_| |\\  | |_| |___) |\n"
                + "|_|   |_|\\__|_| \\_|\\___/|____/ \n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
    }
}
