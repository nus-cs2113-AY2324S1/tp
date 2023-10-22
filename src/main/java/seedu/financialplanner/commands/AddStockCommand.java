package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddStockCommand extends AbstractCommand {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private final String stockCode;

    public AddStockCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.containsKey("s")) {
            throw new IllegalArgumentException("Stock code cannot be empty");
        }

        logger.log(Level.INFO, "Parsing stockcode from input");
        stockCode = rawCommand.extraArgs.get("s");

        rawCommand.extraArgs.remove("s");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        WatchList watchList = WatchList.INSTANCE;
        String stockName;

        logger.log(Level.INFO, "adding stock to watchlist");
        try {
            stockName = watchList.addStock(stockCode);
            assert stockName != null;
            ui.printAddStock(stockName);
        } catch (FinancialPlannerException e) {
            logger.log(Level.SEVERE, "Error adding stock to watchlist");
            System.out.println(e.getMessage());
        }
    }
}
