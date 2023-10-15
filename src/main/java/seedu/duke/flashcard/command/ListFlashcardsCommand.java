package seedu.duke.flashcard.command;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public class ListFlashcardsCommand extends FlashcardCommand {
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        for (Flashcard flashcard : flashcardList.getFlashcards()) {
            System.out.println(flashcard);
        }
    }
}
