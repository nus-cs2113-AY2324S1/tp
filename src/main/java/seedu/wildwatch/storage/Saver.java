//@@woodenclock
package seedu.wildwatch.storage;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.ui.ErrorPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Saver {
    private static final String FILE_PATH = "./WildWatch.txt";

    /**
     * Manages the storage of tasks in a file.
     * Write each task in the desired format to the file.
     */
    public static void save(ArrayList<Entry> entries) {
        assert entries != null : "Trying to save a null list of entries.";
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Entry entry : entries) {
                writer.write(EntryToStringConverter.toFileString(entry) + "\n");
            }
            writer.close();
        } catch (IOException exception) {
            ErrorPrinter.errorMessagePrinter(exception);
        }
    }
}
