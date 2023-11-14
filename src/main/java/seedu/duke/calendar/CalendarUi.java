//@@author Brian030601

package seedu.duke.calendar;

import seedu.duke.calendar.command.EventCommand;

import java.util.Scanner;

public class CalendarUi {
    private Scanner scanner;
    private EventList eventList;

    //@@author Brian030601
    // CalendarUi is the constructor method for CalendarUi class.
    public CalendarUi (EventList eventList) {
        scanner = new Scanner(System.in);
        this.eventList = eventList;
    }

    //@@author Brian030601
    /**
     * executeCommand is used for starting the calendar part of the program.
     * @param command is used for relaying the command entered by the user.
     */

    public void executeCommand(EventCommand command) {
        command.execute(scanner, eventList);
    }
}
