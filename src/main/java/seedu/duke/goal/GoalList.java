package seedu.duke.goal;

import seedu.duke.Duke;
import seedu.duke.data.Date;
import seedu.duke.data.exception.IllegalValueException;
import seedu.duke.data.exception.IncorrectFormatException;
import seedu.duke.storagefile.GoalStorage;
import seedu.duke.ui.TextUi;

import java.io.IOException;
import java.util.ArrayList;

public class GoalList extends ArrayList<GoalList.Goal> {
    private static final String DATEKEYWORD = "on";

    private ArrayList<Goal> goals;
    private int goalCount;

    public GoalList() {
        goals = new ArrayList<>();
        this.goalCount = 0;
    }

    public Goal getGoal(int index) {
        return this.goals.get(index);
    }

    public int getGoalCount() {
        return this.goalCount;
    }

    /**
     * This method removes a goal object from the global field goals list by indexing
     * It also decrements goalCount by 1
     * @param cmd Raw user Command
     * @throws IOException if failed to access output file
     * @throws NumberFormatException if index is invalid number
     * @throws IncorrectFormatException is user command is in incorrect format
     * @return message of succeeding to delete goal and tell user the updated total number of goals
     */
    public static String deleteGoal(String cmd) throws IncorrectFormatException,
            NumberFormatException, IOException {
        verifyDeleteGoalInput(cmd);
        String[] cmdSplit = cmd.toLowerCase().trim().split(" ");
        int index = Integer.parseInt(cmdSplit[1]);
        Goal targetGoal = Duke.goalList.goals.remove(index - 1);
        Duke.goalList.goalCount--;
        Duke.goalStorage.overwriteGoalToFile(Duke.goalList);

        return TextUi.deleteGoalMsg(targetGoal) + TextUi.noOfGoalMsg(Duke.goalList.goalCount);
    }

    public static String achieveGoal(String cmd) throws IncorrectFormatException,
            NumberFormatException, IOException {
        verifyAchieveGoalInput(cmd);
        String[] cmdSplit = cmd.split(" ");
        int index = Integer.parseInt(cmdSplit[1]);
        Goal achievedGoal = Duke.goalList.goals.remove(index - 1);
        Duke.goalList.goalCount--;
        Duke.goalStorage.overwriteGoalToFile(Duke.goalList); //update goal file
        Duke.achievedGoals.goals.add(achievedGoal);
        Duke.achievedGoals.goalCount++;
        Duke.achmStorage.overwriteGoalToFile(Duke.achievedGoals); // update achievement file
        return "Congratulation! You have achieved one goal!\n"
                + "[Finished]" + achievedGoal + " (:";
    }

    /**
     * Begins to format user input by change to small letter, remove leading and
     * checks if the user Input is valid by:
     * 1. check if the length of the command equals 4
     * 2. detect keywords "on"
     * 3. check if user inputs a calories number at valid range
     * The userCmd should be like: set 1234 on Date
     * @param userCmd represents the raw userInput
     * @throws IncorrectFormatException
     */
    private static void verifyGoalInput(String userCmd) throws IncorrectFormatException, NumberFormatException {

        String[] cmdSplit = userCmd.split(" ");
        if (cmdSplit.length != 4) {
            throw new IncorrectFormatException("Oops! The goal instruction is in wrong format.");
        }

        if (!cmdSplit[2].equals(DATEKEYWORD)) {
            throw new IncorrectFormatException("Sorry. I cannot detect the [" + DATEKEYWORD + "] keyword." );
        }

        int calories = Integer.parseInt(cmdSplit[1]); //throws number exception if not a number string
        if (calories <= 0 ){
            throw new IllegalValueException("Please input a positive value.");
        }

    }

