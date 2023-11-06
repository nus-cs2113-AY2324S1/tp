//@@ kherlenbayasgalan

package seedu.duke.calendar.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class AddEventCommand extends EventCommand {

    public void execute(Scanner scanner, EventList eventList) {
        System.out.print("What's the event?: ");
        String eventName = scanner.nextLine();

        LocalDateTime startTime = parseDateTimeInput(scanner, "When does it start? (yyyy-MM-ddTHH:mm:ss) (e.g., 2023-12-20T12:30:30): ");
        LocalDateTime endTime = parseDateTimeInput(scanner, "When does it end? (yyyy-MM-ddTHH:mm:ss) (e.g., 2023-12-20T12:30:30): ");

        if (endTime.isAfter(startTime)) {
            Event event = new Event(eventName, startTime, endTime);
            eventList.addEvent(event);
            System.out.println(event + " has been added to your Calendar");
        } else {
            System.out.println("    End time is before or equal to the start time. Please enter the correct end time.");
        }
    }

    private LocalDateTime parseDateTimeInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String userInput = scanner.nextLine();
            try {
                return LocalDateTime.parse(userInput, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            } catch (DateTimeParseException e) {
                System.out.println("    Invalid date and time format. Please try again.");
            }
        }
    }
}
