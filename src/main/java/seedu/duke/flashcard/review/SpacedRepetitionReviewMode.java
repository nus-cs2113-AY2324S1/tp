//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

public class SpacedRepetitionReviewMode extends ReviewMode {
    public SpacedRepetitionReviewMode(FlashcardList flashcardList) {
        super(flashcardList);
    }

    public String getReviewModeName() {
        return "spaced repetition mode";
    }

    protected Flashcard pickFlashcard() {
        return null;
    }
}
