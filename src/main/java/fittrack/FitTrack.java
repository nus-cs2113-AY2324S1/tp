package fittrack;

import fittrack.command.Command;
import fittrack.command.CommandResult;
import fittrack.command.ExitCommand;
import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;
import fittrack.parser.PatternMatchFailException;
import fittrack.storage.Storage;
import fittrack.storage.Storage.StorageOperationException;
import fittrack.storage.Storage.InvalidStorageFilePathException;

import java.io.IOException;


/**
 * Represents the main part of FitTrack.
 * <p>
 * Referenced
 * <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">here</a>
 * to build main structure of this class.
 */
public class FitTrack {
    public static final String VERSION = "FitTrack - Version 2.1";

    private final Ui ui;
    private Storage storage;
    private UserProfile userProfile;
    private MealList mealList;
    private WorkoutList workoutList;
    private StepList stepList;

    private boolean isValidInput = false;

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
        new FitTrack().run(args);
    }

    private void run(String[] args) {
        start(args);
        loopCommandExecution();
        end();
    }

    private void start(String[] args) {
        ui.printVersion();
        ui.printWelcome();
        loadStorage(args);
        startProfile();
    }

    private void loopCommandExecution() {
        Command command;
        do {
            String userCommandLine = ui.scanCommandLine();
            command = CommandParser.parseCommand(userCommandLine);
            CommandResult commandResult = executeCommand(command);
            ui.printCommandResult(commandResult);
            ui.printLine();
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult executeCommand(Command command) {
        try {
            command.setData(userProfile, mealList, workoutList, stepList, storage);
            CommandResult result = command.execute();
            storage.save(userProfile, mealList, workoutList, stepList);
            return result;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    private void startProfile() {
        while (!isValidInput) {
            try {
                String input = ui.profileMessageAndScanner();
                userProfile.profileSettings(input);
                ui.printProfileDetails(userProfile);
                storage.saveProfile(userProfile);
                isValidInput = true;
            } catch (PatternMatchFailException e) {
                System.out.println("Wrong format. Please enter h/<height> w/<weight> g/<gender> l/<dailyCalorieLimit>");
            } catch (IOException e) {
                System.out.println("Error occurred while saving profile.");
                isValidInput = true;
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void loadStorage(String[] args) {
        try {
            this.storage = initializeStorage(args);
            if (!storage.isProfileFileEmpty()) {
                this.userProfile = storage.profileLoad();
                ui.printPrompt();
                isValidInput = true;
            }
            this.mealList = storage.mealLoad();
            this.workoutList = storage.workoutLoad();
            this.stepList = storage.stepLoad();
        } catch (StorageOperationException | InvalidStorageFilePathException e) {
            System.out.println("There was a problem with the loading of storage contents.");
            ui.printLine();
        }
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     *
     * @param args arguments supplied by the user at program launch
     * @throws InvalidStorageFilePathException if the target file path is incorrect.
     */
    private Storage initializeStorage(String[] args) throws InvalidStorageFilePathException {
        boolean isStorageFileSpecifiedByUser = args.length > 0;
        return isStorageFileSpecifiedByUser ? new Storage(args[0], args[1], args[2], args[3]) : new Storage();
    }

    private void end() {
    }
}
