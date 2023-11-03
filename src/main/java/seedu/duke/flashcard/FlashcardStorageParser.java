package seedu.duke.flashcard;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FlashcardStorageParser class
 * Utility class for parsing
 */
public final class FlashcardStorageParser {

    private static Logger flashlogger; // for logging

    /**
     * load a flash card from certain format
     * Tokens includes attributes of Flashcard
     * @param tokens
     * @return Flashcard object
     */
    public static Flashcard loadFlashcard(String[] tokens){

        flashlogger = Logger.getLogger("flash");
        flashlogger.setLevel(Level.WARNING);

        assert tokens.length == 4 : "Token length should be 4";

        flashlogger.log(Level.INFO, "token length is", tokens.length);

        int id = Integer.parseInt(tokens[0].trim());
        String frontText = tokens[1].trim();
        String backText = tokens[2].trim();
        int difficulty = Integer.parseInt(tokens[3].trim());


        Flashcard flashcard = new Flashcard(frontText, backText);

        flashcard.setId(id);
        flashcard.setDifficulty(difficulty);

        flashlogger.log(Level.INFO, "loaded flashcard");

        return flashcard;
    }
}
