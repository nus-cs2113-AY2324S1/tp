package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public class UnknownCommand extends FlashcardCommand {
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        System.out.println("Unknown command! Please try again.");
    }
}
