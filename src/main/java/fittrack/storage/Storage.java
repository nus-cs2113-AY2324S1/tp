package fittrack.storage;

import java.io.File;
import java.io.IOException;

//TODO pass the profile data to be stored in fittrack.txt
public class Storage {
    private final String filePath = "./data/fittrack.txt";
    private final File file;

    /**
     * Constructs storage.
     */
    public Storage() {
        this.file = new File(filePath);
        assert file != null;
        try {
            if (!this.file.exists()) { // If file does not exist, folder does not exist
                file.getParentFile().mkdir(); // Creates data folder
                file.createNewFile(); // throws IOException, create a file in abstract dir
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Saves user profile data into storage
     */
    public void save() {
        //TODO write data to file
    }
}
