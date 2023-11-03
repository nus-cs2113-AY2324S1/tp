//@@author wendelinwemhoener

package seedu.duke.flashcard;

import java.util.ArrayList;

/**
 * Container class for a list of flashcards.
 * Exposes a simple, unified API for dealing with a list of flashcards.
 */
public class FlashcardList {
    private ArrayList<Flashcard> flashcards;

    /**
     * Instantiates and returns a FlashcardList holding the passed flashcards.
     *
     * @param flashcards The flashcards to be contained by the FlashcardList.
     */
    public FlashcardList(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    /**
     * Returns the amount of flashcards held by the FlashcardList.
     *
     * @return The size of the FlashcardList.
     */
    public int getSize(){
        return flashcards.size();
    }

    /**
     * Adds a flashcard to the FlashcardList.
     *
     * @param flashcard The flashcard that shall be added.
     */
    public void add(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    /**
     * Deletes all flashcards in the FlashcardList, effectively emptying it.
     */
    public void deleteAllFlashcards() {
        flashcards.clear();
    }

    /**
     * Returns whether the FlashcardList contains any flashcards or not.
     *
     * @return If the FlashcardList is empty.
     */
    public boolean isEmpty() {
        return flashcards.isEmpty();
    }

    /**
     * Attempts to delete a flashcard by the passed id.
     * If no flashcard with the passed id exists, returns false.
     *
     * @param flashcardId The id of the flashcard to delete.
     * @return Whether the deletion was successful (true if successful).
     */
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
