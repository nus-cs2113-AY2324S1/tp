//@@author kherlenbayasgalan

package seedu.duke.calendar.command;

import seedu.duke.calendar.Event;
import seedu.duke.calendar.EventList;

import java.util.Scanner;

public class ListCalendarEventsCommand extends EventCommand{
    public void execute(Scanner scanner, EventList flashcardList) {
        System.out.println("Here is a list of all your events: ");

        System.out.println("-".repeat(80));
        for (Flashcard flashcard : flashcardList.getFlashcards()) {
            System.out.print(flashcard);
            System.out.println("-".repeat(80));
        }
    }
}
