//@@author Brian030601

package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class ListCalendarEventsCommand extends EventCommand{

    //@@author Brian030601
    /**
     * The execute method is used to list all the events in the EventList. The method
     * has two parameters (Scanner , EventList). The scanner doesn't do anything here
     * but is given due to the abstract class. EventList is used for getting access to
     * all the events in the list. If the EventList is empty, "The Calendar is empty"
     * message will be displayed.
     * @param scanner is not used. Given due to the abstract class
     * @param eventList is used to access EventList.
     */

    public void execute(Scanner scanner, EventList eventList) {
        if (eventList.getSize() > 0) {
            System.out.println("    Here is a list of all your events: ");
            printLine();

            int count = 0;
            for (Event event : eventList.getEvents()) {
                System.out.println((++count) + ". " + event);
                printLine();
            }
        } else {
            System.out.println("    The Calendar is empty");
        }
    }

    public void printLine() {
        System.out.println("-".repeat(80));
    }
}
