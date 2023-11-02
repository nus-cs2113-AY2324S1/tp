//@@author wendelinwemhoener

package seedu.duke.flashcard;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flashcard {
    private static int globalMaxId = 1;
    private String frontText;
    private String backText;
    private int id;
    private int difficulty;

    public Flashcard(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;

        difficulty = 5;  // initial difficulty of a flashcard

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

    public String getFrontText() {
        return frontText;
    }

    public int getId() {
        return id;
    }

    public String getBackText() {
        return backText;
    }

    //@@author junhyeong0411
    public void setId (int id) {
        this.id = id;
    }


    //@@author wendelinwemhoener
    public String toString() {
        return "front text: " + frontText + System.lineSeparator()
                + "back text: " + backText + System.lineSeparator()
                + "id: " + id + System.lineSeparator();
    }

    public void applyDifficultyChange(int difficultyChange) {
        difficulty += difficultyChange;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
