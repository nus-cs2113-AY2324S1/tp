//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

/**
 * This command represents an unknown command.
 */
public class UnknownCommand extends FlashcardCommand {
    /**
     * Prints an error message and returns.
     *
     * @param scanner Scanner that allows handling user input.
     * @param flashcardList Which flashcards to perform actions on.
     */
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        System.out.println("Unknown command! Please try again.");
    }
}
