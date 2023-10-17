package seedu.duke.flashcard;

import java.io.File;

/**
 * directory for flashcards
 * can list-up txt files in text folder
 */

public class FlashcardDirectory {

    String path;
    File file;

    public FlashcardDirectory(){
        path = "./data";

        file = new File(path);
        if(!file.exists()){
            if(file.mkdir()){
                System.out.println("Created data directory");
            } else{
                System.out.println("Failed to create directory");
            }
        } else{
            System.out.println("Using data directory");
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
        }
        else{
            for(String flashcardFile : flashcardFiles){
                System.out.println(flashcardFile);
            }
        }
    }

}
