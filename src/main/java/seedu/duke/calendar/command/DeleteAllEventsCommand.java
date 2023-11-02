//@@ kherlenbayasgalan

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class DeleteAllEventsCommand extends EventCommand {
    public void execute(Scanner scanner, EventList eventList) {
        eventList.deleteAllEvents();

        System.out.println("    All your events have been successfully " +
                "deleted from the Calendar.");
    }
}
