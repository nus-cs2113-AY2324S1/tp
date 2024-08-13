package seedu.wildwatch;

import seedu.wildwatch.miscellaneous.LogHandler;
import seedu.wildwatch.miscellaneous.BootUp;
import seedu.wildwatch.storage.FileHandler;
import seedu.wildwatch.execute.InputHandler;

public class WildWatch {
    /**
     * Main entry-point for the java.wildwatch.WildWatch application.
     */
    public static void main(String[] args) {
        LogHandler.configure(); //Configure Log
        BootUp.bootUpOne();
        FileHandler.handleFile(); //FileHandler takes on
        InputHandler.handleInput();

    }
}
