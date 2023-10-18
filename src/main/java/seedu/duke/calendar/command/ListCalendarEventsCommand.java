package seedu.duke.calendar.command;

import seedu.duke.calendar.Calendar;

import java.util.Scanner;

public class ListCalendarEventsCommand extends EventCommand{
    public void execute(Scanner scanner, Calendar calendar) {
        calendar.listEvents();
    }
}
