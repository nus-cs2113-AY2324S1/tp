//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar.command;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DeleteEventCommand extends EventCommand{
    public void execute(Scanner scanner, Calendar calendar) {
        System.out.print("Enter the event name: ");
        String eventName = scanner.nextLine();

        calendar.delEvent(eventName);

        System.out.println("Event has been deleted from your Calendar!");
    }
}
