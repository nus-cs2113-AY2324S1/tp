package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public class DeleteFlashcardCommand extends FlashcardCommand {
    public void execute(Scanner scanner, FlashcardList flashcardList) {
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
