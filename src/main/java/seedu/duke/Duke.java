package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println("Sorry, I don't understand what you mean.");
            } 
        } while (!input.equals("bye"));
        
    }
}
