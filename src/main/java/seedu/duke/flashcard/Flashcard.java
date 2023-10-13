package seedu.duke.flashcard;

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

    public String toString() {
        return "-".repeat(80) + System.lineSeparator()
                + "front text: " + frontText + System.lineSeparator()
                + "back text: " + backText + System.lineSeparator()
                + "tags: " + tags.toString() + System.lineSeparator()
                + "next review due on: " + nextReviewOn + System.lineSeparator()
                + "-".repeat(80);
    }
}
