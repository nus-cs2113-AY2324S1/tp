package seedu.duke.flashcard;

import seedu.duke.flashcard.command.FlashcardCommand;
import seedu.duke.flashcard.command.UnknownCommand;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FlashcardComponent {
    private FlashcardCommandParser parser;
    private FlashcardList flashcardList;
    private FlashcardUi ui;

    private FlashcardStorage storage;


    public FlashcardComponent(ArrayList<Flashcard> flashcards) {
        parser = new FlashcardCommandParser();
        storage = new FlashcardStorage("./flashcard.txt");
        try {
            flashcardList = storage.loadFlashcards();
            System.out.println("Loading existed File");
        } catch (FileNotFoundException e){
            System.out.println("Making New file");
            flashcardList = new FlashcardList(flashcards);
        }


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

        // save after every commands
        storage.saveFlashcards(flashcardList.getFlashcards());
    }
}
