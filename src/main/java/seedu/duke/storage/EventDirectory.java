package seedu.duke.storage;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * directory for directory
 * can list-up txt files in text folder
 * In version 1, using only event.txt
 * In version 2, can select or create
 */

public class EventDirectory {
    private static Logger logger; // for logging
    String path;
    File file;

    public EventDirectory(){
        logger = Logger.getLogger("flashDir");
        logger.setLevel(Level.WARNING);

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

        path = "./data/events";

        file = new File(path);
        if(!file.exists()){
            if(file.mkdir()){
                logger.log(Level.INFO, "Created events directory");
                //System.out.println("    Created events directory");
            } else{
                logger.log(Level.INFO, "Failed to create directory");
                //System.out.println("    Failed to create directory");
            }
        } else{
            logger.log(Level.INFO, "Using data/events directory");
            //System.out.println("    Using data/events directory");
        }
    }

    /**
     * list-up saved files
     * selecting file is for version 2
     */
    public void listEventFiles(){
        String[] eventFiles = file.list();
        if(eventFiles == null){
            logger.log(Level.INFO, "Failed to find files");
            //System.out.println("Failed to find files");
        } else if(eventFiles.length == 0){
            logger.log(Level.INFO, "No files exist");
            //System.out.println("No files exist");
        } else{
            for(String eventFile : eventFiles){
                logger.log(Level.INFO, eventFile);
                //System.out.println("        "+eventFile);
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
     * @param path is used for storing file path
     * @return is used to get the path to the file
     */
    public String eventDirectory(String path) {
        return this.path + path;
    }

}
