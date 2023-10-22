//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class ReviewMode {
    protected FlashcardList flashcardList;

    public ReviewMode(FlashcardList flashcardList) {
        this.flashcardList = flashcardList;
    }

    public abstract String getReviewModeName();

    public void startReviewSession(Scanner scanner) {
        System.out.println("    You have started a review session in "
                + getReviewModeName());
        System.out.println();

        while (true) {
            Flashcard flashcardToReview = pickFlashcard();

            printFlashcardFrontTextPrompt(flashcardToReview);

            String input = scanner.nextLine();
            boolean shouldTerminate = input.startsWith("quit") || input.equals("q");
            if (shouldTerminate) {
                break;
            }

            System.out.println("    The actual back text is: " + flashcardToReview.getBackText());
            System.out.println();

            letUserRateReviewDifficulty(scanner, flashcardToReview);

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
                "compare it, or enter 'q'/'quit' to end this " +
                "review session]");
    }

    protected void letUserRateReviewDifficulty(Scanner scanner,
                                               Flashcard flashcard) {
        System.out.println("    How hard was it to remeber the back page of " +
                "this flashcard?");
        System.out.println("    Press <E> if it was easy, <M> if it was " +
                "moderately challenging or <H> if it was quite hard.");

        final ArrayList<String> choices = new ArrayList<>(Arrays.asList(
                "e", "m", "h"));
        String choice = scanner.nextLine();

        while (!choices.contains(choice)) {
            System.out.println("    Invalid choice! Your choice must be e, m " +
                    "or h! Please try again.");

            choice = scanner.nextLine();
        }

        ReviewDifficulty reviewDifficulty;
        switch (choice.toLowerCase()) {
        case "e":
            reviewDifficulty = ReviewDifficulty.EASY;
            break;
        case "m":
            reviewDifficulty = ReviewDifficulty.MODERATE;
            break;
        default:
            reviewDifficulty = ReviewDifficulty.HARD;
            break;
        }

        FlashcardReview flashcardReview =
                new FlashcardReview(LocalDateTime.now(), reviewDifficulty);
        flashcard.addReview(flashcardReview);
    }
}
