package essenmakanan.exception;

import essenmakanan.ui.Ui;

import java.io.File;
import java.io.IOException;

public class EssenFileNotFoundException extends Exception {

    private static void createDirectory(File newDirectory) {
        if (!newDirectory.isDirectory() && newDirectory.mkdir()) {
            System.out.println("Directory successfully created");
        } else {
            System.out.println("Directory located");
        }
    }

    private static void createFile(File newDatabase) throws IOException {
        if (!newDatabase.isFile() && newDatabase.createNewFile()) {
            System.out.println("Data text file successfully created");
        } else {
            System.out.println("Text file located");
        }
    }

    public static void handleFileNotFoundException(String dataDirectory, String dataPath) {
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
