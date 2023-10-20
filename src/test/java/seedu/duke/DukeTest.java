package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import seedu.duke.flashcard.FlashcardComponent;
import seedu.duke.flashcard.FlashcardStorage;

import java.util.ArrayList;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testFlashcardComponent_isResponsible_notResponsible() {
        FlashcardComponent flashcardComponent = new FlashcardComponent();

        assertFalse(flashcardComponent.isResponsible("dfdfdfdfdf"));
        assertFalse(flashcardComponent.isResponsible("help me"));
        assertFalse(flashcardComponent.isResponsible(" "));
    }

    @Test
    public void testFlashcardComponent_isResponsible_responsible() {
        FlashcardComponent flashcardComponent = new FlashcardComponent();

        assertTrue(flashcardComponent.isResponsible("create flashcard"));
        assertTrue(flashcardComponent.isResponsible("create flashcard  "));
        assertTrue(flashcardComponent.isResponsible("list flashcards"));
    }

    @Test
    public void testFlashcardStorage_isAvailable(){
        FlashcardComponent flashcardComponent = new FlashcardComponent();
        FlashcardStorage storage = flashcardComponent.getStorage();
        assertTrue(storage.isStorageAvailable());
    }

    @Test
    public void testCalendar() {
        assertTrue(true);
    }
}
