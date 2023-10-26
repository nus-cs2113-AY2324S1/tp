//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public class DeleteFlashcardCommand extends DualFlashcardCommand {
    private String input = null;

    public DeleteFlashcardCommand(String input) {
        this.input = input;
        beginnerCommandLength = 2;
        expertCommandLength = 3;
        syntaxString = "delete flashcard FLASHCARD_ID";
    }

    protected void executeBeginnerMode(Scanner scanner,
                                    FlashcardList flashcardList) {
        System.out.println("    Enter id of the flashcard you want to delete:" +
                " ");

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

    protected void executeExpertMode(Scanner scanner,
                                  FlashcardList flashcardList) {
        String[] commandParts = input.split(" ");

        try {
            int flashcardId = Integer.parseInt(commandParts[2]);
            deleteFlashcardById(flashcardId, flashcardList);
        } catch (NumberFormatException e) {
            System.out.println("    Invalid id! Id must be an integer");
        }
    }

    private void deleteFlashcardById(int flashcardId, FlashcardList flashcardList) {
        boolean deletionWasSuccessful =
                flashcardList.deleteFlashcardById(flashcardId);

        if (deletionWasSuccessful) {
            System.out.println("    Flashcard with id " + flashcardId + " has been " +
                    "successfully deleted.");
        } else {
            System.out.println("    Couldn't find a flashcard with id " + flashcardId);
            System.out.println("    No deletion has been performed. Please " +
                    "try again with a valid id.");
        }
    }
}
