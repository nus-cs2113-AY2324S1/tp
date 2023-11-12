package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import seedu.duke.calendar.Calendar;
import seedu.duke.calendar.CalendarManager;
import seedu.duke.storage.EventStorage;
import seedu.duke.flashcard.FlashcardComponent;
import seedu.duke.flashcard.FlashcardList;
import seedu.duke.storage.FlashcardStorage;
import seedu.duke.flashcard.FlashcardUi;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testFlashcardComponent_isResponsible_notResponsible() {
        FlashcardComponent flashcardComponent = new FlashcardComponent(new Calendar());

        assertFalse(flashcardComponent.isResponsible("dfdfdfdfdf"));
        assertFalse(flashcardComponent.isResponsible("help me"));
        assertFalse(flashcardComponent.isResponsible(" "));
    }

    @Test
    public void testFlashcardComponent_isResponsible_responsible() {
        FlashcardComponent flashcardComponent = new FlashcardComponent(new Calendar());

        assertTrue(flashcardComponent.isResponsible("create flashcard"));
        assertTrue(flashcardComponent.isResponsible("create flashcard  "));
        assertTrue(flashcardComponent.isResponsible("list flashcards"));
    }

    @Test
    public void testFlashcardStorage_isAvailable(){
        FlashcardComponent flashcardComponent = new FlashcardComponent(new Calendar());
        FlashcardStorage storage = flashcardComponent.getStorage();
        assertTrue(storage.isStorageAvailable());
    }

    @Test
    public void testEventStorage_isAvailable(){
        CalendarManager calendarManager = new CalendarManager(new Calendar(), new ArrayList<>());
        EventStorage storage = calendarManager.getStorage();
        assertTrue(storage.isStorageAvailable());
    }

    @Test
    public void testFlashcardStorage_isSavingCorrectly(){
        String input = "create flashcard\n";
        input += "Hello\n";
        input += "Duke\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);

        FlashcardComponent flashcardComponent = new FlashcardComponent(new Calendar());
        FlashcardStorage storage = flashcardComponent.getStorage();

        FlashcardUi ui = flashcardComponent.getUi();
        ui.setScanner(scanner);

        flashcardComponent.processInput(scanner.nextLine());

        FlashcardList flashcardList = flashcardComponent.getFlashcardList();

        assertTrue(storage.saveFlashcards(flashcardList.getFlashcards()));
    }


    @Test
    public void testCalendar() {
        assertTrue(true);
    }
}
