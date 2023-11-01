package seedu.cafectrl.storage;

import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
//@@author DextheChik3n
/**
 * Manage everything related to file such as writing, reading, opening and creating file
 */
public class FileManager {
    private final Ui ui;

    public FileManager(Ui ui) {
        this.ui = ui;
    }

    /**
     * Reads the text file from the specified file path and stores each line in an ArrayList.
     *
     * @return ArrayList that consists of every text line in each element
     * @throws FileNotFoundException if text file at the specified file path does not exist
     */
    public ArrayList<String> readTextFile(String filePath) throws FileNotFoundException {
        String userWorkingDirectory = System.getProperty("user.dir");
        Path dataFilePath = Paths.get(userWorkingDirectory, filePath);
        File textFile = new File(String.valueOf(dataFilePath));
        ArrayList<String> textLines = new ArrayList<>();

        Scanner s = new Scanner(textFile);

        while (s.hasNext()) {
            textLines.add(s.nextLine());
        }

        s.close();

        return textLines;
    }

    /**
     * Checks if the text file and folder exists in the user's system and creates them (if needed)
     * @return true if and only if file exists, false otherwise
     * @param filePath the specified path location of the file
     */
    public void checkFileExists(String filePath) throws IOException {
        String userWorkingDirectory = System.getProperty("user.dir");
        Path dataFilePath = Paths.get(userWorkingDirectory, filePath);
        Path dataFolderPath = dataFilePath.getParent();
        File textFile = new File(String.valueOf(dataFilePath));
        File folder = new File(String.valueOf(dataFolderPath));

        //Check if data folder exists
        if (!Files.exists(dataFolderPath)) {
            folder.mkdir();
            ui.showToUser(ErrorMessages.DATA_FOLDER_NOT_FOUND_MESSAGE, System.lineSeparator());
        }

        //Check if the file at the specified file path exists
        if (!Files.exists(dataFilePath)) {
            textFile.createNewFile();
        }
    }

    /**
     * Writes a list of texts to the text file at the specified file path.
     * Will overwrite all text in text file.
     *
     * @param filePath file path of the text file.
     * @param listOfTextToAdd text to be written to the text file.
     * @throws IOException If I/O operations are interrupted.
     */
    public void overwriteFile(String filePath, ArrayList<String> listOfTextToAdd) throws IOException {
        checkFileExists(filePath);
        FileWriter fw = new FileWriter(filePath);
        for (String line : listOfTextToAdd) {
            fw.write(line);
        }
        fw.close();
    }

    /**
     * Writes text to the text file at the specified file path.
     * Will overwrite all text in text file.
     *
     * @param filePath file path of the text file.
     * @param textToAdd text to be written to the text file.
     * @throws IOException If I/O operations are interrupted.
     */
    public void overwriteFile(String filePath, String textToAdd) throws IOException {
        checkFileExists(filePath);
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
        checkFileExists(filePath);
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }
}
