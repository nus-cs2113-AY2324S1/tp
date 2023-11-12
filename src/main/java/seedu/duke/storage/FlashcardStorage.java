package seedu.duke.storage;

import seedu.duke.flashcard.Flashcard;
import seedu.duke.flashcard.FlashcardList;
import seedu.duke.storage.exceptions.FlashcardFileFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.storage.FlashcardStorageParser.flashcardFileChecker;

/**
 * storage for flashcards
 * One storage manages one file
 */
public class FlashcardStorage {
    // simply implemented for save & load first

    private static Logger flashlogger; // for logging
    protected String path;

    FlashcardStorageParser parser;

    public FlashcardStorage(String path){
        this.path = path;
        flashlogger = Logger.getLogger("flash");
        flashlogger.setLevel(Level.WARNING);
    }


    public boolean isStorageAvailable(){
        File f = new File(this.path);
        return f.exists();
    }

    /**
     * load list of flashcards
     * from this.path
     * @return list of Flashcards
     * @throws FileNotFoundException
     */
    public FlashcardList loadFlashcards() throws FileNotFoundException{

        flashlogger.log(Level.INFO, "loading flashcard");

        FlashcardList flashcardList = new FlashcardList(new ArrayList<>());
        File f = new File (this.path);
        Scanner s = new Scanner(f);

        try{
            while(s.hasNext()){
                String[] flashTokens = s.nextLine().split(" \\| ");
                flashcardFileChecker(flashTokens);
                flashcardList.add(FlashcardStorageParser.loadFlashcard(flashTokens));
                flashlogger.log(Level.INFO, "added flashcard");

            }
        } catch (FlashcardFileFormatException e) {
            System.out.println("The flashcard save file is corrupted");
            System.out.println("Automatically making new file");
            flashcardList = new FlashcardList(new ArrayList<>());
            saveFlashcards(flashcardList.getFlashcards());
        }

        flashlogger.log(Level.INFO, String.format(
                "There are currently %d flashcards in the savefile",
                flashcardList.getSize()));

        return flashcardList;

    }

    /**
     * saveFlashcards method
     * save all flashcard data to file
     * @param flashcardList
     */
    public boolean saveFlashcards(ArrayList<Flashcard> flashcardList) {

        try {
            FileWriter fw = new FileWriter(path);

            for (int i = 0; i < flashcardList.size(); i++) {

                // get info from a flashcard
                Flashcard flashcard = flashcardList.get(i);

                int id = flashcard.getId();
                String frontText = flashcard.getFrontText();
                String backText = flashcard.getBackText();
                int difficulty = flashcard.getDifficulty();

                fw.write(String.format("%d | %s | %s | %d \r\n",
                        id, frontText, backText, difficulty));
            }
            fw.close();
            return true;
        } catch (IOException e){
            flashlogger.log(Level.WARNING, "problem: failed to save");
            return false;
        }
    }





}
