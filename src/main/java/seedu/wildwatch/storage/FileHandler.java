//@@woodenclock
package seedu.wildwatch.storage;

import seedu.wildwatch.command.ListCommand;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.miscellaneous.BootUp;
import seedu.wildwatch.miscellaneous.ShutDown;
import seedu.wildwatch.ui.FilePrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {
    private static final String FILE_NAME = "WildWatch.txt";
    private static File openedFile; //File to save the entries

    public static void handleFile() {
        if (ExistenceChecker.checkFileExistence(FILE_NAME)) {
            FilePrinter.fileExistMessagePrinter();
            FilePrinter.openingFileMessagePrinter();
            openedFile = new File(FILE_NAME);
            try {
                FileLoader.loadFile(openedFile); // Loads entries from file
                assert openedFile != null : "Trying to read from a non-initialized file.";
                FilePrinter.entriesLoadedMessagePrinter();

                // Lists entries loaded from file
                ListCommand.listEntry();
            } catch (FileNotFoundException exception) {
                FilePrinter.noFileMessagePrinter();
            } catch (InvalidInputException e) {
                FilePrinter.corruptFileMessagePrinter();
                ShutDown.shutDownWithError();
                System.exit(0);
            }
        } else {  // File does not exist
            FilePrinter.noFileMessagePrinter();
            try {
                openedFile = FileCreator.createFile(FILE_NAME);
            } catch (IOException exception) {
                FilePrinter.fileCreationFailMessagePrinter();
                ShutDown.shutDownWithError();
                System.exit(0);
            }
            assert openedFile.exists() : "File was supposed to be created but it doesn't exist.";
        }

        BootUp.bootUpTwo(); //Welcome prompt message
    }
}
