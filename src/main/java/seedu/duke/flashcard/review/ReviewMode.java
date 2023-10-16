//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class ReviewMode {
    protected FlashcardList flashcardList;

    public ReviewMode(FlashcardList flashcardList) {
        this.flashcardList = flashcardList;
    }

    public abstract String getReviewModeName();

    public void startReviewSession(Scanner scanner) {
        System.out.println("    [Review session has been started]");
        System.out.println("    [Review mode is: " + getReviewModeName() + "]");
        System.out.println();

        while (true) {
            Flashcard flashcardToReview = pickFlashcard();

            printFlashcardFrontTextPrompt(flashcardToReview);

            String input = scanner.nextLine();
            boolean shouldTerminate = input.equals("quit") || input.equals("q");
            if (shouldTerminate) {
                break;
            }

            System.out.println("    The actual back text is: " + flashcardToReview.getBackText());
            System.out.println();

            flashcardToReview.setLastReviewOn(LocalDateTime.now());
        }

        System.out.println("    Success! You have ended this review session.");
    }

    protected abstract Flashcard pickFlashcard();

    protected void printFlashcardFrontTextPrompt(Flashcard flashcardToReview) {
        System.out.println("    " + "-".repeat(76));
        System.out.println("    The front text is: " + flashcardToReview.getFrontText());
        System.out.println();

        System.out.println("    [Think of the answer (the back text) in " +
                "your head]");
        System.out.println("    [Press <ENTER> when you are ready to " +
                "compare it,]");
        System.out.println("    [or enter 'q' or 'quit' to end this " +
                "review session]");
    }
}
