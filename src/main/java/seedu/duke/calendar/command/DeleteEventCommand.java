//@@author kherlenbayasgalan & Cheezeblokz

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;
import seedu.duke.calendar.Event;

import java.util.Scanner;

public class DeleteEventCommand extends EventCommand{
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
