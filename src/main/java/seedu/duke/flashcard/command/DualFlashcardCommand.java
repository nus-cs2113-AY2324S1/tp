//@@author wendelinwemhoener

package seedu.duke.flashcard.command;

import seedu.duke.flashcard.FlashcardList;

import java.util.Scanner;

public abstract class DualFlashcardCommand extends FlashcardCommand {
    protected int beginnerCommandLength;
    protected int expertCommandLength;
    protected String input;
    protected String syntaxString;

    protected abstract void executeBeginnerMode(Scanner scanner, FlashcardList flashcardList);

    protected abstract void executeExpertMode(Scanner scanner,
                                         FlashcardList flashcardList);

    public void execute(Scanner scanner, FlashcardList flashcardList) {
        String[] commandParts = input.split(" ");
        if (commandParts.length == beginnerCommandLength) {
            executeBeginnerMode(scanner, flashcardList);
        } else if (commandParts.length == expertCommandLength) {
            executeExpertMode(scanner, flashcardList);
        } else {
            System.out.println("    Invalid syntax! The syntax is '" + syntaxString + "'");
            System.out.println("    Please try again.");
        }
    }
}
