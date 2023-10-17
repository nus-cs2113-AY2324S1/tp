//@@author wendelinwemhoener

package seedu.duke.flashcard;

import java.util.ArrayList;

public class FlashcardList {
    private ArrayList<Flashcard> flashcards;

    public FlashcardList(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }


    public void add(Flashcard flashcard) {
        flashcards.add(flashcard);
    }
}
