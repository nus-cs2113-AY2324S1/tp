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

    public boolean deleteEvent(String name) {
        for(Event event : eventList) {
            if (event.getName().equals(name)) {
                eventList.remove(event);
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return "EventStorage{" +
                "events=" + eventList +
                '}';
    }
}
