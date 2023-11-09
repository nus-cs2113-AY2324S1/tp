//@@author wendelinwemhoener

package seedu.duke.flashcard;

import seedu.duke.flashcard.command.FlashcardCommand;
import seedu.duke.flashcard.command.CreateFlashcardCommand;
import seedu.duke.flashcard.command.ListFlashcardsCommand;
import seedu.duke.flashcard.command.StartReviewCommand;
import seedu.duke.flashcard.command.DeleteAllFlashcardsCommand;
import seedu.duke.flashcard.command.DeleteFlashcardCommand;
import seedu.duke.flashcard.command.UnknownCommand;

/**
 * Parses input entered by the user into a FlashcardCommand for further
 * processing.
 */
public class FlashcardCommandParser {
    /**
     * Returns the FlashcardCommand corresponding to the passed input.
     *
     * @param input The text inputted by the user.
     * @return The FlashcardCommand corresponding to input
     */
    public FlashcardCommand parseInput(String input) {
        assert input != null : "input is null";

        input = input.toLowerCase();

        if (input.strip().startsWith("create flashcard")) {
            return new CreateFlashcardCommand();
        } else if (input.strip().startsWith("list flashcards")) {
            return new ListFlashcardsCommand();
        } else if (input.startsWith("review flashcards")) {
            return new StartReviewCommand(input);
        } else if (input.strip().startsWith("delete all flashcards")) {
            return new DeleteAllFlashcardsCommand();
        } else if (input.startsWith("delete flashcard")) {
            return new DeleteFlashcardCommand(input);
        }

        return new UnknownCommand();
    }
}
