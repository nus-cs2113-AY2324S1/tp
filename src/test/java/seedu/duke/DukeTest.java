package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import seedu.duke.calendar.Calendar;
import seedu.duke.calendar.CalendarManager;
import seedu.duke.flashcard.exceptions.FlashcardException;
import seedu.duke.flashcard.exceptions.InvalidFlashcardIdException;
import seedu.duke.flashcard.exceptions.InvalidReviewModeException;
import seedu.duke.flashcard.review.RandomReviewMode;
import seedu.duke.flashcard.review.ReviewMode;
import seedu.duke.flashcard.review.SpacedRepetitionReviewMode;
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
    public void testFlashcardComponent_isResponsible_notResponsible() {
        FlashcardComponent flashcardComponent = new FlashcardComponent(new Calendar());

        assertFalse(flashcardComponent.isResponsible("dfdfdfdfdf"));
        assertFalse(flashcardComponent.isResponsible("help me"));
        assertFalse(flashcardComponent.isResponsible(" "));
    }

    @Test
    public void testFlashcardComponent_isResponsible_responsible() {
        FlashcardComponent flashcardComponent = new FlashcardComponent(
                new Calendar());

        assertTrue(flashcardComponent.isResponsible("create flashcard"));
        assertTrue(flashcardComponent.isResponsible("delete flashcard  b"));
        assertTrue(flashcardComponent.isResponsible("list flashcards"));
        assertTrue(flashcardComponent.isResponsible("delete all flashcards"));
        assertTrue(flashcardComponent.isResponsible("review flashcards"));
        assertTrue(flashcardComponent.isResponsible("review flashcards c"));
    }

    @Test
    public void testReviewMode_getReviewModeName_randomReviewMode() {
        FlashcardList fl = new FlashcardList(new ArrayList<>());
        ReviewMode rm = new RandomReviewMode(fl);

        assertEquals(rm.getReviewModeName(), "random review mode");
    }

    @Test
    public void testFlashcardException_initializeException() {
        InvalidFlashcardIdException e1 = new InvalidFlashcardIdException();
        InvalidReviewModeException e2 = new InvalidReviewModeException();

        assertTrue(e1 instanceof FlashcardException);
        assertTrue(e2 instanceof FlashcardException);
        assertTrue(e1 instanceof Exception);
    }

    @Test
    public void testReviewMode_getReviewModeName_spacedRepetitionReviewMode() {
        FlashcardList fl = new FlashcardList(new ArrayList<>());
        ReviewMode rm = new SpacedRepetitionReviewMode(fl);

        assertEquals(rm.getReviewModeName(), "spaced repetition mode");
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
    public void sampleTestCalendar() {
        assertTrue(true);
    }

    @Test
    public void testCalendar_isResponsible_true() {
        CalendarManager calendarManager = new CalendarManager(new Calendar(), new ArrayList<>());

        assertTrue(calendarManager.isResponsible("add event"));
        assertTrue(calendarManager.isResponsible("list events  "));
        assertTrue(calendarManager.isResponsible("delete event"));
    }

    @Test
    public void testCalendar_isResponsible_false() {
        CalendarManager calendarManager = new CalendarManager(new Calendar(), new ArrayList<>());

        assertFalse(calendarManager.isResponsible("add"));
        assertFalse(calendarManager.isResponsible("hello"));
        assertFalse(calendarManager.isResponsible("event"));
    }
}
