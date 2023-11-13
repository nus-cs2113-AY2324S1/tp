//@@author Brian030601

package seedu.duke.calendar.command;

import java.time.LocalDateTime;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class AddEventCommand extends DualEventCommand {

    //@@author Cheezeblokz
    public AddEventCommand(String input) {
        this.input = input;
        beginnerCommandLength = 2;
        expertCommandLength = 5;
        syntaxString = "add event EVENT_NAME EVENT_START_DATE (in format yyyy-mm-ddThh:mm:ss) " +
                "EVENT_END_DATE (in format yyyy-mm-ddThh:mm:ss)";
    }

    //@@author Brian030601
    /**
     * The executeBeginnerMode method is used to add an event to the calendar.
     * It has two parameters (Scanner, EventList). The EventList is used to add an
     * event to the list. The scanner is used to get the user's event name input.
     * The method first takes the event name, then through parseDateTimeInput,
     * it gets an acceptable date/time from the user. If the user inserts acceptable inputs,
     * the event will be added. If the user doesn't, either one of DateTimeParseException
     * or Invalid input exception.
     * @param scanner is used to get user's event name.
     * @param eventList is used to add an event to the list.
     */

    @Override
    public void executeBeginnerMode(Scanner scanner, EventList eventList) {
        System.out.print("What's the event?: ");
        String eventName = scanner.nextLine();

        // checks if the acceptable format is given by the user to prevent program crash
        LocalDateTime startTime = parseDateTimeInput(scanner,
                "When does it start? (yyyy-MM-ddTHH:mm:ss) (e.g., 2023-12-20T12:30:30): ");
        LocalDateTime endTime = parseDateTimeInput(scanner,
                "When does it end? (yyyy-MM-ddTHH:mm:ss) (e.g., 2023-12-20T12:30:30): ");

        if (endTime.isAfter(startTime)) {
            Event event = new Event(eventName, startTime, endTime);
            eventList.addEvent(event);
            System.out.println("    " + event + " has been added to your Calendar");
        } else {
            System.out.println("    End time is before or equal to the start time. Please enter the correct end time.");
        }
    }

    //@@author Cheezeblokz
    @Override
    protected void executeExpertMode(Scanner scanner, EventList eventList) {
        String[] commandParts = input.split(" ");
        String eventName = commandParts[2];
        try {
            LocalDateTime startTime = LocalDateTime.parse(commandParts[3]);
            LocalDateTime endTime = LocalDateTime.parse(commandParts[4]);
            if (endTime.isAfter(startTime)) {
                Event event = new Event(eventName, startTime, endTime);
                eventList.addEvent(event);
                System.out.println("    " + event + " has been added to your Calendar");
            } else {
                System.out.println("    End time is before or equal to the start time. " +
                        "Please enter the correct end time.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("    Invalid date and time format. Please try again.");
        }
    }


    //@@author Brian030601
    /**
     * The parseDateTimeInput takes two parameters (Scanner , String) and through those parameters
     * the method gets user's input. Using exception handling of LocalDateTime, the user checks if the
     * input is in correct date/time format. If it is not in the specified "yyyy-MM-ddTHH:mm:ss" format
     * the method will throw DateTimeParseException. If it is in right format, the method will return
     * the input when it is called.
     * @param scanner is used to get user's date/time input.
     * @param prompt is given to instruct the user on an acceptable date/time format.
     * @return returns an acceptable time/date input.
     */

    private LocalDateTime parseDateTimeInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String userInput = scanner.nextLine();
            try {
                // checks if the acceptable format is given by the user to prevent program crash
                return LocalDateTime.parse(userInput);
            } catch (DateTimeParseException e) {
                System.out.println("    Invalid date and time format. Please try again.");
            }
        }
    }
}
