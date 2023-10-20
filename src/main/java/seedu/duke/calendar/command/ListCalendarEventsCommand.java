//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class ListCalendarEventsCommand extends EventCommand{
    public void execute(Scanner scanner, EventList eventList) {
        System.out.println("Here is a list of all your events: ");
        printLine();

        for (Event event : eventList.getEvent()) {
            System.out.println(event);
            printLine();
        }
        System.out.print("Enter your command: ");
    }

    public void printLine() {
        System.out.println("-------------------");
    }
}
