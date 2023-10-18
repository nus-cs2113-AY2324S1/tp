package fittrack;

import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.command.ExitCommand;
import fittrack.parser.CommandParser;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;

/**
 * Represents the main part of FitTrack.
 * <p>
 * Referenced
 * <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">here</a>
 * to build main structure of this class.
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
        assert false: "dummy assertion set to fail";
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
            System.out.println("Wrong format. Please enter h/<height> w/<weight> l/<dailyCalorieLimit>");
        } catch (NumberFormatException e) {
            System.out.println("Please enter numbers for height, weight, and daily calorie limit.");
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
     *
     * @throws PatternMatchFailException if regex match fails
     * @throws NumberFormatException if one of arguments is not double
     */
    private void profileSettings() throws PatternMatchFailException, NumberFormatException {
        System.out.println(
                "Please enter your height (in cm), weight (in kg), and daily calorie limit (in kcal):"
        );
        String input = ui.scanNextLine();

        assert (input == null) : "input cannot be null";

        UserProfile profile = new CommandParser().parseProfile(input);
        userProfile.setHeight(profile.getHeight());
        userProfile.setWeight(profile.getWeight());
        userProfile.setDailyCalorieLimit(profile.getDailyCalorieLimit());
        ui.printProfileDetails(userProfile);
    }

    private void end() {
    }
}
