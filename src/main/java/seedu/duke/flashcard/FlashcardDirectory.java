package seedu.duke.flashcard;

import java.io.File;

/**
 * directory for flashcards
 * can list-up txt files in text folder
 * In version 1, using only flashcard.txt
 * In version 2, can select or create
 */

public class FlashcardDirectory {

    String path;
    File file;

    public FlashcardDirectory(){
        path = "./data/flashcards";

        file = new File(path);
        if(!file.exists()){
            if(file.mkdir()){
                System.out.println("    Created flashcards directory");
            } else{
                System.out.println("    Failed to create directory");
            }
        } else{
            System.out.println("    Using data/flashcards directory");
        }
    }

    /**
     * list-up saved files
     * selecting file is for version 2
     */
    public void listFlashcardFiles(){
        String[] flashcardFiles = file.list();
        if(flashcardFiles == null){
            System.out.println("Failed to find files");
        } else if(flashcardFiles.length == 0){
            System.out.println("No files exist");
        } else{
            for(String flashcardFile : flashcardFiles){
                System.out.println("        "+flashcardFile);
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
