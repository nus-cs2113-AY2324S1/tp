package seedu.duke;

import java.util.ArrayList;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.meal.MealCommand;
import seedu.duke.goal.GoalList;
import seedu.duke.meal.Meal;
import seedu.duke.parser.Parser;
import seedu.duke.exerciselog.Log;
import seedu.duke.storagefile.AchmStorage;
import seedu.duke.storagefile.DataManager;
import seedu.duke.storagefile.GoalStorage;
import seedu.duke.ui.TextUi;
import seedu.duke.storagefile.ExerciseLogStorage;

/**
 * Entry point of the Address Book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    /**
     * Version info of the program.
     */
    public static final String VERSION = "Version-2.1";
    public static Log exerciseLog = new Log();
    public static GoalList goalList = new GoalList();
    public static GoalList achievedGoals = new GoalList();
    public static ExerciseLogStorage exerciseLogStorage;
    public static TextUi ui;
    public static GoalStorage goalStorage;
    public static AchmStorage achmStorage;
    private final String dirPath = "data";
    private final String exerciseLogFilePath = "./data/ExerciseLog.txt";
    private final String goalFilePath = "./data/GoalRecord.txt";
    private final String achmFilePath = "./data/Achievement.txt";
    private final String mealSavePath = "Meal.json";
    private static ArrayList<Meal> meals = new ArrayList<Meal>();

    public static void main(String... launchArgs) {
        new Duke().run(launchArgs);
    }

    /**
     * @param launchArgs
     *                   Runs the program until termination.
     */
    public void run(String[] launchArgs) {
        start(launchArgs);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and
     * prints the welcome message.
     *
     * @param launchArgs arguments supplied by the user at program launch
     */
    private void start(String[] launchArgs) {
        try {
            ui = new TextUi();
            exerciseLogStorage = ExerciseLogStorage.initializeStorage(dirPath, exerciseLogFilePath);
            exerciseLogStorage.checkForLogTextFile(exerciseLog);
            goalStorage = GoalStorage.initializeGoalStorage(dirPath, goalFilePath);
            goalStorage.restoreGoalRecord();
            achmStorage = AchmStorage.initializeGoalStorage(dirPath, achmFilePath);
            achmStorage.restoreGoalRecord();
            ui.showWelcomeMessage(VERSION, "storage.getPath()");
            DataManager.setRelativePath(mealSavePath);
            String dataJson = DataManager.readData();
            ArrayList<Meal> data = DataManager.convertFromJsonToMealList(dataJson);
            if (data != null) {
                meals = data;
            }
            MealCommand.setMeals(meals);
        } catch (Exception e) { // TODO: change to specific storage exceptions later
            ui.showInitFailedMessage();
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
        ui.showGoodbyeMessage();
        try {
            DataManager.saveData(DataManager.convertToJson(meals));
        } catch (Exception exception) {
            ui.showToUser(exception.toString());
        }
        System.exit(0);
    }

    /**
     * Reads the user command and executes it, until the user issues the exit
     * command.
     * The command will be formatted before execution by following:
     * 1. change to lower case
     * 2. remove leading and ending whitespace
     * 3. remove consecutive white space between words
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            try {
                String userCommandText = ui.getUserCommand();
                command = new Parser().parseCommand(userCommandText.toLowerCase().trim().replaceAll("\\s+", " "));
                CommandResult result = executeCommand(command);
                ui.showResultToUser(result);
                if (ExitCommand.isExit(command)) {
                    break;
                }
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            }
        } while (true);
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
