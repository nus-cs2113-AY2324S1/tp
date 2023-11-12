//@@author wendelinwemhoener

package seedu.duke.flashcard;

/**
 * Represents a flashcard with its associated front and back text as well as
 * its id and current difficulty.
 */
public class Flashcard {
    private static int globalMaxId = 1;
    private String frontText;
    private String backText;
    private int id;
    private int difficulty;

    /**
     * Instantiates and returns a flashcard with the given front and back text.
     * Its id and difficulty (which is 5) are automatically set.
     *
     * @param frontText The text on the front of the flashcard.
     * @param backText The text on the back of the flashcard.
     */
    public Flashcard(String frontText, String backText) {
        this.frontText = frontText;
        this.backText = backText;

        difficulty = 5;  // initial difficulty of a flashcard

        globalMaxId += 1;
        id = globalMaxId;

        assert globalMaxId >= id : "No id must be bigger than globalMaxId";
    }

    /**
     * Updates globalMaxId to be greater than the id of all passed flashcards.
     *
     * @param flashcardList List of flashcard to calculate the max id over.
     */
    public static void calculateAndUpdateGlobalMaxId(
            FlashcardList flashcardList) {
        int currentMax = 1;

        for (Flashcard flashcard : flashcardList.getFlashcards()){
            if (flashcard.getId() > currentMax) {
                currentMax = flashcard.getId();
            }
        }

        globalMaxId = currentMax;
    }

    public int getId() {
        return id;
    }

    //@@author junhyeong0411
    public void setId (int id) {
        this.id = id;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getBackText() {
        return backText;
    }


    //@@author wendelinwemhoener
    /**
     * Returns a string representation of this flashcard.
     *
     * @return Front and back text as well as id and difficulty.
     */
    public String toString() {
        return "front text: " + frontText + System.lineSeparator()
                + "back text: " + backText + System.lineSeparator()
                + "id: " + id + System.lineSeparator()
                + "difficulty: " + difficulty + System.lineSeparator();
    }

    /**
     * Adjusts (increases) the difficulty of this flashcard.
     *
     * @param difficultyChange The amount to increase the difficulty by.
     */
    public void applyDifficultyChange(int difficultyChange) {
        difficulty += difficultyChange;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
