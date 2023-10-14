package seedu.stocker.storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an object to handle writing to txt files and appending to them.
 */
public class Storage {
    /**
     * Writes to the first line of a txt file
     * can be used to clear a txt file as well.
     *
     * @param filePath relative path of file to write to
     * @param textToAdd what to write to the file
     * @throws IOException if invalid input is entered
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends given text to next line of txt file.
     *
     * @param filePath relative path of file to write to
     * @param textToAppend what to write to the file
     * @throws IOException if invalid input is given
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        BufferedWriter bf = new BufferedWriter(new FileWriter(filePath));
        bf.write(textToAppend);
        bf.newLine();
        bf.close();
    }


}
