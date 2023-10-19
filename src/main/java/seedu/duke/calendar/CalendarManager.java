//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar;

import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.UnknownCommand;
import seedu.duke.calendar.Event;
import seedu.duke.flashcard.FlashcardStorage;

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

    public CalendarManager(ArrayList<Event> events) {

        EventDirectory eventdirectory = new EventDirectory();
        eventdirectory.listEventFiles();

        storage = new EventStorage("./data/events/event.txt");

        try{
            eventList = storage.loadEvents();
        } catch (FileNotFoundException e){
            System.out.println("Making new file for Events");
            eventList = new EventList(events);
        }

        calendar = new Calendar();
        calendarUi = new CalendarUi(eventList);
        calendarCommandParser = new CalendarCommandParser();
        scanner = new Scanner(System.in);

    }

    public EventStorage getStorage(){
        return this.storage;
    }

    public boolean validCommand(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);

        return !(command instanceof UnknownCommand);
    }

    public boolean isResponsible(String input) {
        return validCommand(input);
    }

    public void processInput(String input) {
        startCalendar(input);

        storage.saveEvents(eventList.getEvent());
    }

    public void startCalendar(String input) {
        EventCommand command = calendarCommandParser.parseInput(input);
        assert !(command instanceof seedu.duke.calendar.command.UnknownCommand) :
                "Command cannot be " + "unknown";
        calendarUi.executeCommand(command);
        //calendarCommandParser.parseInput(command);
    }

}
