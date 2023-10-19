//@@author kherlenbayasgalan

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class UnknownCommand extends EventCommand{
    public void execute(Scanner scanner, EventList eventList) {
        System.out.println("Unknown command! Please enter a valid command.");
    }
}
