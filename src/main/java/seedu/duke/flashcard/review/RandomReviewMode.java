//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.ArrayList;
import java.util.Random;

public class RandomReviewMode extends ReviewMode {
    public RandomReviewMode(FlashcardList flashcardList) {
        super(flashcardList);
    }

    public String getReviewModeName() {
        return "random review mode";
    }

    protected Flashcard pickFlashcard() {
        ArrayList<Flashcard> flashcards = flashcardList.getFlashcards();
        Random random = new Random();

        return flashcards.get(random.nextInt(flashcards.size()));
    }
}
