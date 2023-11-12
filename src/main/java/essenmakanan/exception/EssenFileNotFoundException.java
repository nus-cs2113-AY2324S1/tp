package essenmakanan.exception;

import essenmakanan.ui.Ui;

import java.io.File;
import java.io.IOException;

/**
 * Indicates an error that a file is not found.
 */
public class EssenFileNotFoundException extends Exception {

    /**
     * Creates a directory based on the path.
     *
     * @param newDirectory The directory.
     */
    private void createDirectory(File newDirectory) {
        if (!newDirectory.isDirectory() && newDirectory.mkdir()) {
            System.out.println("Directory successfully created");
        } else {
            System.out.println("Directory located");
        }
    }

    /**
     * Creates a text file based on the path.
     *
     * @param newDatabase The text file.
     * @throws IOException If there is an error related to creating the file.
     */
    private void createFile(File newDatabase) throws IOException {
        if (!newDatabase.isFile() && newDatabase.createNewFile()) {
            System.out.println("Data text file successfully created");
        } else {
            System.out.println("Text file located");
        }
    }

    /**
     * Handles missing text file for the application's database.
     *
     * @param dataDirectory The name of data directory.
     * @param dataPath The name of data path.
     */
    public void handleFileNotFoundException(String dataDirectory, String dataPath) {
        System.out.println("Creating database");

        File newDirectory = new File(dataDirectory);
        File newDatabase = new File(dataPath);

        try {
            createDirectory(newDirectory);
            createFile(newDatabase);
        } catch (IOException exception){
            Ui.handleIOException(exception);
        }

    }
}
