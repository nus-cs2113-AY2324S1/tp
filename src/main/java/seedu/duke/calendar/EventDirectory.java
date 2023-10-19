package seedu.duke.calendar;

import java.io.File;

/**
 * directory for directory
 * can list-up txt files in text folder
 * In version 1, using only event.txt
 * In version 2, can select or create
 */

public class EventDirectory {
    String path;
    File file;

    public EventDirectory(){
        path = "./data/events";

        file = new File(path);
        if(!file.exists()){
            if(file.mkdir()){
                System.out.println("    Created data directory");
            } else{
                System.out.println("    Failed to create directory");
            }
        } else{
            System.out.println("    Using data/events directory");
        }
    }

    /**
     * list-up saved files
     * selecting file is for version 2
     */
    public void listEventFiles(){
        String[] eventFiles = file.list();
        if(eventFiles == null){
            System.out.println("Failed to find files");
        } else if(eventFiles.length == 0){
            System.out.println("No files exist");
        } else{
            for(String eventFile : eventFiles){
                System.out.println("        "+eventFile);
            }
        }
    }

    /**
     * return default directory
     * for version 1
     * @return directory for flashcard txt file
     */
    public String defaultDirectory() {
        return this.path + "/event.txt";
    }

    /**
     * return directory of flashcard txt file
     * for version 2
     * @param path
     * @return
     */
    public String eventDirectory(String path) {
        return this.path + path;
    }

}
