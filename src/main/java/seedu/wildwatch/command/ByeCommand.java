package seedu.wildwatch.command;

import seedu.wildwatch.miscellaneous.ShutDown;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    private static final Logger LOGGER = Logger.getLogger(ByeCommand.class.getName());
    /**
     * Exit the program
     */
    public static void exitProgram() {
        LOGGER.log(Level.INFO, "Initiating shutdown procedures.");
        ShutDown.shutDown();
    }

    //TODO: figure out a way to get rid of this
    public void execute() {
    }
}
