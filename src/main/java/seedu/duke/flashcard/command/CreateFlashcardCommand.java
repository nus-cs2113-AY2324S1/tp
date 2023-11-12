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
        assert scanner != null : "Must be a valid Scanner instance";
        assert flashcardList != null : "Must be a valid FlashcardList " +
                "instance";

        String frontPageText = getInputUntilNonEmptyString(scanner, "front");
        String backPageText = getInputUntilNonEmptyString(scanner, "back");

        Flashcard flashcard = new Flashcard(frontPageText, backPageText);

        flashcardList.add(flashcard);

        System.out.println();
        System.out.println("    Success! Flashcard has been added to your " +
                "collection.");
    }

    /**
     * Gets a user input for a flashcard, making sure that it is non-empty.
     *
     * @param scanner To get user input.
     * @param flashcardSide Which side of the flashcard (front or back) is
     *                      targeted.
     * @return The user input for the specified flashcardSide.
     */
    private String getInputUntilNonEmptyString(Scanner scanner,
                                               String flashcardSide) {
        System.out.print("    Enter the " + flashcardSide + " page text: ");
        String text = scanner.nextLine();

        while (text.strip().equals("")) {
            System.out.println("        Invalid input! The " + flashcardSide +
                    " text must contain at least one letter or digit!");

            System.out.print("    Enter the " + flashcardSide
                    + " page text: ");
            text = scanner.nextLine();
        }

        assert text != null : "Must be a non-null string";
        assert (!text.strip().equals("")) : "Must be a non-empty string";
        return text;
    }
}
