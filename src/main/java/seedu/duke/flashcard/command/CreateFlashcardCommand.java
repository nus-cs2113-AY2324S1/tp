//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

/**
 * This command allows creating a new flashcard by inputting its front and
 * back text.
 */
public class CreateFlashcardCommand extends FlashcardCommand {
    /**
     * Creates a new flashcard.
     * The user is asked to input the front and back text of the new
     * flashcard, and then it is added to the flashcardList.
     *
     * @param scanner Scanner that allows handling user input.
     * @param flashcardList Which flashcards to perform actions on.
     */
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        System.out.print("    Enter the front page text: ");
        String frontPageText = scanner.nextLine();

        while (frontPageText.strip().equals("")) {
            System.out.println("        Invalid input! The front text must " +
                    "contain at least one letter or digit!");

            System.out.print("    Enter the front page text: ");
            frontPageText = scanner.nextLine();
        }

        System.out.print("    Enter the back page text: ");
        String backPageText = scanner.nextLine();

        while (backPageText.strip().equals("")) {
            System.out.println("        Invalid input! The back text must " +
                    "contain at least one letter or digit!");

            System.out.print("    Enter the back page text: ");
            backPageText = scanner.nextLine();
        }

        Flashcard flashcard = new Flashcard(frontPageText, backPageText);

        flashcardList.add(flashcard);

        System.out.println();
        System.out.println("    Success! Flashcard has been added to your " +
                "collection.");
    }
}
