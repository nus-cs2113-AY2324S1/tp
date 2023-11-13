//@@author Brian030601

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;
import java.util.Scanner;

public abstract class EventCommand {

    //@@author Brian030601
    /**
     * The execute method is an abstract class that must be implemented by
     * its children classes. That way, its children will be able to execute
     * necessary functions.
     * @param scanner is used to get user's input.
     * @param eventList is used to access EventList.
     */

    public abstract void execute(Scanner scanner, EventList eventList);
}
