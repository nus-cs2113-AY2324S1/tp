//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import java.time.LocalDateTime;

public class FlashcardReview {
    private LocalDateTime reviewDate;
    private ReviewDifficulty reviewDifficulty;

    public FlashcardReview(LocalDateTime reviewDate, ReviewDifficulty reviewDifficulty) {
        this.reviewDate = reviewDate;
        this.reviewDifficulty = reviewDifficulty;
    }
}
