package seedu.duke.storage;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * directory for flashcards
 * can list-up txt files in text folder
 * In version 1, using only flashcard.txt
 * In version 2, can select or create
 */

public class FlashcardDirectory {
    private static Logger logger; // for logging
    String path;
    File file;

    public FlashcardDirectory(){

        logger = Logger.getLogger("flashDir");
        logger.setLevel(Level.WARNING);

        path = "./data/flashcards";

        String dataPath = "./data";

        File dataFolder = new File(dataPath);
        if(!dataFolder.exists()){
            if(dataFolder.mkdir()){
                logger.log(Level.INFO, "Created data directory");
                //System.out.println("    Created events directory");
            } else{
                logger.log(Level.INFO, "Failed to create data directory");
                //System.out.println("    Failed to create directory");
            }
        } else{
            logger.log(Level.INFO, "Using data directory");
            //System.out.println("    Using data/events directory");
        }

        file = new File(path);
        if(!file.exists()){
            if(file.mkdir()){
                logger.log(Level.INFO, "Created flashcards directory");
                //System.out.println("    Created flashcards directory");
            } else{
                logger.log(Level.INFO, "Failed to create directory");
                //System.out.println("    Failed to create directory");
            }
        } else{
            logger.log(Level.INFO, "Using data/flashcards directory");
            //System.out.println("    Using data/flashcards directory");
        }
    }

    /**
     * list-up saved files
     * selecting file is for version 2
     */
    public void listFlashcardFiles(){
        String[] flashcardFiles = file.list();
        if(flashcardFiles == null){
            logger.log(Level.INFO, "Failed to find files");
            //System.out.println("Failed to find files");
        } else if(flashcardFiles.length == 0){
            logger.log(Level.INFO, "No files exist");
            //System.out.println("No files exist");
        } else{
            for(String flashcardFile : flashcardFiles){
                logger.log(Level.INFO, flashcardFile);
                //System.out.println("        "+flashcardFile);
            }
        }
    }

    /**
     * return default directory
     * for version 1
     * @return directory for flashcard txt file
     */
    public String defaultDirectory() {
        return this.path + "/flashcard.txt";
    }

    /**
     * return directory of flashcard txt file
     * for version 2
     * @param path
     * @return
     */
    public String flashcardDirectory(String path) {
        return this.path + path;
    }

}
