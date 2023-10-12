package seedu.financialplanner;

import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.Exit;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.storage.Storage;
import seedu.financialplanner.utils.Parser;
import seedu.financialplanner.utils.Ui;

public class FinancialPlanner {
    private Ui ui;
    private WatchList watchList;
    private FinancialList financialList;
    private Storage storage;
    private static final String FILE_PATH = "data/data.txt";

    public FinancialPlanner() throws FinancialPlannerException {
        ui = new Ui();
        financialList = new FinancialList();
        watchList = new WatchList();
        storage = new Storage();
        storage.load(financialList, ui, FILE_PATH);
    }

    public void run() {
        ui.welcomeMessage();
        String input;
        Command command = null;

        while (!(command instanceof Exit)) {
            input = ui.input();
            try {
                command = Parser.parse(input);
                command.execute(ui, financialList, watchList);
            } catch (FinancialPlannerException e) {
                ui.showMessage(e.getMessage());
            }
        }

        save();
        ui.exitMessage();
    }

    public void save() {
        try {
            storage.save(financialList, FILE_PATH);
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            new FinancialPlanner().run();
        } catch (FinancialPlannerException e) {
            Ui.printCorruptedFileError(e.getMessage());
        }
    }
}
