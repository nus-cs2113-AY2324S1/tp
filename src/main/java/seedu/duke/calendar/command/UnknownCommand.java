//@@author Brian030601

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class UnknownCommand extends EventCommand{

    //@@author Brian030601
    /**
     * The execute method prints an unknown command message when an unknown command
     * is entered by the user. The two parameters (Scanner , EventList) are not
     * user by the method. But its there due to the abstract class.
     * @param scanner is not used. Given due to the abstract class
     * @param eventList is not used. Given due to the abstract class
     */

    public void execute(Scanner scanner, EventList eventList) {
        System.out.println("Unknown command! Please enter a valid command.");
    }
}
