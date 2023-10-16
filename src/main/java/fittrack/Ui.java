package fittrack;

import fittrack.command.CommandResult;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * Represents the user interface of FitTrack.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = "___________.__  __ ___________                     __\n"
                                     + "\\_   _____/|__|/  |\\__    ___/___________    ____ |  | __\n"
                                     + " |    __)  |  \\   __\\|    |  \\_  __ \\__  \\ _/ ___\\|  |/ /\n"
                                     + " |     \\   |  ||  |  |    |   |  | \\/ __ \\  \\___|    <\n"
                                     + " \\___  /   |__||__|  |____|   |__|  (____  /\\___  >__|_ \\";


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
        System.out.println(LOGO);
    }

    public void printCommandResult(CommandResult commandResult) {
        System.out.println(commandResult.getFeedback());
        printBlankLine();
    }

    /**
     * Prints greetings to user and the height and weight that
     * the user has entered.
     *
     * @param profile array containing the height and weight
     */
    public void printProfileDetails(double[] profile) {
        System.out.println("Here are your profile settings");
        System.out.println("Height: " + profile[0]);
        System.out.println("Weight: " + profile[1]);
    }
}
