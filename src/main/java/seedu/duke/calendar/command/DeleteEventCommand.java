//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class DeleteEventCommand extends EventCommand{
    public void execute(Scanner scanner, EventList eventList) {
        System.out.print("Enter the event name: ");
        String eventName = scanner.nextLine();

        eventList.deleteEvent(eventName);
        System.out.println(eventName + " has been deleted from your Calendar!");
    }
}
