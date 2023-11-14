//@@author Cheezeblokz

package seedu.duke.calendar;

public class Calendar {
    /**
     * The class is here for integrating Flashcard goals with the Calendar.
     * Any other features related to the Calendar can be added here in the future.
     */
    EventList eventList;

    public Calendar() {
    }

    public void setEventList(EventList eventList) {
        this.eventList = eventList;
    }

    public void incrementFlashcardCount() {
        for(Event event : eventList.getEvents()) {
            if (event.getClass() == Goal.class) {
                ((Goal) event).flashcardCompleted();
            }
        }
    }
}
