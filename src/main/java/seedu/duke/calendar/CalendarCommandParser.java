//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar;

import seedu.duke.calendar.command.AddEventCommand;
import seedu.duke.calendar.command.DeleteAllEvents;
import seedu.duke.calendar.command.DeleteEventCommand;
import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.UnknownCommand;
import seedu.duke.calendar.command.FindEvent;
import seedu.duke.calendar.command.ListCalendarEventsCommand;

public class CalendarCommandParser {
    public EventCommand parseInput(String input) {
        assert input != null : "input is null";

        if (input.startsWith("add event")) {
            return new AddEventCommand();
        } else if (input.startsWith("delete event")) {
            return new DeleteEventCommand();
        } else if (input.startsWith("list events")) {
            return new ListCalendarEventsCommand();
        } else if (input.startsWith("delete all events")) {
            return new DeleteAllEvents();
        } else if (input.startsWith("find event")) {
            return new FindEvent();
        }

        return new UnknownCommand();
    }
}
