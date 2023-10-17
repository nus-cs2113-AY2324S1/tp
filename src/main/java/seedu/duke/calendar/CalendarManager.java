package seedu.duke.calendar;

public class CalendarManager {
    Calendar calendar;
    CalendarUi calendarUi;
    CalendarCommandParser calendarCommandParser;

    public CalendarManager() {
        calendar = new Calendar();
        calendarUi = new CalendarUi();
        calendarCommandParser = new CalendarCommandParser();
    }

    public void start() {

    }

}
