package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.parser.Parser;
//import seedu.addressbook.storage.StorageFile;
//import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
//import seedu.addressbook.storage.StorageFile.StorageOperationException;
import seedu.duke.ui.TextUi;


/**
 * Entry point of the Address Book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    /**
     * Version info of the program.
     */
    public static final String VERSION = "AddressBook Level 2 - Version 1.0";

    private TextUi ui;

    //    private StorageFile storage;
    public static void main(String... launchArgs) {
        new Duke().run(launchArgs);
    }

    /**
     * Runs the program until termination.
     */
    public void run(String[] launchArgs) {
        start(launchArgs);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     *
     * @param launchArgs arguments supplied by the user at program launch
     */
    private void start(String[] launchArgs) {
        try {
            this.ui = new TextUi();
            // this.storage = initializeStorage(launchArgs);
            ui.showWelcomeMessage(VERSION, "storage.getPath()");

        } catch (Exception e) { // TODO: change to specific storage exceptions later
            ui.showInitFailedMessage();
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            CommandResult result = command.execute();
            // storage.save(addressBook);
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //    /**
    //     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
    //     * @param launchArgs arguments supplied by the user at program launch
    //     * @throws InvalidStorageFilePathException if the target file path is incorrect.
    //     */
    //    private StorageFile initializeStorage(String[] launchArgs) throws InvalidStorageFilePathException {
    //        boolean isStorageFileSpecifiedByUser = launchArgs.length > 0;
    //        return isStorageFileSpecifiedByUser ? new StorageFile(launchArgs[0]) : new StorageFile();
    //    }
}
