//@@ kherlenbayasgalan

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

    // for logging
    public int getSize() {
        return eventList.size();
    }

    public int findEvent(String keyword) {
        int count = 0;
        for (Event event: eventList) {
            if (event.getName().contains(keyword)) {
                System.out.println((++count) + ". " + event);
            }
        }

        return count;
    }

    public int deleteEvent(String name) {
        int size = eventList.size();
        if (size > 0) {
            eventList.removeIf(event -> event.getName().equals(name));
        } else {
            System.out.println("The Calendar is empty");
        }

        return size;
    }

    public void deleteAllEvents() {
        eventList.clear();
    }

    @Override
    public String toString() {
        return "EventStorage{" +
                "events=" + eventList +
                '}';
    }
}
