//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

/**
 * This command allows deleting a specific flashcard (by its id).
 */
public class DeleteFlashcardCommand extends DualFlashcardCommand {
    public DeleteFlashcardCommand(String input) {
        this.input = input;
        beginnerCommandLength = 2;
        expertCommandLength = 3;
        syntaxString = "delete flashcard FLASHCARD_ID";
    }

    /**
     * Starts an interactive process for deleting a flashcard by its id.
     * The user is prompted to enter the id; and then it is deleted.
     *
     * @param scanner Scanner for getting user input.
     * @param flashcardList FlashcardList from which to delete.
     */
    protected void executeBeginnerMode(Scanner scanner,
                                    FlashcardList flashcardList) {
        assert flashcardList != null : "Must be a valid flashcardList " +
                "instance";

        System.out.println("    Enter id of the flashcard you want to " +
                "delete: ");

        String input = scanner.nextLine();
        int flashcardId;

        try {
            flashcardId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("    Invalid input! Id must be an integer");
            return;
        }

        deleteFlashcardById(flashcardId, flashcardList);
    }

    /**
     * Allows deleting a flashcard whose id has already been inputted.
     *
     * @param scanner Scanner for getting user input.
     * @param flashcardList FlashcardList from which to delete.
     */
    protected void executeExpertMode(Scanner scanner,
                                  FlashcardList flashcardList) {
        assert flashcardList != null : "Must be a valid flashcardList " +
                "instance";

        String[] commandParts = input.split(" ");
        assert commandParts.length != 0 : "must contain command parts";

        try {
            int flashcardId = Integer.parseInt(commandParts[2]);
            deleteFlashcardById(flashcardId, flashcardList);
        } catch (NumberFormatException e) {
            System.out.println("    Invalid id! Id must be an integer");
        }
    }

    /**
     * Tries to delete a flashcard by id and prints whether it succeeded.
     *
     * @param flashcardId The id of the flashcard to delete.
     * @param flashcardList The list of all known flashcards.
     */
    private void deleteFlashcardById(int flashcardId,
                                     FlashcardList flashcardList) {
        boolean deletionWasSuccessful =
                flashcardList.deleteFlashcardById(flashcardId);

        if (deletionWasSuccessful) {
            System.out.println("    Flashcard with id " + flashcardId
                    + " has been successfully deleted.");
        } else {
            System.out.println("    Couldn't find a flashcard with id "
                    + flashcardId);
            System.out.println("    No deletion has been performed. Please "
                    + "try again with a valid id.");
        }
    }
}
