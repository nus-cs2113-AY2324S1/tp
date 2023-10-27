package seedu.cafectrl.storage;

import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading from and storing data to the text file.
 */
public abstract class Storage {
    String filePath;
    Ui ui;

    public Storage (String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui  = new Ui();
    }

    /**
     * Reads the text file from the specified file path and stores each line in an ArrayList.
     *
     * @return ArrayList that consists of every text line in each element
     * @throws FileNotFoundException If file is not found at the specified file path.
     */
    public ArrayList<String> readTextFile(String filePath) throws FileNotFoundException {
        String userWorkingDirectory = System.getProperty("user.dir");
        java.nio.file.Path tasksFilePath = java.nio.file.Paths.get(userWorkingDirectory, filePath);
        File textFile = new File(String.valueOf(tasksFilePath));

        if (textFile.length() == 0) {
            throw new FileNotFoundException();
        }

        Scanner s = new Scanner(textFile);
        ArrayList<String> textLines = new ArrayList<>();

        while (s.hasNext()){
            textLines.add(s.nextLine());
        }

        s.close();

        return textLines;
    }

    /**
     * Initializes reading data from text file process
     * @return ArrayList of respective type based on the storage functionality
     * @throws FileNotFoundException if the file is not found in the specified file path in main
     */
    public abstract ArrayList loadData() throws FileNotFoundException;

    /**
     * Handles the parsing of text lines into intended objects to be added to the list
     * @param textLines every line in the text file
     * @return ArrayList of respective type based on the storage functionality
     */
    public abstract ArrayList extractData(ArrayList<String> textLines);

    /**
     * Handles opening and creating (if needed) the text file and folder
     * @param inputFilePath the file path that is passed in main
     * @return the file path of where the data is stored
     * @throws IOException if an I/O error occurred while creating the text file
     */
    public String openTextFile(String inputFilePath) throws IOException {
        String userWorkingDirectory = System.getProperty("user.dir");
        java.nio.file.Path dataFilePath = java.nio.file.Paths.get(userWorkingDirectory, inputFilePath);
        java.nio.file.Path dataFolderPath = dataFilePath.getParent();
        File textFile = new File(String.valueOf(dataFilePath));
        File folder = new File(String.valueOf(dataFolderPath));

        if (!Files.exists(dataFolderPath)) {
            folder.mkdir();
            ui.showToUser(Messages.DATA_FOLDER_NOT_FOUND_MESSAGE, System.lineSeparator());
        }

        if (!Files.exists(dataFilePath)) {
            textFile.createNewFile();
            ui.showToUser(Messages.DATA_FILE_NOT_FOUND_MESSAGE, System.lineSeparator());
        }

        return dataFilePath.toString();
    }

    /**
     * Handles the process of parsing the items to strings and writing to the text file
     * @throws IOException If I/O operations are interrupted.
     */
    public abstract void storeData() throws IOException;

    /**
     * Writes text to the text file at the specified file path.
     * Will overwrite all text in text file.
     *
     * @param filePath file path of the text file.
     * @param textToAdd text to be written to the text file.
     * @throws IOException If I/O operations are interrupted.
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends text to the text file at the specified file path.
     * Will add text to text file.
     *
     * @param filePath file path of the text file.
     * @param textToAdd text to be added to the text file.
     * @throws IOException If I/O operations are interrupted.
     */
    public void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }
}
