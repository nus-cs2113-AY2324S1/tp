package seedu.duke.flashcard;

import java.time.LocalDateTime;

public class FlashcardReview {
    private LocalDateTime reviewDate;
    private FlashcardDifficulty reviewDifficulty;

    public FlashcardReview(LocalDateTime reviewDate, FlashcardDifficulty reviewDifficulty) {
        this.reviewDate = reviewDate;
        this.reviewDifficulty = reviewDifficulty;
    }
}
