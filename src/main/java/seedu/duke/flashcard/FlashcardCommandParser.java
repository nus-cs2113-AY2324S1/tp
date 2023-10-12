package seedu.duke.flashcard;

import seedu.duke.flashcard.command.CreateFlashcardCommand;
import seedu.duke.flashcard.command.FlashcardCommand;
import seedu.duke.flashcard.command.ListFlashcardsCommand;
import seedu.duke.flashcard.command.UnknownCommand;

public class FlashcardCommandParser {
    public FlashcardCommand parseInput(String input) {
        if (input.startsWith("create flashcard")) {
            return new CreateFlashcardCommand();
        } else if (input.startsWith("list flashcards")) {
            return new ListFlashcardsCommand();
        }

        return new UnknownCommand();
    }
}
