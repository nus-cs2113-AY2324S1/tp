//@@ kherlenbayasgalan

package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.util.Scanner;
import java.time.LocalDateTime;

public class AddEventCommand extends EventCommand{
    public void execute(Scanner scanner, EventList eventList) {
        System.out.println("What's the event?: ");
        String eventName = scanner.nextLine();
        System.out.println("When does it start?: ");
        LocalDateTime startTime = LocalDateTime.parse(scanner.nextLine());
        System.out.println("When does it end?: ");
        LocalDateTime endTime = LocalDateTime.parse(scanner.nextLine());

        Event event = new Event(eventName, startTime, endTime);

        eventList.addEvent(event);

        System.out.println("The event has been added to you calendar");
    }
}
