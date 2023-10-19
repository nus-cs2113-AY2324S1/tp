//@@author wendelinwemhoener

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


    public FlashcardComponent() {
        parser = new FlashcardCommandParser();

        //@@author junhyeong0411
        FlashcardDirectory flashcarddirectory = new FlashcardDirectory();
        flashcarddirectory.listFlashcardFiles();

        storage = new FlashcardStorage("./data/flashcard.txt");
        try {
            flashcardList = storage.loadFlashcards();
        } catch (FileNotFoundException e){
            System.out.println("Making New file");
            flashcardList = new FlashcardList(new ArrayList<>());
        }

        //@@author wendelinwemhoener
        ui = new FlashcardUi(flashcardList);
    }

    //@@author junhyeong0411
    public FlashcardStorage getStorage(){
        return this.storage;
    }

    //@@author wendelinwemhoener
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
        assert !(command instanceof UnknownCommand) : "Command cannot be " +
                "unknown";

        ui.executeCommand(command);

        //@@author junhyeong0411
        // save after every commands
        storage.saveFlashcards(flashcardList.getFlashcards());

        //@@author wendelinwemhoener
    }
}
