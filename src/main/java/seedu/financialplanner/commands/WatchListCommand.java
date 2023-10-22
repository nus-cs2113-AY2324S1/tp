package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchListCommand extends AbstractCommand {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    public WatchListCommand(RawCommand rawCommand) throws IllegalArgumentException{
        if (!rawCommand.extraArgs.isEmpty()) {
            logger.log(Level.WARNING, "Invalid extra arguments found");
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            assert unknownExtraArgument != null;
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }
    @Override
    public void execute() {
        Ui.INSTANCE.printWatchListHeader();
        try {
            WatchList.INSTANCE.fetchFMPStockPrices();

            logger.log(Level.INFO, "Printing watchlist");
            Ui.INSTANCE.printStocksInfo(WatchList.INSTANCE);
        } catch (FinancialPlannerException e) {
            System.out.println(e.getMessage());
        }

    }
}
