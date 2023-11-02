//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.ArrayList;

public class SpacedRepetitionReviewMode extends ReviewMode {
    public SpacedRepetitionReviewMode(FlashcardList flashcardList) {
        super(flashcardList);
    }

    public String getReviewModeName() {
        return "spaced repetition mode";
    }

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

        return maxDifficultyFlashcard;
    }
}
