package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;
import seedu.duke.calendar.Goal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddGoalEventCommand extends EventCommand{

    //@@author kherlenbayasgalan-reused

    /**
     * The execute method is used to add an event goal to the calendar. It has two parameters (Scanner, EventList).
     * The EventList is used to add an event to the list. The scanner is used to get the user's event name input.
     * The method first takes the event name, then through parseDateTimeInput, it gets an acceptable date/time
     * from the user. If the user inserts acceptable inputs, the event goal will be added. If the user doesn't,
     * either one of DateTimeParseException or Invalid input exception.
     * @param scanner is used to get user's event name.
     * @param eventList is used to add an event to the list.
     */

    public void execute(Scanner scanner, EventList eventList) {
        System.out.print("What's the goal event name?: ");
        String eventName = scanner.nextLine();

        // checks if the acceptable format is given by the user to prevent program crash
        LocalDateTime endTime = parseDateTimeInput(scanner, "When does it end? (yyyy-MM-ddTHH:mm:ss) (e.g., 2023-12-20T12:30:30): ");
        int goal = parseIntegerInput(scanner, "How many flashcard to review by then?: ");

        Event event = new Goal(eventName, endTime, goal, 0);
        eventList.addEvent(event);
        System.out.println(event + " has been added to your Calendar");
    }

    //@@author Cheezeblokz

    private int parseIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String userInput = scanner.nextLine();
            try {
                // checks if the acceptable format is given by the user to prevent program crash
                return Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("    Invalid integer input. Please try again.");
            }
        }
    }

    //@@author kherlenbayasgalan-reused

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
