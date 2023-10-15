package seedu.financialplanner.commands;

import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

public class OverviewCommand extends Command {
    @Override
    public void execute(Ui ui, FinancialList financialList, WatchList watchList) {
        ui.showMessage("Here is an overview of your financials:");
    }
}
