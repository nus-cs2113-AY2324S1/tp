//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.calendar.Calendar;
import seedu.duke.flashcard.FlashcardList;
import seedu.duke.flashcard.exceptions.InvalidReviewModeException;
import seedu.duke.flashcard.review.RandomReviewMode;
import seedu.duke.flashcard.review.ReviewMode;
import seedu.duke.flashcard.review.SpacedRepetitionReviewMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This command allows starting a flashcard review by either using beginner
 * or expert mode.
 *
 * Users get to choose from random review mode and spaced repetition review
 * mode.
 */
public class StartReviewCommand extends DualFlashcardCommand {
    private final ArrayList<String> choices = new ArrayList<>(Arrays.asList(
            "a", "b"));
    private Calendar calendar;

    public StartReviewCommand(String input, Calendar calendar) {
        this.input = input;
        this.calendar = calendar;
        beginnerCommandLength = 2;
        expertCommandLength = 3;
        syntaxString = "review flashcards REVIEW_MODE";
    }

    /**
     * Returns the letter of the review mode chosen by the user.
     *
     * @param scanner To get input from the user.
     * @return The letter entered by the user to choose the review mode.
     */
    private String getUserChoiceReviewMode(Scanner scanner) {
        System.out.println("    How do you want to review your flashcards?");
        System.out.println("        a) random mode");
        System.out.println("        b) spaced repetition mode");

        return scanner.nextLine();
    }

    /**
     * Starts an interactive process to select and start a review mode.
     *
     * The user is asked for the review mode he wants to use; and then the
     * respective review mode is started.
     *
     * @param scanner To get user input.
     * @param flashcardList The flashcards to operate on.
     */
    protected void executeBeginnerMode(Scanner scanner,
                                     FlashcardList flashcardList) {
        assert scanner != null : "Must be valid Scanner instance";

        String choice = getUserChoiceReviewMode(scanner);

        while (!choices.contains(choice.toLowerCase())) {
            System.out.println("    Invalid choice! Your choice must be a or "
                    + "b! Please try again.");

            choice = getUserChoiceReviewMode(scanner);
        }

        startReview(scanner, flashcardList, choice);
    }

    /**
     * Starts a review session in the review mode inputted by the user.
     *
     * The user mode (a or b) is already passed as part of the
     * one-line-command used to start this review.
     *
     * @param scanner To get user input.
     * @param flashcardList The flashcards to operate on.
     */
    protected void executeExpertMode(Scanner scanner,
                                     FlashcardList flashcardList) {
        String[] commandParts = input.split(" ");

        assert commandParts.length > 0 : "must contain values";

        try {
            String choice = commandParts[2].toLowerCase();

            if (!choices.contains(choice)) {
                throw new InvalidReviewModeException();
            }

            startReview(scanner, flashcardList, choice);
        } catch (InvalidReviewModeException e) {
            System.out.println("    Invalid choice! Your choice must be a"
                    + " or b! Please try again.");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Invalid syntax! The syntax is 'review " +
                    "flashcards REVIEW_MODE'");
            System.out.println("    Please try again.");
        }
    }

    /**
     * Starts a flashcard review in the desired mode.
     *
     * @param scanner To get user input.
     * @param flashcardList The flashcards from whom to pick.
     * @param choice Character indicating the desired review mode.
     */
    private void startReview(Scanner scanner,
                             FlashcardList flashcardList,
                             String choice) {
        ReviewMode reviewMode = createReviewMode(choice.toLowerCase(), flashcardList);

        reviewMode.startReviewSession(scanner, calendar);

    }

    /**
     * Creates and returns an instance of the desired ReviewMode.
     *
     * @param choice Whether it should be random or spaced repetition mode.
     * @param flashcardList The flashcards on which the review should operate.
     * @return An instance of the desired review mode.
     */
    private ReviewMode createReviewMode(String choice, FlashcardList flashcardList) {
        ReviewMode reviewMode = null;

        if (choice.equals("a")) {
            reviewMode = new RandomReviewMode(flashcardList);
        } else if (choice.equals("b")) {
            reviewMode = new SpacedRepetitionReviewMode(flashcardList);
        }

        assert reviewMode != null;

        return reviewMode;
    }
}