    /**
     * Possible exceptions:
     * 1. Missing target index or index is invalid, includes wrong range or even not a number
     * 2. Command length not equals to 2
     * @param cmd Raw User Command
     * @throws IncorrectFormatException if the input command is in incorrect format,
     */
    private static void verifyDeleteGoalInput(String cmd) throws IncorrectFormatException, NumberFormatException {
        String[] cmdSplit = cmd.split(" ");
        if (cmdSplit.length == 1) {
            throw new IncorrectFormatException("Oops! Please provide the target index.");
        }

        if (cmdSplit.length != 2) {
            throw new IncorrectFormatException("Oops! Wrong format of delete goal instruction.");
        }

        int index = Integer.parseInt(cmdSplit[1]); //throws number format exception if not a number string
        if (index <= 0 || index > Duke.goalList.goalCount){
            throw new IllegalValueException("Please input a valid index by referring to your goals list.");
        }
    }

    /**
     * This method is similar to verifyDeleteGoalInput method as the input of each command is similar
     * @param cmd Raw User Command
     * @throws IncorrectFormatException Either missing the target index or command contains more than 2 words
     * @throws NumberFormatException if an invalid number string is input, e.g. achieve one
     */
    private static void verifyAchieveGoalInput(String cmd) throws IncorrectFormatException, NumberFormatException {
        String[] cmdSplit = cmd.split(" ");
        if (cmdSplit.length == 1) {
            throw new IncorrectFormatException("Oops! Please tell me the target index.");
        }

        if (cmdSplit.length != 2) {
            throw new IncorrectFormatException("Oops! Wrong format of achieve goal instruction.");
        }

        int index = Integer.parseInt(cmdSplit[1]); //throws number format exception if not a number string
        if (index <= 0 || index > Duke.goalList.goalCount){
            throw new IllegalValueException("Please input a valid index by referring to your goals list.");
        }
    }

    /**
     * This method will first check if the raw user input is in the correct format.
     * If not, terminate the method and throws error message.
     * If yes, continue to add a new goal object into the goals list.
     * @param userCmd represents raw user input
     * @param target represents to target list to add new goal
     * @param storage represents the target storage to update goal data
     * @throws IncorrectFormatException if user input is in wrong format
     * @throws NumberFormatException if the user does not input a valid number
     * @throws IOException if failed to access and update output file
     * @return String about succeeding to create goal object
     */
    public static String addGoal(String userCmd, GoalList target, GoalStorage storage) throws IncorrectFormatException,
            NumberFormatException, IOException {
        verifyGoalInput(userCmd); //if invalid, exceptions is thrown

        String[] cmdSplit = userCmd.split(" ");
        int calories = Integer.parseInt(cmdSplit[1]);
        String date = cmdSplit[3];

        target.goals.add(new Goal(calories, date));
        target.goalCount++;
        storage.overwriteGoalToFile(target);

        return TextUi.addGoalSuccessMessage();
    }

    /**
     * Exception appears if the length of view goal command does not equal to 1
     * i.e. containing extra information
     * @param cmd Raw user command
     * @throws IncorrectFormatException if user command format is incorrect.
     */
    public static void verifyViewGoalInput(String cmd) throws IncorrectFormatException {
        String[] cmdSplit = cmd.split(" ");
        if (cmdSplit.length != 1){
            throw new IncorrectFormatException("Use single word [viewG] to view your goal list.");
        }
    }

    /**
     * similar implementation to verify view goal list command
     * @param cmd Raw user command
     * @throws IncorrectFormatException if more than one word is input
     */
    public static void verifyViewAchievementInput(String cmd) throws IncorrectFormatException {
        String[] cmdSplit = cmd.split(" ");
        if (cmdSplit.length != 1){
            throw new IncorrectFormatException("Use single word [achievement] to view your achievement.");
        }
    }

    public static class Goal {
        private int calories;
        private Date date;

        public Goal(int calories, String date) throws IncorrectFormatException {
            this.calories = calories;
            this.date = setGoalDateTime(date);
        }

        private Date setGoalDateTime(String date) throws IncorrectFormatException {
            return new Date(date, true);
        }

        public String toString() {
            return "Consume " + this.calories + " kcal on " + this.date;
        }
    }

}
