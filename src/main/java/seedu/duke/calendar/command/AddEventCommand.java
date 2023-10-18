package seedu.duke.calendar.command;

import seedu.duke.calendar.Calendar;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AddEventCommand extends EventCommand{
    public void execute(Scanner scanner, Calendar calendar) {
        System.out.print("Enter the event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter the event from date (yyyy-mm-ddThh:mm:ss) (eg. 2023-12-20T12:30:30): ");
        LocalDateTime eventFrom = LocalDateTime.parse(scanner.nextLine());
        System.out.print("Enter the event to date (yyyy-mm-ddThh:mm:ss) (eg. 2023-12-20T12:30:30): ");
        LocalDateTime eventTo = LocalDateTime.parse(scanner.nextLine());

        calendar.addEvent(eventName, eventFrom, eventTo);

        System.out.println("Success! Event has been added to your Calendar!");
    }
}
