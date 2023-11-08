//@@ kherlenbayasgalan

package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class FindEventCommand extends EventCommand {

    /**
     * The execute method is to search and find an event from the EventList. The method has two
     * parameters (Scanner , EventList). Scanner takes the name of the event that the user
     * wants to find. EventList is accessed to search the event from the list. If the given
     * input or part of it is not in the event list, "no such event" message will be displayed.
     * @param scanner is used to get user's input on what event to search for.
     * @param eventList is used to access EventList and find the specified event.
     */

    public void execute(Scanner scanner, EventList eventList) {
        System.out.print("What event are you looking for?: ");
        String eventName = scanner.nextLine();

        int count = eventList.findEvent(eventName);

        if (count > 0) {
            System.out.println("    These events have been found");
        } else {
            System.out.println("    No such event found");
        }
    }
}
