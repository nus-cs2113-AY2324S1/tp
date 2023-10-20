package seedu.duke.calendar;

import seedu.duke.calendar.command.EventCommand;

import java.util.Scanner;

public class CalendarUi {
    private Scanner scanner;
    private EventList eventList;

    public CalendarUi (EventList eventList) {
        scanner = new Scanner(System.in);
        this.eventList = eventList;
    }

    public void executeCommand(EventCommand command) {
        command.execute(scanner, eventList);
    }
}
