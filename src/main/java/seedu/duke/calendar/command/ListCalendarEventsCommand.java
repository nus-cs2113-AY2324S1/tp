//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class ListCalendarEventsCommand extends EventCommand{
    public void execute(Scanner scanner, EventList eventList) {
        if (eventList.getSize() > 0) {
            System.out.println("Here is a list of all your events: ");
            printLine();

            int count = 0;
            for (Event event : eventList.getEvent()) {
                System.out.println((++count) + ". " + event.getName() + " Event from: " +event.getFrom() +
                        " to: " + event.getTo());
                printLine();
            }
        } else {
            System.out.println("    There is no event in your Calendar");
        }
    }

    public void printLine() {
        System.out.println("-".repeat(80));
    }
}
