//@@author wendelinwemhoener

package seedu.duke.flashcard;

import seedu.duke.flashcard.review.FlashcardReview;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flashcard {
    private static int current_max_id = 1;
    private String frontText;
    private String backText;
    private ArrayList<String> tags;
    private ArrayList<FlashcardReview> reviews;
    private LocalDateTime lastReviewOn;
    private int id;

    public Flashcard(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;

        tags = new ArrayList<>();
        reviews = new ArrayList<>();

        lastReviewOn = null;

        current_max_id += 1;
        id = current_max_id;
    }

    public void setLastReviewOn(LocalDateTime lastReviewOn) {
        this.lastReviewOn = lastReviewOn;
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
                + "next review due on: " + lastReviewOn + System.lineSeparator()
                + "id: " + id + System.lineSeparator();
    }

    public void addReview(FlashcardReview flashcardReview) {
        reviews.add(flashcardReview);
    }
}
