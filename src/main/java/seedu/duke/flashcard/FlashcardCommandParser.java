package seedu.duke.flashcard;

import seedu.duke.flashcard.command.*;

public class FlashcardCommandParser {
    public FlashcardCommand parseInput(String input) {
        if (input.startsWith("create flashcard")) {
            return new CreateFlashcardCommand();
        } else if (input.startsWith("list flashcards")) {
            return new ListFlashcardsCommand();
        } else if (input.startsWith("start review")) {
            return new StartReviewCommand();
        }

        return new UnknownCommand();
    }
}
