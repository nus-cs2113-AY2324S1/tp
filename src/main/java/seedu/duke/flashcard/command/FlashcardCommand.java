//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

/**
 * Abstract class that provides the interface for executing commands by using
 * a Scanner for input and a FlashcardList to perform actions on as command
 * execution context.
 */
public abstract class FlashcardCommand {
    /**
     * Executes a command, given the appropriate context.
     * This context consists of a scanner for handling input and a
     * FlashcardList to execute actions on.
     *
     * @param scanner Scanner that allows handling user input.
     * @param flashcardList Which flashcards to perform actions on.
     */
    public abstract void execute(Scanner scanner, FlashcardList flashcardList);
}
