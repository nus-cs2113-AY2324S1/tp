package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

public class ReviewByTagMode extends ReviewMode {
    public ReviewByTagMode(FlashcardList flashcardList) {
        super(flashcardList);
    }

    public String getReviewModeName() {
        return "review by tag mode";
    }

    protected Flashcard pickFlashcard() {
        return null;
    }
}
