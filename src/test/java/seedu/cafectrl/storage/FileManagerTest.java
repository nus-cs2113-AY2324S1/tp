package seedu.cafectrl.storage;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Junit test for FileManager.java
 */
public class FileManagerTest {
    @Test
    public void readTextFile_emptyFilePath_fileNotFoundExceptionThrown() {
        String inputFilePath = "";

        FileManager fileManager = new FileManager(new Ui());
        assertThrows(FileNotFoundException.class, () -> fileManager.readTextFile(inputFilePath));
    }

    @Test
    public void readTextFile_nullFilePath_nullPointerExceptionThrown() {

        FileManager fileManager = new FileManager(new Ui());
        assertThrows(NullPointerException.class, () -> fileManager.readTextFile(null));
    }

    @Test
    public void checkFileExists_emptyFilePath_fileNotFoundExceptionThrown() {
        String inputFilePath = "";

        FileManager fileManager = new FileManager(new Ui());
        assertThrows(Exception.class, () -> fileManager.checkFileExists(inputFilePath));
    }

    @Test
    public void checkFileExists_nullFilePath_nullPointerExceptionThrown() {
        FileManager fileManager = new FileManager(new Ui());
        assertThrows(NullPointerException.class, () -> fileManager.checkFileExists(null));
    }

    @Test
    public void overwriteFile_emptyFilePath_emptyFileInputMessage() {
        ArrayList<String> actualOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };
        FileManager fileManager = new FileManager(ui);

        String inputFilePath = "";
        ArrayList<String> inputTextList = new ArrayList<>();

        fileManager.overwriteFile(inputFilePath, inputTextList);
        assertEquals(ErrorMessages.MISSING_FILEPATH, actualOutput.get(0));
    }
}
