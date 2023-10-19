//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar;

import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.UnknownCommand;
import seedu.duke.calendar.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class CalendarManager {
    Calendar calendar;
    CalendarUi calendarUi;
    EventList eventList;
    CalendarCommandParser calendarCommandParser;
    Scanner scanner;

    public CalendarManager(ArrayList<Event> events) {
        eventList = new EventList(events);
        calendar = new Calendar();
        calendarUi = new CalendarUi(eventList);
        calendarCommandParser = new CalendarCommandParser();
        scanner = new Scanner(System.in);
    }

    public boolean validCommand(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);

        return !(command instanceof UnknownCommand);
    }

    public void startCalendar(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);
        assert !(command instanceof seedu.duke.calendar.command.UnknownCommand) :
                "Command cannot be " + "unknown";
        calendarUi.executeCommand(command);
        //calendarCommandParser.parseInput(command);
    }

}
