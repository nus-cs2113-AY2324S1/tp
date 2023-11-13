package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

/**
 * This command allows deleting all current flashcards.
 */
public class DeleteAllFlashcardsCommand extends FlashcardCommand {
    /**
     * Deletes all flashcards from the flashcardList.
     *
     * @param scanner Scanner that allows handling user input.
     * @param flashcardList Which flashcards to perform actions on.
     */
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        flashcardList.deleteAllFlashcards();

        System.out.println("    All your flashcards have been successfully " +
                "deleted.");

        assert flashcardList.isEmpty() : "flashcardList must be empty";
    }
}
