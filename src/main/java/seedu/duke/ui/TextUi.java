package seedu.duke.ui;

import static seedu.duke.common.Messages.MESSAGE_GOODBYE;
import static seedu.duke.common.Messages.MESSAGE_INIT_FAILED;
import static seedu.duke.common.Messages.MESSAGE_USING_STORAGE_FILE;
import static seedu.duke.common.Messages.MESSAGE_WELCOME;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import seedu.duke.Duke;
import seedu.duke.commands.CommandResult;
import seedu.duke.data.Goal;
import seedu.duke.data.GoalList;
import seedu.duke.data.Printable;

/**
 * Text UI of the application.
 */
public class TextUi {

    /**
     * Offset required to convert between 1-indexing and 0-indexing.
     */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /**
     * A decorative prefix added to the beginning of lines printed by FitNUS
     */
    private static final String LINE_PREFIX = "|| ";

    /**
     * A platform independent line separator.
     */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /**
     * Format of indexed list item
     */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    /**
     * Format of a comment input line. Comment lines are silently consumed when
     * reading user input.
     */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or
     * is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     *
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }

    /**
     * Generates and prints the welcome message upon the start of the application.
     *
     * @param version         current version of the application.
     * @param storageFilePath path to the storage file being used.
     */
    public void showWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        showToUser(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                DIVIDER);
    }

    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    public void showInitFailedMessage() {
        showToUser(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }

    /**
     * Shows message(s) to the user
     * 
     * @param message the message to show to the user
     */
    public void showToUser(String... message) {
        for (String m : message) {
            if (m == null) {
                continue;
            }
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    /**
     * Shows the result of a command execution to the user. Includes additional
     * formatting to demarcate different
     * command execution segments.
     * 
     * @param result the command result to be shown to the user
     */
    public void showResultToUser(CommandResult result) {
        final Optional<List<? extends Printable>> resultItems = result.getRelevantItems();
        if (resultItems.isPresent()) {
            showItemsListView(resultItems.get());
        }
        showToUser(result.feedbackToUser, DIVIDER);
    }

    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void showItemsListView(List<? extends Printable> items) {
        final List<String> formattedItems = new ArrayList<>();
        for (Printable person : items) {
            formattedItems.add(person.getAsText());
        }
        showToUserAsIndexedList(formattedItems);
    }

    /**
     * Shows a list of strings to the user, formatted as an indexed list.
     */
    private void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    /**
     * Formats a list of strings as a viewable indexed list.
     */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

    public static String noOfGoalMsg(int goalCount) {
        return "You now have " + goalCount + " goals to accomplish.";
    }

    public static String deleteGoalMsg(Goal deletedGoal) {

        return "Good. I have removed this goal: " + deletedGoal + "\n"
                + "Remember not to give up unaccomplished target!" + "\n";

    }

    /**
     * This method is used to implement Goal commend execution, when adding a new goal
     * @return string contains information of generating a new goal successfully
     */
    public static String addGoalSuccessMessage() {
        int currentNoOfGoal = Duke.goalList.getGoalCount();
        Goal newlyAddedGoal = Duke.goalList.getGoal(currentNoOfGoal - 1);
        return "Nice! I have added the following goal to your goals list: \n\t" + newlyAddedGoal;
    }

    /**
     * This is used to show the content inside the goal list.
     * It first checks if the list contains at least one goal,
     * then print the goal by using string builder.
     * @return String containing all the inserted goal in the global field goal list
     */
    public static String showGoalList() {
        int numberOfGoal = Duke.goalList.getGoalCount();
        if (numberOfGoal == 0) {
            return "Oh not! You don't have any goal to achieve currently.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here you go! Remember to stick to your exercise and meal plans.\n");
        for (int i = 0; i < numberOfGoal; i++){
            sb.append(i + 1).append(". ").append(Duke.goalList.getGoal(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Similar to show Goal List. This method is used to list out all achieved goal in record.
     * @return String containing all achieved goal
     */
    public static String showAchievement() {
        int numberOfGoal = Duke.achievedGoals.getGoalCount();
        if (numberOfGoal == 0) {
            return "Add oil! There is no achievement found.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Congratulation! See your achievements below: \n");
        for (int i = 0; i < numberOfGoal; i++){
            sb.append(i + 1).append(". [A]").append(Duke.achievedGoals.getGoal(i)).append("\n");
        }

        return sb.toString();
    }

    /**
     * This method return content of goal list in any goalList object
     * It is typically used to overwrite save file whenever change in goal records
     * @param goals a GoalList object
     * @return String containing goal information of the goal object
     */
    public static String contentOfGoalList(GoalList goals) {
        if (goals.getGoalCount() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < goals.getGoalCount(); i++){
            sb.append(goals.getGoal(i)).append("\n");
        }
        return sb.toString();
    }

    public static String buildingFileMsg() {
        return "Building new save file...\n" + "Building new file succeed!";
    }

}
