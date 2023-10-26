//@@author wendelinwemhoener

package seedu.duke.flashcard;

import seedu.duke.flashcard.review.FlashcardReview;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flashcard {
    private static int globalMaxId = 1;
    private String frontText;
    private String backText;
    private ArrayList<FlashcardReview> reviews;
    private LocalDateTime lastReviewOn;
    private int id;

    public Flashcard(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;

        reviews = new ArrayList<>();

        lastReviewOn = null;

        globalMaxId += 1;
        id = globalMaxId;
    }

    public static void calculateAndUpdateGlobalMaxId(FlashcardList flashcardList) {
        int currentMax = 1;

        for (Flashcard flashcard : flashcardList.getFlashcards()){
            if (flashcard.getId() > currentMax) {
                currentMax = flashcard.getId();
            }
        }

        globalMaxId = currentMax + 1;
    }

    public void setLastReviewOn(LocalDateTime lastReviewOn) {
        this.lastReviewOn = lastReviewOn;
    }

    public String getFrontText() {
        return frontText;
    }

    public int getId() {
        return id;
    }

    public String getBackText() {
        return backText;
    }

    public String toString() {
        return "front text: " + frontText + System.lineSeparator()
                + "back text: " + backText + System.lineSeparator()
                + "next review due on: " + lastReviewOn + System.lineSeparator()
                + "id: " + id + System.lineSeparator();
    }

    public void addReview(FlashcardReview flashcardReview) {
        reviews.add(flashcardReview);
    }
}
