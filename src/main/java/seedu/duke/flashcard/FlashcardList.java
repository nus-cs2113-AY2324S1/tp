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

    // @@author junhyeong0411
    public int getSize(){
        return flashcards.size();
    }


    //@@author wendelinwemhoener
    public void add(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public void deleteAllFlashcards() {
        flashcards.clear();
    }

    public boolean isEmpty() {
        return flashcards.isEmpty();
    }

    public boolean deleteFlashcardById(int flashcardId) {
        int indexToDeleteAt = -1;

        for (int i = 0; i < flashcards.size(); i++) {
            if (flashcards.get(i).getId() == flashcardId) {
                indexToDeleteAt = i;
            }
        }

        if (indexToDeleteAt == -1) {
            return false;
        } else {
            flashcards.remove(indexToDeleteAt);
            return true;
        }
    }
}
