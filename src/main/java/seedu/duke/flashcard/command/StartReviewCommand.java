//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;
import seedu.duke.flashcard.review.RandomReviewMode;
import seedu.duke.flashcard.review.ReviewByTagMode;
import seedu.duke.flashcard.review.ReviewMode;
import seedu.duke.flashcard.review.SpacedRepetitionReviewMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StartReviewCommand extends FlashcardCommand {
    private final ArrayList<String> choices = new ArrayList<>(Arrays.asList(
            "a", "b", "c"));

    private String getUserChoiceReviewMode(Scanner scanner) {
        System.out.println("    How do you want to review your flashcards?");
        System.out.println("        a) random mode");
        System.out.println("        b) spaced repetition mode");
        System.out.println("        c) review by tag mode");

        return scanner.nextLine();
    }

    public void execute(Scanner scanner, FlashcardList flashcardList) {
        String choice = getUserChoiceReviewMode(scanner);

        while (!choices.contains(choice.toLowerCase())) {
            System.out.println("    Invalid choice! Your choice must be a, b " +
                    "or c! Please try again.");

            choice = getUserChoiceReviewMode(scanner);
        }

        ReviewMode reviewMode = createReviewMode(choice.toLowerCase(), flashcardList);

        if (reviewMode instanceof RandomReviewMode) {
            reviewMode.startReviewSession(scanner);
        } else {
            System.out.println("    This review mode hasn't yet been " +
                    "implemented. Sorry!");
        }
    }

    private ReviewMode createReviewMode(String choice, FlashcardList flashcardList) {
        ReviewMode reviewMode = null;

        if (choice.equals("a")) {
            reviewMode = new RandomReviewMode(flashcardList);
        } else if (choice.equals("b")) {
            reviewMode = new SpacedRepetitionReviewMode(flashcardList);
        } else if (choice.equals("c")) {
            reviewMode = new ReviewByTagMode(flashcardList);
        }

        assert reviewMode != null;

        return reviewMode;
    }
}
