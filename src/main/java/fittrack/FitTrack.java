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
    private final UserProfile userProfile;
    private final MealList mealList;
    private final WorkoutList workoutList;
    private final Ui ui;

    private FitTrack() {
        ui = new Ui();

        userProfile = new UserProfile();
        mealList = new MealList();
        workoutList = new WorkoutList();
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
        command.setData(userProfile, mealList, workoutList);
        return command.execute();
    }

    /**
     * Gets user profile details when program starts.
     */
    private void profileSettings() throws PatternMatchFailException {
        System.out.println("Please enter your name:");
        String name = ui.scanNextLine();
        userProfile.setName(name);

        System.out.println(
                "Please enter your height (in cm), weight (in kg), " +
                "and daily calorie surplus limit (in kcal):"
        );
        String input = ui.scanNextLine();

        UserProfile profile = new CommandParser().parseProfile(input);
        userProfile.setHeight(profile.getHeight());
        userProfile.setWeight(profile.getWeight());
        userProfile.setDailyCalorieSurplusLimit(profile.getDailyCalorieSurplusLimit());
        ui.printProfileDetails(userProfile);
    }

    private void end() {
    }
}
