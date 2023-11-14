//@@author Brian030601

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class DeleteAllEventsCommand extends EventCommand {

    //@@author Brian030601
    /**
     * The execute method is used to delete all events from the EventList. The method takes two
     * parameters (Scanner , EventList). However, scanner is not used here. It's given because
     * of the abstract class. The EventList is used to clear the list.
     * @param scanner is not used. Given due to the abstract class
     * @param eventList is used to delete all events in the EventList.
     */

    public void execute(Scanner scanner, EventList eventList) {
        eventList.deleteAllEvents();

        System.out.println("    All your events have been successfully " +
                "deleted from the Calendar.");
    }
}
