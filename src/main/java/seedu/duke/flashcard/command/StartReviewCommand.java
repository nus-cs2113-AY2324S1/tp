//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.calendar.Calendar;
import seedu.duke.flashcard.FlashcardList;
import seedu.duke.flashcard.review.RandomReviewMode;
import seedu.duke.flashcard.review.ReviewMode;
import seedu.duke.flashcard.review.SpacedRepetitionReviewMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

    private String getUserChoiceReviewMode(Scanner scanner) {
        System.out.println("    How do you want to review your flashcards?");
        System.out.println("        a) random mode");
        System.out.println("        b) spaced repetition mode");

        return scanner.nextLine();
    }

    protected void executeBeginnerMode(Scanner scanner,
                                     FlashcardList flashcardList) {
        String choice = getUserChoiceReviewMode(scanner);

        while (!choices.contains(choice.toLowerCase())) {
            System.out.println("    Invalid choice! Your choice must be a or " +
                    "b! Please try again.");

            choice = getUserChoiceReviewMode(scanner);
        }

        startReview(scanner, flashcardList, choice);
    }

    protected void executeExpertMode(Scanner scanner,
                                     FlashcardList flashcardList) {
        String[] commandParts = input.split(" ");

        try {
            String choice = commandParts[2].toLowerCase();

            if (!choices.contains(choice)) {
                System.out.println("    Invalid choice! Your choice must be a" +
                        " or b! Please try again.");
                return;
            }

            startReview(scanner, flashcardList, choice);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Invalid syntax! The syntax is 'review " +
                    "flashcards REVIEW_MODE'");
            System.out.println("    Please try again.");
        }
    }

    private void startReview(Scanner scanner,
                             FlashcardList flashcardList,
                             String choice) {
        ReviewMode reviewMode = createReviewMode(choice.toLowerCase(), flashcardList);

        reviewMode.startReviewSession(scanner, calendar);

    }

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
