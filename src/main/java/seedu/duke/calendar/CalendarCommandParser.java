//@@ kherlenbayasgalan & jingxizhu

package seedu.duke.calendar;

import seedu.duke.calendar.command.AddEventCommand;
import seedu.duke.calendar.command.DeleteAllEventsCommand;
import seedu.duke.calendar.command.DeleteEventCommand;
import seedu.duke.calendar.command.EventCommand;
import seedu.duke.calendar.command.UnknownCommand;
import seedu.duke.calendar.command.FindEventCommand;
import seedu.duke.calendar.command.ListCalendarEventsCommand;

public class CalendarCommandParser {
    public EventCommand parseInput(String input) {
        assert input != null : "input is null";

        if (input.startsWith("add event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("delete event")) {
            return new DeleteEventCommand();
        } else if (input.startsWith("list events")) {
            return new ListCalendarEventsCommand();
        } else if (input.startsWith("delete all events")) {
            return new DeleteAllEventsCommand();
        } else if (input.startsWith("find event")) {
            return new FindEventCommand();
        }

        return new UnknownCommand();
    }
}
