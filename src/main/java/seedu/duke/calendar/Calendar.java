package seedu.duke.calendar;

import java.time.LocalDateTime;

public class Calendar {
    EventStorage eventStorage;

    public Calendar() {
        eventStorage = new EventStorage();
    }

    public void addEvent(String name, LocalDateTime from, LocalDateTime to) {
        eventStorage.addEvent(name, from, to);
    }

    public void delEvent(String eventName) {
        eventStorage.delEvent(eventName);
    }

    public void listEvents() {
        System.out.println(eventStorage);
    }
}
