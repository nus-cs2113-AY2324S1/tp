//@@author wendelinwemhoener

package seedu.duke.flashcard.review;

import seedu.duke.calendar.Calendar;
import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Base class for implementing different kinds of flashcard reviews.
 *
 * Provides functionalities to easily review flashcards that can be used by
 * subclasses as building blocks to implement specific kinds (e.g. random or
 * difficulty-based) reviews.
 */
public abstract class ReviewMode {
    protected FlashcardList flashcardList;

    public ReviewMode(FlashcardList flashcardList) {
        assert flashcardList != null : "flashcardList cannot be null";
        this.flashcardList = flashcardList;
    }

    /**
     * Abstract method to allow subclasses to identify themselves easily.
     *
     * @return Textual description of what kind of review mode this class is.
     */
    public abstract String getReviewModeName();


    /**
     * Starts and executes a flashcard review session.
     *
     * @param scanner For getting input from user.
     */
    public void startReviewSession(Scanner scanner, Calendar calendar) {
        assert scanner != null : "must be a valid Scanner instance";

        System.out.println("    You have started a review session in "
                + getReviewModeName() + System.lineSeparator());

        if (flashcardList.isEmpty()) {
            System.out.println("    You have no flashcards to review!");
            System.out.println("    Add some flashcards and try again.");
            return;
        }

        while (true) {
            Flashcard flashcardToReview = pickFlashcard();

            printFlashcardFrontTextPrompt(flashcardToReview);

            String input = scanner.nextLine();
            input = input.toLowerCase();
            boolean shouldTerminate = input.strip().equals("quit")
                    || input.strip().equals("q");
            if (shouldTerminate) {
                break;
            }

            System.out.println("    The actual back text is: "
                    + flashcardToReview.getBackText());
            System.out.println();

            if (getReviewModeName().equals("spaced repetition mode")) {
                letUserRateReviewDifficulty(scanner, flashcardToReview);
            }

            calendar.incrementFlashcardCount();
        }

        System.out.println("    Success! You have ended this review session.");
    }

    /**
     * Allows subclasses to choose which flashcard to review next.
     *
     * @return The next flashcard to review.
     */
    protected abstract Flashcard pickFlashcard();

    /**
     * Prints front of a flashcard and prompts the user to think of its back.
     *
     * @param flashcardToReview The flashcard currently being reviewed.
     */
    protected void printFlashcardFrontTextPrompt(Flashcard flashcardToReview) {
        assert flashcardToReview != null : "must be valid Flashcard instance";

        System.out.println("    " + "-".repeat(76));
        System.out.println("    The front text is: "
                + flashcardToReview.getFrontText());
        System.out.println();

        System.out.println("    Think of the answer (the back text) in " +
                "your head.");
        System.out.println("    Press ENTER when you are ready to compare " +
                "it,");
        System.out.println("    or enter q or quit to end this review " +
                "session.");
    }

    /**
     * Lets the user rate the difficulty of a flashcard and then adjusts it.
     *
     * Depending on the rating given by the user, the flashcard difficulty is
     * increased, kept constant or decreased. If the user rates the flashcard
     * as easy, difficulty is decreased by one; if the user rates it as
     * medium hard, difficulty is kept unchanges; and if the user rates it as
     * hard, difficulty is increased by one.
     *
     * @param scanner To get user input.
     * @param flashcard The flashcard currently under review.
     */
    protected void letUserRateReviewDifficulty(Scanner scanner,
                                               Flashcard flashcard) {
        assert flashcard != null : "must be a valid Flashcard instance";
        assert scanner != null : "must be a valid Scanner instance";

        System.out.println("    How hard was it to remember the back page of "
                + "this flashcard?");
        System.out.println("    Input E if it was easy, M if it was "
                + "moderately challenging ");
        System.out.println("    or H if it was quite hard.");

        final ArrayList<String> choices = new ArrayList<>(Arrays.asList(
                "e", "m", "h"));
        String choice = scanner.nextLine();

        while (!choices.contains(choice.toLowerCase())) {
            System.out.println("    Invalid choice! Your choice must be E, M "
                    + "or H! Please try again.");

            choice = scanner.nextLine();
        }

        int difficultyChange;
        switch (choice.toLowerCase()) {
        case "e":
            difficultyChange = -1;
            break;
        case "m":
            difficultyChange = 0;
            break;
        default:
            difficultyChange = +1;
            break;
        }

        flashcard.applyDifficultyChange(difficultyChange);
    }
}
