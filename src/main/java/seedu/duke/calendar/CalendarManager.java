//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar;

import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.UnknownCommand;

import java.util.Scanner;

public class CalendarManager {
    Calendar calendar;
    CalendarUi calendarUi;
    CalendarCommandParser calendarCommandParser;
    Scanner scanner;

    public CalendarManager() {
        calendar = new Calendar();
        calendarUi = new CalendarUi();
        calendarCommandParser = new CalendarCommandParser();
        scanner = new Scanner(System.in);
    }

    public boolean validCommand(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);

        return !(command instanceof UnknownCommand);
    }

    public void startCalendar(String input) {
        calendarUi.begin();
        EventCommand command = calendarCommandParser.parseInput(input);
        assert !(command instanceof seedu.duke.calendar.command.UnknownCommand) :
                "Command cannot be " + "unknown";
        calendarCommandParser.parseInput(input);
    }

}
