package seedu.duke.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

public class EventStorage {
    ArrayList<Event> events;

    public EventStorage() {
        events = new ArrayList<>();
    }

    public void addEvent(String name, LocalDate from, LocalDate to) {
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
        return "EventStorage{" +
                "events=" + events +
                '}';
    }
}
