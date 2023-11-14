//@@author Cheezeblokz
package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;
import seedu.duke.calendar.Goal;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AddGoalEventCommand extends DualEventCommand{

    public AddGoalEventCommand(String input) {
        this.input = input;
        beginnerCommandLength = 3;
        expertCommandLength = 6;
        syntaxString = "add goal event GOAL_NAME GOAL_END_DATE (in format yyyy-mm-ddThh:mm:ss) FLASHCARD_GOAL_NUMBER";
    }

    @Override
    public void executeBeginnerMode(Scanner scanner, EventList eventList) {
        System.out.print("What's the goal event name?: ");
        String eventName = scanner.nextLine();

        LocalDateTime endTime = parseDateTimeInput(scanner,
                "What is the deadline? (yyyy-MM-ddTHH:mm:ss) (e.g., 2023-12-20T12:30:30): ");
        int goal = parseIntegerInput(scanner,
                "How many flashcard to review by then?: ");

        if (endTime.isAfter(LocalDateTime.now())) {
            Event event = new Goal(eventName, endTime, goal, 0);
            eventList.addEvent(event);
            System.out.println(event + " has been added to your Calendar");
        } else {
            System.out.println("    End time is before the current time. Please enter the correct end time.");
        }
    }

    //@@author Cheezeblokz
    @Override
    protected void executeExpertMode(Scanner scanner, EventList eventList) {
        String[] commandParts = input.split(" ");
        String eventName = commandParts[3];
        try {
            LocalDateTime endTime = LocalDateTime.parse(commandParts[4]);
            int goal = Integer.parseInt(commandParts[5]);
            if (endTime.isAfter(LocalDateTime.now())) {
                Event event = new Goal(eventName, endTime, goal, 0);
                eventList.addEvent(event);
                System.out.println(event + " has been added to your Calendar");
            } else {
                System.out.println("    End time is before the current time. Please enter the correct end time.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("    Invalid date and time format. Please try again.");
        } catch (NumberFormatException e) {
            System.out.println("    Invalid integer input. Please try again.");
        }
    }

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

    //@@author Brian030601-reused

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
