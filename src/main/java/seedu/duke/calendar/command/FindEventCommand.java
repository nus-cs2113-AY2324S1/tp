//@@ kherlenbayasgalan

package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class FindEventCommand extends EventCommand {
    public void execute(Scanner scanner, EventList eventList) {
        System.out.print("What event are you looking for?: ");
        String eventName = scanner.nextLine();

        if (eventList.findEvent(eventName) > 0) {
            System.out.println("    Here are the matching events in your list:");
            eventList.findEvent(eventName);
            System.out.println("    These events have been found");
        } else {
            System.out.println("    No such event found");
        }
    }
}
