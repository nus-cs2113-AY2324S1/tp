package seedu.duke.data;


import seedu.duke.Duke;
import seedu.duke.data.exception.IllegalValueException;
import seedu.duke.data.exception.IncorrectFormatException;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;

public class GoalList extends ArrayList<Goal> {
    private static final String GOALKEYWORD = "set";
    private static final String DATEKEYWORD = "on";
    private static final String DELETEGOALKEYWORD = "deleteg";

    private ArrayList<Goal> goals;
    private int goalCount;

    public GoalList() {
        goals = new ArrayList<>();
        this.goalCount = 0;
    }

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

    /** This method removes a goal object from the global field goals list by indexing
     * It also decrements goalCount by 1
     * @param cmd Raw user Command
     * @return message of succeeding to delete goal and tell user the updated total number of goals
     */
    public static String deleteGoal(String cmd) throws IncorrectFormatException, NumberFormatException{
        verifyDeleteGoalInput(cmd);
        String[] cmdSplit = cmd.toLowerCase().trim().split(" ");
        int index = Integer.parseInt(cmdSplit[1]);
        Goal targetGoal = Duke.goals.remove(index);
        Duke.goals.goalCount--;
        return TextUi.DeleteGoalMsg(targetGoal) + TextUi.NumberofGoalMsg(Duke.goals.goalCount);
    }

    /**
     * Begins to format user input by change to small letter, remove leading and
     * checks if the user Input is valid by:
     * 1. check if the length of the command equals 4
     * 2. detect keywords "set", "on", etc.
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

        if (!cmdSplit[0].equals(GOALKEYWORD) ) {
            throw new IncorrectFormatException("Sorry. I cannot detect the [" + GOALKEYWORD + "] keyword." );
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
     * format usr input by change to small letter, remove leading and ending white space.
     * @param cmd Raw User Command
     * @throws IncorrectFormatException if the input command is in incorrect format,
     * possibles cases include: command length!=2, not start with Keyword, wrong integer, etc.
     */
    private static void verifyDeleteGoalInput(String cmd) throws IncorrectFormatException, NumberFormatException {
        String[] cmdSplit = cmd.split(" ");
        if (cmdSplit.length == 1) {
            throw new IncorrectFormatException("Oops! Please provide the target index.");
        }

        if (cmdSplit.length != 2) {
            throw new IncorrectFormatException("Oops! Wrong format of delete goal instruction.");
        }

        if (!cmdSplit[0].equals(DELETEGOALKEYWORD) ) {
            throw new IncorrectFormatException("Sorry. Please starts with [deleteG]");
        }

        int index = Integer.parseInt(cmdSplit[1]); //throws number exception if not a number string
        if (index <= 0 || index > Duke.goals.goalCount){
            throw new IllegalValueException("Please input a valid index by referring to your goals list.");
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
    public static String addGoal(String userCmd) throws IncorrectFormatException, NumberFormatException{
        verifyGoalInput(userCmd); //if invalid, exceptions is thrown

        String[] cmdSplit = userCmd.split(" ");
        int calories = Integer.parseInt(cmdSplit[1]);
        String date = cmdSplit[3];

        Duke.goals.add(new Goal(calories, date));
        Duke.goals.goalCount++;

        return TextUi.addGoalSuccessMessage();
    }

}
