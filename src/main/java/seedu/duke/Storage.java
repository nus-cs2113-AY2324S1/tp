package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * storage for flashcards
 * Flashcard implementation should be finished first
 */
public class Storage {
    // simply implemented for save & load first

    protected String path;

    public Storage(String path){
        this.path = path;
    }


    public void loadFlashcards() throws FileNotFoundException{

        File f = new File (this.path);
        Scanner s = new Scanner(f);

    }
}
