//@@author Brian030601

package seedu.duke.calendar;

import java.util.ArrayList;

public class EventList {
    //@@author Brian030601
    private ArrayList<Event> eventList;

    // EventList is a constructor method
    public EventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    // addEvent is used for adding an event to the EventList.
    public void addEvent(Event event) {
        eventList.add(event);
    }

    // getEvent is used to get an event from the EventList.
    public ArrayList<Event> getEvents() {
        return eventList;
    }

    // getSize is used for getting the size of EventList.
    public int getSize() {
        return eventList.size();
    }

    // findEvent is used for finding a specific event from the EventList.
    public int findEvent(String keyword) {
        int count = 0;
        for (Event event: eventList) {
            if (event.getName().contains(keyword)) {
                System.out.println((++count) + ". " + event);
            }
        }

        return count;
    }

    // deleteEvent is used for deleting an event from the EventList
    public int deleteEvent(String name) {
        int size = eventList.size();
        if (size > 0) {
            eventList.removeIf(event -> event.getName().equals(name));
        } else {
            System.out.println("The Calendar is empty");
        }

        return size;
    }

    // deleteAllEvents is used for deleting all events in the EventList.
    public void deleteAllEvents() {
        eventList.clear();
    }

    // toString formats the print version of EventList.
    @Override
    public String toString() {
        return "EventStorage{" +
                "events=" + eventList +
                '}';
    }
}
