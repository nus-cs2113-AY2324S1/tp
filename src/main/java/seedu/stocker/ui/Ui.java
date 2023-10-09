package seedu.stocker.ui;

import java.util.Scanner;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import seedu.stocker.drugs.Drug;
import seedu.stocker.commands.CommandResult;

import static seedu.stocker.common.Messages.MESSAGE_WELCOME;
import static seedu.stocker.common.Messages.MESSAGE_GOODBYE;

public class Ui {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A decorative prefix added to the beginning of lines printed by Jerry */
    private static final String LINE_PREFIX = "|| ";

    private static final String DIVIDER = "===================================================";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printversion1Help(){
        System.out.println("Here are a list of possible commands");
        System.out.println("1. add - Add a drug into the system");
        System.out.println("2. delete - Remove a drug from the system");
        System.out.println("3. list - List all current drugs in the system");
        System.out.println("4. find - Find a specific drug in the system");
        System.out.println("5. help - List all available commands");

        System.out.println();
        System.out.println();

        System.out.println("Here is the formatting for the commands");

        System.out.println("For add:");
        System.out.println("add /n <drug name> /d <expiry date> /q <quantity>");
        System.out.println();

        System.out.println("For delete:");
        System.out.println("delete /n <drug name>");
        System.out.println();


        System.out.println("For list:");
        System.out.println("list");
        System.out.println();


        System.out.println("For find:");
        System.out.println("find <keyword>");
        System.out.println();


        System.out.println("For help:");
        System.out.println("help");

    }
    
    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();

        return fullInputLine;
    }

    // public void uiBegin() {
    //     Scanner input = new Scanner(System.in);
    //     String instruction = input.next();
    //
    //     while(instruction.equals("bye") != true) {
    //         switch (instruction) {
    //         case "help":
    //             printversion1Help();
    //             instruction = input.next();
    //             break;
    //
    //         default:
    //             instruction = input.next();
    //
    //         }
    //     }
    //     System.out.println("Bye! See you soon!");
    // }
    
    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public void showWelcomeMessage() {
        showToUser(MESSAGE_WELCOME, DIVIDER, DIVIDER);
    }

    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public void showResultToUser(CommandResult result) {
        final Optional<List<Drug>> resultDrugs = result.getRelevantDrugs();
        if (resultDrugs.isPresent()) {
            showDrugListView(resultDrugs.get());
        }
        showToUser(result.feedbackToUser, DIVIDER);
    }

    /** Shows a list of strings to the user, formatted as an indexed list. */
    private void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    /**
     * Shows a list of drugs to the user, formatted as an indexed list.
     */
    private void showDrugListView(List<Drug> drugs) {
        final List<String> formattedDrugs = new ArrayList<>();
        for (Drug drug : drugs) {
            formattedDrugs.add(drug.toString());
        }
        showToUserAsIndexedList(formattedDrugs);
    }

    /** Formats a list of strings as a viewable indexed list. */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = DISPLAYED_INDEX_OFFSET;
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

}
