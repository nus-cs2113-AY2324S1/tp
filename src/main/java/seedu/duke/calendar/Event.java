//@@author @@kherlenbayasgalan & @@ Jingxizhu

package seedu.duke.calendar;

import java.time.LocalDateTime;

public class Event {
    private String name;
    private LocalDateTime from;
    private LocalDateTime to;

    // Event is a constructor method for Event class.
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    // getName returns the name of the event.
    public String getName() {
        return name;
    }

    // setName is used for setting the event name.
    public void setName(String name) {
        this.name = name;
    }

    // getFrom is used for getting the start time of an Event.
    public String getFrom() {
        return from.toString();
    }

    // setFrom is used for setting the start time of an Event.
    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    // getFrom is used for getting the end time of an Event.
    public String getTo() {
        return to.toString();
    }

    // setFrom is used for setting the end time of an Event.
    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    // toString is used for formatting the print version of an Event.
    @Override
    public String toString() {
        return "Event '" + name + '\'' +
                " From: " + from +
                ", To: " + to;
    }

}
