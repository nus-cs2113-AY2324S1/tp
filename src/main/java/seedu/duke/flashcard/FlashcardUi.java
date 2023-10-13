package seedu.duke.flashcard;

import seedu.duke.flashcard.command.FlashcardCommand;

import java.util.Scanner;

public class FlashcardUi {
    private Scanner scanner;
    private FlashcardList flashcardList;

    public FlashcardUi(FlashcardList flashcardList) {
        scanner = new Scanner(System.in);
        this.flashcardList = flashcardList;
    }

    public void executeCommand(FlashcardCommand command) {
        command.execute(scanner, flashcardList);
    }
}
