//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class ListCalendarEventsCommand extends EventCommand{
    public void execute(Scanner scanner, EventList eventList) {
        System.out.println("Here is a list of all your events: ");

        System.out.println("-".repeat(80));
        for (Event event : eventList.getEvent()) {
            System.out.print(event);
            System.out.println("-".repeat(80));
        }
    }
}
