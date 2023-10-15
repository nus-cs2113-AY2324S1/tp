package seedu.financialplanner.commands;

import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.utils.Ui;

public class Invalid extends Command {
    public Invalid() {
    }

    @Override
    public void execute(Ui ui, CashflowList cashflowList, WatchList watchList) {
        ui.showMessage("Unknown command. Please try again.");
    }
}
