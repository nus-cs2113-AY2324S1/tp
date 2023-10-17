//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public abstract class FlashcardCommand {
    public abstract void execute(Scanner scanner, FlashcardList flashcardList);
}
