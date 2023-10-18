package seedu.duke.calendar;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventStorage {
    ArrayList<Event> events;

    public EventStorage() {
        events = new ArrayList<>();
    }

    public void addEvent(String name, LocalDateTime from, LocalDateTime to) {
        events.add(new Event(name, from, to));
    }

    public boolean delEvent(String name) {
        for(Event event : events) {
            if (event.getName().equals(name)) {
                events.remove(event);
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "events=" + events;
    }
}
