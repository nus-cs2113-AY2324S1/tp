//@@author kherlenbayasgalan & jingxizhu

package seedu.duke.calendar.command;



public abstract class EventCommand {
    public abstract void execute(Scanner scanner, EventList eventList);
}
