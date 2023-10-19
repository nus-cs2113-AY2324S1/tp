package seedu.duke.data;


import seedu.duke.data.exception.IllegalValueException;
import seedu.duke.data.exception.IncorrectFormatException;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;

public class GoalList {
    private final String GOAL_KEYWORD = "set";
    private final String DATE_KEYWORD = "on";

    private ArrayList<Goal> goals;
    private int goalCount;
    private TextUi ui;

    public GoalList(ArrayList<Goal> goals) {
        this.goals = goals;
        this.goalCount = goals.size();
    }

    public Goal getGoal(int index) {
        return goals.get(index);
    }

    public int getGoalCount() {
        return goalCount;
    }

    /**
     * @param index points to the target gaol at the goal list
     */
    public void deleteGoal(int index) {
        Goal targetGoal = goals.remove(index);
        goalCount--;
        ui.printDeleteGoal(targetGoal);
        ui.printNumberofGoal(goalCount);
    }

    /**
     * The userCmd should be like: set 1234 on Date
     * @param userCmd represents the raw userInput
     * @throws IncorrectFormatException checks if the user Input is valid by:
     * 1. check if the length of the command equals 4
     * 2. detect keywords "set", "on", etc.
     * 3. check if user inputs a calories number at valid range
     */
    private void checkGoalInput(String userCmd) throws IncorrectFormatException, NumberFormatException {

        String[] cmdSplit = userCmd.split(" ");
        if (cmdSplit.length != 4) {
            throw new IncorrectFormatException("Goal message contain extra data. Please "
                    + "provides with only relevant information.");
        }

        if (!cmdSplit[0].equals(GOAL_KEYWORD) ) {
            throw new IncorrectFormatException("Sorry. I cannot detect the '" + GOAL_KEYWORD + "' keyword." );
        }

        if (!cmdSplit[2].equals(DATE_KEYWORD)) {
            throw new IncorrectFormatException("Sorry. I cannot detect the '" + DATE_KEYWORD + "' keyword." );
        }


        int calories = Integer.parseInt(cmdSplit[1]); //throws number exception if not a number string
        if (calories <= 0 ){
            throw new IllegalValueException("Please input a positive value");
        }

    }

    /**
     * This method will first check if the raw user input is in the correct format.
     * If not, terminate the method and throws error message.
     * If yes, continue to add a new goal object into the goals list.
     * @param userCmd represents raw user input
     * @throws IncorrectFormatException if user input is in wrong format
     * @throws NumberFormatException if the user does not input a valid number
     */
    public void addGoal(String userCmd) throws IncorrectFormatException, NumberFormatException{
        userCmd = userCmd.toLowerCase().trim();
        checkGoalInput(userCmd); //if invalid, exceptions is thrown

        String[] cmdSplit = userCmd.split(" ");
        int calories = Integer.parseInt(cmdSplit[1]);
        String date = cmdSplit[3];

        goals.add(new Goal(calories, date));
        goalCount++;
    }

}
