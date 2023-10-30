package seedu.cafectrl.storage;

import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manage everything related to file such as writing, reading, opening and creating file
 */
public class FileManager {
    private final Ui ui;

    public FileManager(Ui ui) {
        this.ui = ui;
    }

    //@@author DextheChik3n
    /**
     * Reads the text file from the specified file path and stores each line in an ArrayList.
     *
     * @return ArrayList that consists of every text line in each element
     */
    public ArrayList<String> readTextFile(String filePath) {
        openTextFile(filePath);
        String userWorkingDirectory = System.getProperty("user.dir");
        java.nio.file.Path tasksFilePath = java.nio.file.Paths.get(userWorkingDirectory, filePath);
        File textFile = new File(String.valueOf(tasksFilePath));

        ArrayList<String> textLines = new ArrayList<>();
        // todo Dexter: implement proper error handling here
        try {
            Scanner s = new Scanner(textFile);

            while (s.hasNext()){
                textLines.add(s.nextLine());
            }

            s.close();
        } catch (FileNotFoundException e) {
            ui.showToUser(ErrorMessages.DATA_FILE_NOT_FOUND_MESSAGE);
        }
        return textLines;
    }

    //@@author DextheChik3n
    /**
     * Handles opening and creating (if needed) the text file and folder
     * @param filePath the file path that is passed in main
     * @return the file path of where the data is stored
     * @throws IOException if an I/O error occurred while creating the text file
     */
    public String openTextFile(String filePath) {
        String userWorkingDirectory = System.getProperty("user.dir");
        java.nio.file.Path dataFilePath = java.nio.file.Paths.get(userWorkingDirectory, filePath);
        java.nio.file.Path dataFolderPath = dataFilePath.getParent();
        File textFile = new File(String.valueOf(dataFilePath));
        File folder = new File(String.valueOf(dataFolderPath));

        if (!Files.exists(dataFolderPath)) {
            folder.mkdir();
            ui.showToUser(ErrorMessages.DATA_FOLDER_NOT_FOUND_MESSAGE);
        }

        if (!Files.exists(dataFilePath)) {
            try {
                textFile.createNewFile();
            } catch (Exception e) {
                ui.showToUser(ErrorMessages.DATA_FILE_NOT_FOUND_MESSAGE);
            }
        }

        return dataFilePath.toString();
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
        String openFilePath = openTextFile(filePath);
        FileWriter fw = new FileWriter(openFilePath);
        for (String line : listOfTextToAdd) {
            fw.write(line);
        }
        fw.close();
    }

    //@@author DextheChik3n
    /**
     * Writes text to the text file at the specified file path.
     * Will overwrite all text in text file.
     *
     * @param filePath file path of the text file.
     * @param textToAdd text to be written to the text file.
     * @throws IOException If I/O operations are interrupted.
     */
    public void overwriteFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    //@@author DextheChik3n
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
