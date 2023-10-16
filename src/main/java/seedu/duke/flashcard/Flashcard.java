//@@author wendelinwemhoener

package seedu.duke.flashcard;

import seedu.duke.flashcard.review.FlashcardReview;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flashcard {
    private String frontText;
    private String backText;
    private ArrayList<String> tags;
    private ArrayList<FlashcardReview> reviews;
    private LocalDateTime nextReviewOn;

    public Flashcard(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;

        tags = new ArrayList<>();
        reviews = new ArrayList<>();

        nextReviewOn = null;
    }

    public String getFrontText() {
        return frontText;
    }

    public String getBackText() {
        return backText;
    }

    public String toString() {
        return "front text: " + frontText + System.lineSeparator()
                + "back text: " + backText + System.lineSeparator()
                + "tags: " + tags.toString() + System.lineSeparator()
                + "next review due on: " + nextReviewOn + System.lineSeparator();
    }
}
