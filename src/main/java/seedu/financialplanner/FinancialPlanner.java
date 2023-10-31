package seedu.financialplanner;

import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.ExitCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.storage.Storage;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

import java.time.LocalDate;

public class FinancialPlanner {
    public static final LocalDate currentDate = LocalDate.now();
    private static final String FILE_PATH = "data/data.txt";
    private final Storage storage = Storage.getInstance();
    private final Ui ui = Ui.getInstance();
    private final WatchList watchList = WatchList.getInstance();
    
    private FinancialPlanner() {
    }

    public static void main(String[] args) {
        FinancialPlannerLogger.initialise();
        new FinancialPlanner().run();
    }

    public void run() {
        try {
            storage.load(FILE_PATH, currentDate);
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

    public void save() {
        try {
            storage.save(FILE_PATH);
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
