//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public class ListFlashcardsCommand extends FlashcardCommand {
    public void execute(Scanner scanner, FlashcardList flashcardList) {
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
