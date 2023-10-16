package fittrack;

import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.command.ExitCommand;
import fittrack.parser.CommandParser;
import fittrack.parser.PatternMatchFailException;

/**
 * Represents the main part of FitTrack.
 */
public class FitTrack {
    private UserProfile userProfile;
    private final MealList mealList;
    private final WorkList works;
    private final Ui ui;

    private FitTrack() {
        ui = new Ui();
        mealList = new MealList();
        works = new WorkList();
    }

    /**
     * Main entry-point for the FitTrack application.
     */
    public static void main(String[] args) {
        new FitTrack().run();
    }

    private void run() {
        start();
        loopCommandExecution();
        end();
    }

    private void start() {
        ui.printWelcome();
        try {
            profileSettings();
        } catch (PatternMatchFailException e) {
            System.out.println("Wrong format. h/<height> w/<weight>");
        }
    }

    private void loopCommandExecution() {
        Command command;
        do {
            String userCommandLine = ui.scanCommandLine();
            command = new CommandParser().parseCommand(userCommandLine);
            CommandResult commandResult = executeCommand(command);
            ui.printCommandResult(commandResult);


        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        command.setData(userProfile, mealList, works);
        return command.execute();
    }

    /**
     * Gets user profile details when program starts.
     */
    private void profileSettings() throws PatternMatchFailException {
        System.out.println("Please enter your name:");
        String name = ui.scanNextLine();
        System.out.println("Please enter your height (in cm) and weight (in kg):");
        String input = ui.scanNextLine();
        double[] profile;

        profile = new CommandParser().parseProfile(input);
        userProfile = new UserProfile(name, profile[0], profile[1]);
        ui.printProfileDetails(name, profile);

    }

    private void end() {
        ui.closeScanner();
    }
}
