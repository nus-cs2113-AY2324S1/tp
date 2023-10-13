package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import seedu.duke.flashcard.FlashcardComponent;
import seedu.duke.flashcard.command.UnknownCommand;

import java.util.ArrayList;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testFlashcardComponent_isResponsible_notResponsible() {
        FlashcardComponent flashcardComponent = new FlashcardComponent(
                new ArrayList<>());

        assertFalse(flashcardComponent.isResponsible("dfdfdfdfdf"));
        assertFalse(flashcardComponent.isResponsible("help me"));
        assertFalse(flashcardComponent.isResponsible(" "));
    }

    @Test
    public void testFlashcardComponent_isResponsible_responsible() {
        FlashcardComponent flashcardComponent = new FlashcardComponent(
                new ArrayList<>());

        assertTrue(flashcardComponent.isResponsible("create flashcard"));
        assertTrue(flashcardComponent.isResponsible("create flashcard  "));
        assertTrue(flashcardComponent.isResponsible("list flashcards"));
    }
}
