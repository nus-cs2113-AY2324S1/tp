package seedu.duke.flashcard;

import seedu.duke.flashcard.command.FlashcardCommand;
import seedu.duke.flashcard.command.UnknownCommand;

import java.util.ArrayList;

public class FlashcardComponent {
    private FlashcardCommandParser parser;
    private FlashcardList flashcardList;
    private FlashcardUi ui;

    public FlashcardComponent(ArrayList<Flashcard> flashcards) {
        parser = new FlashcardCommandParser();
        flashcardList = new FlashcardList(flashcards);
        ui = new FlashcardUi(flashcardList);
    }

    public boolean isResponsible(String input) {
        FlashcardCommand command = parser.parseInput(input);

        if (command instanceof UnknownCommand) {
            return false;
        } else {
            return true;
        }
    }

    public void processInput(String input) {
        FlashcardCommand command = parser.parseInput(input);
        ui.executeCommand(command);
    }
}
