//@@author wendelinwemhoener

package seedu.duke.flashcard;

import seedu.duke.calendar.Calendar;
import seedu.duke.flashcard.command.FlashcardCommand;
import seedu.duke.flashcard.command.CreateFlashcardCommand;
import seedu.duke.flashcard.command.ListFlashcardsCommand;
import seedu.duke.flashcard.command.StartReviewCommand;
import seedu.duke.flashcard.command.DeleteAllFlashcardsCommand;
import seedu.duke.flashcard.command.DeleteFlashcardCommand;
import seedu.duke.flashcard.command.UnknownCommand;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses input entered by the user into a FlashcardCommand for further
 * processing.
 */
public class FlashcardCommandParser {
    private Logger logger;

    /**
     * Returns the FlashcardCommand corresponding to the passed input.
     *
     * @param input The text inputted by the user.
     * @return The FlashcardCommand corresponding to input
     */
    public FlashcardCommand parseInput(String input, Calendar calendar) {
        assert input != null : "input must not be null";

        logger = Logger.getLogger("FlashcardCommandParser");
        logger.setLevel(Level.WARNING);

        input = input.toLowerCase().strip();

        logger.log(Level.INFO, "trying to find matching FlashcardCommand");

        if (input.equals("create flashcard")) {
            return new CreateFlashcardCommand();
        } else if (input.equals("list flashcards")) {
            return new ListFlashcardsCommand();
        } else if (input.startsWith("review flashcards")) {
            String[] commandParts = input.split(" ");

            if (commandParts[0].equals("review") && commandParts[1].equals(
                    "flashcards")) {
                return new StartReviewCommand(input, calendar);
            }
        } else if (input.equals("delete all flashcards")) {
            return new DeleteAllFlashcardsCommand();
        } else if (input.startsWith("delete flashcard")) {
            String[] commandParts = input.split(" ");

            if (commandParts[0].equals("delete") && commandParts[1].equals(
                    "flashcard")) {
                return new DeleteFlashcardCommand(input);
            }
        }

        logger.log(Level.INFO, "input doesn't match any know command");

        return new UnknownCommand();
    }
}
