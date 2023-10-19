//@@author kherlenbayasgalan & jingxizhu

package seedu.duke.calendar.command;

import seedu.duke.calendar.EventList;

import java.util.Scanner;

public abstract class EventCommand {
    public abstract void execute(Scanner scanner, EventList eventList);
}
