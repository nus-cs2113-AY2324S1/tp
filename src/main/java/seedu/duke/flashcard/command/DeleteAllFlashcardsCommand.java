package seedu.duke.flashcard.command;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public class DeleteAllFlashcardsCommand extends FlashcardCommand {
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        flashcardList.deleteAllFlashcards();

        System.out.println("    All your flashcards have been successfully " +
                "deleted .");
    }
}
