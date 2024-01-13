//@@woodenclock
package seedu.wildwatch.execute;

import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.command.ByeCommand;
import seedu.wildwatch.ui.InputConsole;
import seedu.wildwatch.ui.LinePrinter;
import seedu.wildwatch.ui.InputPromptPrinter;

public class InputHandler {
    private static final Logger LOGGER = Logger.getLogger(InputHandler.class.getName());

    /**
     * Runs the WildWatch by looping through the I/O process.
     * If "bye" is received,
     */
    public static void handleInput() {
        boolean loopFlag = true;
        while (loopFlag) {
            InputPromptPrinter.inputPromptPrinter();
            String input = InputConsole.inputRetriever(); //Retrieves input of user
            if (!input.equals("bye")) {
                LinePrinter.printHorizontalLines();
            }
            LOGGER.log(Level.INFO, "Input received: {0}", input);
            loopFlag = CommandHandler.processCommand(input);
            EntryList.saveEntry();
            LinePrinter.printHorizontalLines();
        }
        ByeCommand.exitProgram();
    }
}
