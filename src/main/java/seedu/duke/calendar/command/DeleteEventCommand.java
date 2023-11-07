//@@author kherlenbayasgalan & Cheezeblokz

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;
import seedu.duke.calendar.Event;

import java.util.Scanner;

public class DeleteEventCommand extends EventCommand{

    /**
     * The execute method is used to delete an specified event from the EventList.
     * It takes two parameters (Scanner , EventList). The method takes in the event name,
     * then calls the deleteEvent function to search and delete the event.
     * If the event is not in the EventList, then "event doesn't exist" message will be displayed.
     * @param scanner is used to get user's desired event to be deleted.
     * @param eventList is used to delete the specified event from the EventList.
     */

    public void execute(Scanner scanner, EventList eventList) {
        int size;

        System.out.print("Enter the event name: ");
        String eventName = scanner.nextLine();

        size = eventList.deleteEvent(eventName);
        if (size > eventList.getSize()) {
            System.out.println("    " + eventName + " has been deleted from your Calendar!");
        } else if (size != 0 && size == eventList.getSize()) {
            System.out.println("    " + eventName + " doesn't exist in your Calendar!");
        }
    }
}
