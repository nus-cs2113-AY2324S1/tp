//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

/**
 * This command allows listing all current flashcards.
 */
public class ListFlashcardsCommand extends FlashcardCommand {
    /**
     * Prints out a list of all current flashcards.
     * Handles the scenario that there are no flashcards by printing an
     * appropriate "You dont't have any flashcards yet!" message.
     *
     * @param scanner Scanner that allows handling user input.
     * @param flashcardList Which flashcards to perform actions on.
     */
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        if (flashcardList.isEmpty()) {
            System.out.println("    You dont't have any flashcards yet! ");
            return;
        }

        assert flashcardList.isEmpty() == false : "must contain flashcards";

        System.out.println("    Here is a list of all your flashcards: ");
        System.out.println();

        System.out.println("-".repeat(80));
        for (Flashcard flashcard : flashcardList.getFlashcards()) {
            System.out.print(flashcard);
            System.out.println("-".repeat(80));
        }
        System.out.println();

        System.out.println("    Those were all your flashcards.");
    }
}
