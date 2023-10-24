//@@author wendelinwemhoener

package seedu.duke.flashcard;

import seedu.duke.flashcard.command.FlashcardCommand;
import seedu.duke.flashcard.command.CreateFlashcardCommand;
import seedu.duke.flashcard.command.ListFlashcardsCommand;
import seedu.duke.flashcard.command.StartReviewCommand;
import seedu.duke.flashcard.command.DeleteAllFlashcardsCommand;
import seedu.duke.flashcard.command.DeleteFlashcardCommand;
import seedu.duke.flashcard.command.UnknownCommand;

public class FlashcardCommandParser {
    public FlashcardCommand parseInput(String input) {
        assert input != null : "input is null";

        if (input.startsWith("create flashcard")) {
            return new CreateFlashcardCommand();
        } else if (input.startsWith("list flashcards")) {
            return new ListFlashcardsCommand();
        } else if (input.startsWith("review flashcards")) {
            return new StartReviewCommand();
        } else if (input.startsWith("delete all flashcards")) {
            return new DeleteAllFlashcardsCommand();
        } else if (input.startsWith("delete flashcard")) {
            return new DeleteFlashcardCommand();
        }

        return new UnknownCommand();
    }
}
