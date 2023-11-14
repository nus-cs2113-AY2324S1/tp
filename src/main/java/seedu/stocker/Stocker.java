package seedu.stocker;

import seedu.stocker.authentication.LoginSystem;
import seedu.stocker.storage.Storage;
import seedu.stocker.ui.Ui;
import seedu.stocker.parser.Parser;
import seedu.stocker.commands.Command;
import seedu.stocker.commands.CommandResult;
import seedu.stocker.commands.ExitCommand;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;
import seedu.stocker.vendors.VendorsList;

import java.io.IOException;

import static seedu.stocker.common.Messages.MESSAGE_EXECUTION_FAILED;

public class Stocker {

    private Ui ui;
    private Inventory inventory;
    private SalesList salesList;
    private Cart currentCart;
    private VendorsList vendorsList;

    public static void main(String[] launchArgs) {
        new Stocker().run();
    }

    /**
     * Runs Login System.
     */
    public boolean startLogin() throws IOException {
        LoginSystem system = new LoginSystem();
        system.run();

        return system.loginStatus;
    }


    /**
     * Runs the program until termination.
     */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, and prints the welcome message.
     */
    private void start() {
        try {
            this.ui = new Ui();
            this.inventory = new Inventory();
            this.salesList = new SalesList();
            this.currentCart = new Cart();
            this.vendorsList = new VendorsList();
            Storage storage = new Storage(inventory);
            storage.loadFileContents("drugs.txt");
            storage.loadSoldItems("soldItems.txt", salesList);
            boolean checker = startLogin();
            assert checker;
            ui.showWelcomeMessage();
        } catch (Exception e) {
            ui.showInitFailedMessage();
            System.exit(0);
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
            ui.showResultToUser(executeCommand(command));
        } while (!ExitCommand.isExit(command));
    }


    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult<Object> executeCommand(Command command) {
        try {
            command.setData(inventory, salesList, currentCart, vendorsList);
            return command.execute();
        } catch (IOException ioe) {
            return new CommandResult<>(MESSAGE_EXECUTION_FAILED);
        }
    }
}
