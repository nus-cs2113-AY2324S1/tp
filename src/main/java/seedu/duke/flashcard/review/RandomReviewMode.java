//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Review mode that randomly picks flashcards to review.
 *
 * This mode doesn't allow adjusting the difficulty of the reviewed flashcards.
 */
public class RandomReviewMode extends ReviewMode {
    public RandomReviewMode(FlashcardList flashcardList) {
        super(flashcardList);
    }

    /**
     * Identifies the instance as a random review mode instance.
     *
     * @return Textual description that this is the random review mode.
     */
    public String getReviewModeName() {
        return "random review mode";
    }

    /**
     * Chooses a random flashcard to be reviewed next.
     *
     * @return The next flashcard to review.
     */
    protected Flashcard pickFlashcard() {
        ArrayList<Flashcard> flashcards = flashcardList.getFlashcards();

        SecureRandom random = new SecureRandom();

        assert random != null : "must be valid SecureRandom instance";

        return flashcards.get(random.nextInt(flashcards.size()));
    }
}
