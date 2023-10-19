//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public class CreateFlashcardCommand extends FlashcardCommand {
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        System.out.print("    Enter the front page text: ");
        String frontPageText = scanner.nextLine();
        System.out.print("    Enter the back page text: ");
        String backPageText = scanner.nextLine();

        Flashcard flashcard = new Flashcard(frontPageText, backPageText);

        flashcardList.add(flashcard);

        System.out.println();
        System.out.println("    Success! Flashcard has been added to your " +
                "collection.");
    }
}
