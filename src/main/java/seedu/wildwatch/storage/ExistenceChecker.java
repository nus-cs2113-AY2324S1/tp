//@@woodenclock
package seedu.wildwatch.storage;

import java.io.File;

import seedu.wildwatch.ui.FilePrinter;

public class ExistenceChecker {
    /**
     * Checks if file exists. Opens file if the file exists.
     *
     * @return true if file exists; false if not.
     */
    public static boolean checkFileExistence(String filename) {
        assert filename != null : "File path should not be null.";

        FilePrinter.checkingIfFileExistsMessagePrinter(filename);
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        } else {
            return true;
        }
    }
}
