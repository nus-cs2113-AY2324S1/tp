package seedu.duke.calendar;


import java.util.ArrayList;

public class EventList {
    private ArrayList<Event> eventList;

    public EventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public ArrayList<Event> getEvent() {
        return eventList;
    }

    public void deleteEvent(String name) {
        eventList.removeIf(event -> event.getName().equals(name));
    }
    @Override
    public String toString() {
        return "EventStorage{" +
                "events=" + eventList +
                '}';
    }
}
