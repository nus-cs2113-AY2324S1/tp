//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

/**
 * This class allows creating commands that can be invoked in two ways: the
 * beginner mode or the expert mode.
 *
 * The beginner mode interactively prompts the user to enter his data; i.e.
 * first the user enters the command name, and then the command prompts him
 * to enter the additional pieces of information required (i.e. which review
 * mode to choose). Thus, the user is guided through the choices he has to
 * make.
 *
 * The expert mode allows the user to enter all information at once in a
 * single line. This speeds the process up, but provides the user with less
 * guidance and is thus harder.
 *
 * In order to accommodate for both modes and thus allow users to decide
 * whether they want to take the slower, but easier approach or rather the
 * faster, but harder approach, DualFlashcardCommand allows implementing
 * both modes so that users can simply choose.
 *
 * The developer has to implement the executeBeginnerMode and the
 * executeExpertMode methods to implement beginner mode and expert mode,
 * respectively.
 */
public abstract class DualFlashcardCommand extends FlashcardCommand {
    protected int beginnerCommandLength;
    protected int expertCommandLength;
    protected String input;
    protected String syntaxString;

    /**
     * Allows implementing the beginner mode (with interactive input).
     *
     * @param scanner To get user input.
     * @param flashcardList The flashcards to operate on.
     */
    protected abstract void executeBeginnerMode(Scanner scanner,
                                                FlashcardList flashcardList);

    /**
     * Allows implementing the expert mode (with one-line input).
     *
     * @param scanner To get user input.
     * @param flashcardList The flashcards to operate on.
     */
    protected abstract void executeExpertMode(Scanner scanner,
                                         FlashcardList flashcardList);

    /**
     * Decides whether to use beginner or expert mode and executes it.
     *
     * This decision is based on the length of the inputted command: it is
     * compared to the length of the input for both beginner and expert mode
     * and then the appropriate method is executed.
     *
     * @param scanner Scanner that allows handling user input.
     * @param flashcardList Which flashcards to perform actions on.
     */
    public void execute(Scanner scanner, FlashcardList flashcardList) {
        String[] commandParts = input.split(" ");

        assert commandParts != null : "must contain values";

        if (commandParts.length == beginnerCommandLength) {
            executeBeginnerMode(scanner, flashcardList);
        } else if (commandParts.length == expertCommandLength) {
            executeExpertMode(scanner, flashcardList);
        } else {
            System.out.println("    Invalid syntax! The syntax is '"
                    + syntaxString + "'");
            System.out.println("    Please try again.");
        }
    }
}
