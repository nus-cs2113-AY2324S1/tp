//@@author wendelinwemhoener

package seedu.duke.flashcard;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Container class for a list of flashcards.
 * Exposes a simple, unified API for dealing with a list of flashcards.
 */
public class FlashcardList {
    private ArrayList<Flashcard> flashcards;
    private Logger logger;

    /**
     * Instantiates and returns a FlashcardList holding the passed flashcards.
     *
     * @param flashcards The flashcards to be contained by the FlashcardList.
     */
    public FlashcardList(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;

        logger = Logger.getLogger("FlashcardUi");
        logger.setLevel(Level.WARNING);
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
        logger.log(Level.INFO, "clearing the list of flashcards");

        flashcards.clear();

        assert flashcards.size() == 0 : "flashcardList should be empty now";
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
        logger.log(Level.INFO, "trying to delete flashcard by id");

        int indexToDeleteAt = -1;

        for (int i = 0; i < flashcards.size(); i++) {
            if (flashcards.get(i).getId() == flashcardId) {
                indexToDeleteAt = i;
            }
        }

        if (indexToDeleteAt == -1) {
            logger.log(Level.INFO, "deletion was unsuccessful");
            return false;
        } else {
            flashcards.remove(indexToDeleteAt);
            logger.log(Level.INFO, "successfully deleted flashcard");
            return true;
        }
    }
}
