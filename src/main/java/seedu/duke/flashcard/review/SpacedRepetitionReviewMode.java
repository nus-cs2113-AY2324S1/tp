//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.ArrayList;

/**
 * Review mode that picks the flashcards with the highest difficulty.
 *
 * After each flashcard review, the reviewed flashcard's difficulty is
 * adjusted based on the user input. This way, harder flashcards are shown
 * more often and easier ones are shown less frequently to enhance learning
 * efficiency.
 */
public class SpacedRepetitionReviewMode extends ReviewMode {
    public SpacedRepetitionReviewMode(FlashcardList flashcardList) {
        super(flashcardList);
    }

    /**
     * Identifies the instance as a spaced repetition review mode instance.
     *
     * @return Textual description that this is the spaced repetition mode.
     */
    public String getReviewModeName() {
        return "spaced repetition mode";
    }

    /**
     * Chooses the flashcard with the highest difficulty to be reviewed next.
     *
     * @return The next flashcard to review.
     */
    protected Flashcard pickFlashcard() {
        ArrayList<Flashcard> flashcards = flashcardList.getFlashcards();

        int maxDifficulty = Integer.MIN_VALUE;
        Flashcard maxDifficultyFlashcard = null;

        for (Flashcard flashcard : flashcards) {
            if (flashcard.getDifficulty() > maxDifficulty) {
                maxDifficulty = flashcard.getDifficulty();
                maxDifficultyFlashcard = flashcard;
            }
        }

        assert maxDifficultyFlashcard != null : "there should be a flashcard";
        assert maxDifficulty != Integer.MIN_VALUE;

        return maxDifficultyFlashcard;
    }
}
