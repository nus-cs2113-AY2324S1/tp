package fittrack;

import fittrack.command.CommandResult;

import java.util.Scanner;

/**
 * Represents the user interface of FitTrack.
 */
public class Ui {
    private final Scanner in;

    /**
     * Constructs UI of FitTrack.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Scans a line from the user input.
     *
     * @return user input as a line of string
     */
    public String scanNextLine() {
        return in.nextLine();
    }

    /**
     * Scans a command line from the user input.
     *
     * @return command line as a line of string
     */
    public String scanCommandLine() {
        return scanNextLine();
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printWelcome() {
        System.out.println("Welcome to FitTrack!");
    }

    public void printCommandResult(CommandResult commandResult) {
        System.out.println(commandResult.getFeedback());
        printBlankLine();
    }

    /**
     * Prints greetings to user and the profile of the user.
     *
     * @param profile user profile
     */
    public void printProfileDetails(UserProfile profile) {
        System.out.println("Here are your profile settings.");
        System.out.println("Height: " + profile.getHeight());
        System.out.println("Weight: " + profile.getWeight());
        System.out.println("Daily calorie surplus limit: " + profile.getDailyCalorieLimit());
    }
}
