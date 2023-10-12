package seedu.financialplanner.commands;

import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

public class Invalid extends Command {
    public Invalid() {
    }

    @Override
    public void execute(Ui ui, FinancialList financialList, WatchList watchList) {
        ui.showMessage("Unknown command. Please try again.");
    }
}
