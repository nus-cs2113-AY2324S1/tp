package seedu.nuscents;

import seedu.nuscents.commands.Command;
import seedu.nuscents.commands.ExitCommand;
import seedu.nuscents.data.exception.NuscentsException;
import seedu.nuscents.parser.Parser;
import seedu.nuscents.storage.Storage;
import seedu.nuscents.ui.Ui;
import seedu.nuscents.data.TransactionList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Nuscents {
    private Ui ui;
    private Storage storage;
    private TransactionList transactions;

    /**
     * Sets up the required objects and loads up the data from the storage file.
     *
     * @param storageFilePath path of the file used to store data
     */
    public Nuscents(String storageFilePath, String budgetFilePath)
            throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(storageFilePath);
        try {
            transactions = new TransactionList(storage.readDataFromFile());
        } catch (FileNotFoundException e) {
            Ui.showReadDataError();
            File storageFile = new File(storageFilePath);
            storageFile.getParentFile().mkdirs();
            storageFile.createNewFile();
            transactions = new TransactionList(storage.readDataFromFile());
        }

        File budgetFile = new File(budgetFilePath);
        if (budgetFile.exists()) {
            transactions.setBudget(storage.readBudgetFromFile());
        }

    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        Ui.showWelcomeMessage();
        runProgramUntilTermination();
    }

    /**
     * Reads the user command and executes it, until the user issues the "bye" command.
     * This method continuously prompts the user for commands and executes them until the user enters "bye".
     */
    private void runProgramUntilTermination() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Command command = Parser.parseCommand(fullCommand);
                command.execute(transactions);
                isExit = ExitCommand.isExit(command);
                storage.writeToFile(transactions);
                storage.writeBudgetToFile(transactions);
            } catch (NuscentsException e) {
                ui.showException(e);
            } catch (IOException | ParseException e) {
                Ui.showFatalErrorMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.showException(e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Nuscents("./data/nuscents.txt", "./data/budget.txt").run();
        } catch (IOException e) {
            Ui.showFileAccessErrorMessage();
        } catch (ParseException e) {
            Ui.showFatalErrorMessage();
        }
    }
}
