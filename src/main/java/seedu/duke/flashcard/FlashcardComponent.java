//@@author wendelinwemhoener

package seedu.duke.flashcard;

import seedu.duke.calendar.Calendar;
import seedu.duke.flashcard.command.FlashcardCommand;
import seedu.duke.flashcard.command.UnknownCommand;
import seedu.duke.storage.FlashcardDirectory;
import seedu.duke.storage.FlashcardStorage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Encapsulates all classes needed for the flashcard functionality and
 * allows access to them via one unified API.
 */
public class FlashcardComponent {
    private FlashcardCommandParser parser;
    private FlashcardList flashcardList;
    private FlashcardUi ui;
    private FlashcardStorage storage;
    private Calendar calendar;

    //@@author junhyeong0411
    public FlashcardComponent(Calendar calendar) {
        parser = new FlashcardCommandParser();

        FlashcardDirectory flashcarddirectory = new FlashcardDirectory();
        flashcarddirectory.listFlashcardFiles();

        storage = new FlashcardStorage(flashcarddirectory.defaultDirectory());
        try {
            flashcardList = storage.loadFlashcards();
        } catch (FileNotFoundException e){
            //System.out.println("Making New file for Flashcards");
            flashcardList = new FlashcardList(new ArrayList<>());
        }

        Flashcard.calculateAndUpdateGlobalMaxId(flashcardList);
        ui = new FlashcardUi(flashcardList);
        this.calendar = calendar;
    }

    public FlashcardStorage getStorage(){
        return this.storage;
    }

    public FlashcardList getFlashcardList(){
        return this.flashcardList;
    }

    /**
     * get FlashcardUi method
     * for test
     * @return
     */
    public FlashcardUi getUi(){
        return ui;
    }

    //@@author wendelinwemhoener
    /**
     * Returns if FlashcardComponent is responsible for handling this input.
     *
     * @param input The text inputted by the user.
     * @return Whether FlashcardComponent is responsible for handling the input.
     */
    public boolean isResponsible(String input) {
        assert input != null : "input must not be null";

        FlashcardCommand command = parser.parseInput(input, calendar);


        if (command instanceof UnknownCommand) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Processes user input by parsing it and executing the resulting command.
     *
     * @param input The text inputted by the user.
     */
    public void processInput(String input) {
        assert input != null : "input must not be null";

        FlashcardCommand command = parser.parseInput(input, calendar);
        assert !(command instanceof UnknownCommand) : "Command cannot be " +
                "unknown";

        ui.executeCommand(command);

        //@@author junhyeong0411
        // save after every commands
        storage.saveFlashcards(flashcardList.getFlashcards());
    }
}
