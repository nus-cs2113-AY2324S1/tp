//@@woodenclock
package seedu.wildwatch.storage;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.parser.FileStringParser;


public class FileLoader {
    /**
     * Loads tasks from file into the TaskList.
     */
    public static void loadFile(File openedFile) throws FileNotFoundException, InvalidInputException {
        Scanner fileReader = new Scanner(openedFile); // create a Scanner using the File as the source
        while (fileReader.hasNext()) {
            String lineOfFile = fileReader.nextLine();

            new FileStringParser().parse(lineOfFile).execute(); // Creates new entry from line in file
        }
    }
}
