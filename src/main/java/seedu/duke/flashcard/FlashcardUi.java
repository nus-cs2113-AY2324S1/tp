package seedu.duke.flashcard;

import seedu.duke.flashcard.command.CreateFlashcardCommand;
import seedu.duke.flashcard.command.FlashcardCommand;
import seedu.duke.flashcard.command.ListFlashcardsCommand;

import java.util.Scanner;

public class FlashcardUi {
    private Scanner scanner;
    private FlashcardList flashcardList;

    public FlashcardUi(FlashcardList flashcardList) {
        scanner = new Scanner(System.in);
        this.flashcardList = flashcardList;
    }

    public void executeCommand(FlashcardCommand command) {
        if (command instanceof CreateFlashcardCommand) {
            executeCreateFlashcardCommand();
        } else if (command instanceof ListFlashcardsCommand) {
            listFlashcards();
        }
    }

    private void executeCreateFlashcardCommand() {
        System.out.print("Enter the front page text: ");
        String frontPageText = scanner.nextLine();
        System.out.print("Enter the back page text: ");
        String backPageText = scanner.nextLine();

        Flashcard flashcard = new Flashcard(frontPageText, backPageText);

        flashcardList.add(flashcard);
    }

    public void listFlashcards() {
        for (Flashcard flashcard : flashcardList.getFlashcards()) {
            System.out.println(flashcard);
        }
    }
}
