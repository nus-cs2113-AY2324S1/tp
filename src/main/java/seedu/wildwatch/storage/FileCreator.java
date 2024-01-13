//@@woodenclock
package seedu.wildwatch.storage;

import java.io.File;
import java.io.IOException;

import seedu.wildwatch.ui.FilePrinter;

public class FileCreator {
    /**
     * Creates new file with filename specified by {@code FILE_PATH}.
     */
    public static File createFile(String filename) throws IOException {
        File file = new File(filename);
        if (file.createNewFile()) {
            FilePrinter.createNewFileMessagePrinter(filename);
            return file;
        } else {
            throw new IOException();
        }
    }
}
