package seedu.stocker;

import seedu.stocker.authentication.LoginSystem;
import seedu.stocker.exceptions.StockerException;
import seedu.stocker.storage.Storage;
import seedu.stocker.ui.Ui;
import seedu.stocker.parser.Parser;
import seedu.stocker.commands.Command;
import seedu.stocker.commands.CommandResult;
import seedu.stocker.commands.ExitCommand;
import seedu.stocker.drugs.Inventory;

import java.io.IOException;


public class Stocker {

    private Ui ui;
    private Inventory inventory;
    private Storage storage;

    public static void main(String[] launchArgs) throws IOException, StockerException {
        new Stocker().run();
    }

    /**
     * Runs Login System.
     */
    public boolean startLogin() throws IOException, StockerException {
        this.ui = new Ui();
        ui.showLoginMessage();
        LoginSystem system = new LoginSystem();
        system.run();

        if (system.loginStatus == true) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Runs the program until termination.
     */
    public void run() throws IOException, StockerException {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, and prints the welcome message.
     */
    private void start() throws IOException, StockerException {
        this.ui = new Ui();
        this.inventory = new Inventory();
        this.storage = new Storage();
        storage.loadFileContents("drugs.txt");
        boolean checker = startLogin();
        assert checker == true;
        if(checker){
            ui.showWelcomeMessage();
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
    private void runCommandLoopUntilExitCommand() throws IOException, StockerException {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
            assert !ExitCommand.isExit((command));

        } while (!ExitCommand.isExit(command));
    }


    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) throws IOException, StockerException {
        command.setData(inventory);
        CommandResult result = command.execute();
        return result;
    }
}
