package seedu.financialplanner.commands;

import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;

public class Invalid extends Command {
    public Invalid() {
    }

    @Override
    public void execute(Ui ui, WatchList watchList) {
        ui.showMessage("\tUnknown command. Please try again.");
    }
}
