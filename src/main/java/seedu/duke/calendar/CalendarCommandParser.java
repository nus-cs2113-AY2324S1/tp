//@@author kherlenbayasgalan

package seedu.duke.calendar;

import seedu.duke.calendar.command.AddEventCommand;
import seedu.duke.calendar.command.DeleteEventCommand;
import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.ListCalendarEventsCommand;
import seedu.duke.calendar.command.UnknownCommand;

public class CalendarCommandParser {
    public EventCommand parseInput(String input) {
        assert input != null : "input is null";

        if (input.startsWith("add event")) {
            return new AddEventCommand();
        } else if (input.startsWith("delete event")) {
            return new DeleteEventCommand();
        } else if (input.startsWith("list event")) {
            return new ListCalendarEventsCommand();
        }

        return new UnknownCommand();
    }
}
