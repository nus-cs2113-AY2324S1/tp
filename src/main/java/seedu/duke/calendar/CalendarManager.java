//@@author Brian030601

package seedu.duke.calendar;

import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.UnknownCommand;
import seedu.duke.storage.EventDirectory;
import seedu.duke.storage.EventStorage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CalendarManager {
    Calendar calendar;
    CalendarUi calendarUi;
    EventList eventList;
    CalendarCommandParser calendarCommandParser;
    Scanner scanner;

    private EventStorage storage;

    /**
     * The CalendarManager initializes the accesses to other classes.
     * It also loads events from the storage.
     * @param events is used to initialize the EventList.
     */

    public CalendarManager(Calendar calendar, ArrayList<Event> events) {

        EventDirectory eventdirectory = new EventDirectory();
        eventdirectory.listEventFiles();

        storage = new EventStorage(eventdirectory.defaultDirectory());

        try{
            eventList = storage.loadEvents();
        } catch (FileNotFoundException e){
            //System.out.println("Making new file for Events");
            eventList = new EventList(events);
        }

        calendar.setEventList(eventList);
        this.calendar = calendar;
        calendarUi = new CalendarUi(eventList);
        calendarCommandParser = new CalendarCommandParser();
        scanner = new Scanner(System.in);

    }

    // getStorage is used for getting the storage
    public EventStorage getStorage(){
        return this.storage;
    }

    //@@author Brian030601
    /**
     * validCommand is used for checking whether the command is valid, and
     * not an instance of UnknownCommand.
     * @param input is used for converting the input into command.
     * @return returns whether the command is instance of UnknownCommand or not.
     */

    public boolean validCommand(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);

        return !(command instanceof UnknownCommand);
    }

    //@@author Brian030601
    // isResponsible calls the validCommand method.
    public boolean isResponsible(String input) {
        return validCommand(input);
    }

    // processInput is used for saving the events in the EventList.
    public void processInput(String input) {
        startCalendar(input);

        storage.saveEvents(eventList.getEvents());
    }

    //@@author Brian030601
    /**
     * startCalender starts the Calendar features and uses the input as a command.
     * @param input is used for converting the input into command.
     */

    public void startCalendar(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);
        assert !(command instanceof seedu.duke.calendar.command.UnknownCommand) :
                "Command cannot be " + "unknown";
        calendarUi.executeCommand(command);
        //calendarCommandParser.parseInput(command);
    }
}
