package seedu.financialplanner;

import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.ExitCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.storage.Storage;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

import java.time.LocalDate;

/**
 * Represents the main class for the Financial Planner.
 * It manages commands and user interactions.
 */
public class FinancialPlanner {
    private static final LocalDate date = LocalDate.now();
    private static final String FILE_PATH = "data/data.txt";
    private final Storage storage = Storage.getInstance();
    private final Ui ui = Ui.getInstance();
    private final WatchList watchList = WatchList.getInstance();
    private final CashflowList cashflowList = CashflowList.getInstance();
    
    private FinancialPlanner() {
    }

    /**
     * The main starting point of the Financial Planner.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        FinancialPlannerLogger.initialise();
        new FinancialPlanner().run();
    }

    /**
     * Loads storage from save file and starts the Financial Planner.
     * Saves the storage to save file upon exit.
     */
    public void run() {
        try {
            storage.load(FILE_PATH, date);
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
            return;
        }

        ui.welcomeMessage();
        String input;
        Command command = null;

        while (!(command instanceof ExitCommand)) {
            input = ui.input();
            try {
                command = Parser.parseCommand(input);
                command.execute();
            } catch (Exception e) {
                ui.showMessage(e.getMessage());
            }
        }
        save();
        ui.exitMessage();
    }

    /**
     * Saves existing data to the save file.
     */
    public void save() {
        try {
            storage.save(FILE_PATH);
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
