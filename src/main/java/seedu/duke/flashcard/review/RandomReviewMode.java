//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.security.SecureRandom;
import java.util.ArrayList;

public class RandomReviewMode extends ReviewMode {
    public RandomReviewMode(FlashcardList flashcardList) {
        super(flashcardList);
    }

    public String getReviewModeName() {
        return "random review mode";
    }

    protected Flashcard pickFlashcard() {
        ArrayList<Flashcard> flashcards = flashcardList.getFlashcards();

        SecureRandom random = new SecureRandom();

        return flashcards.get(random.nextInt(flashcards.size()));
    }
}
