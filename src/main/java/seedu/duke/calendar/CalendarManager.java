package seedu.duke.calendar;

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

    public void start() {
        calendarUi.begin();
        String userCommand = scanner.nextLine();
        calendarCommandParser.parseInput(userCommand);
    }

}
