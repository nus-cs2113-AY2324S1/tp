package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command that inherits from the Command abstract class
 * Represents that command that add stock to watchlist
 */
@SuppressWarnings("unused")
public class AddStockCommand extends Command {

    public static final String NAME = "addstock";

    public static final String USAGE =
            "addstock </s STOCK CODE>";
    public static final String EXAMPLE =
            "addstock /s META";
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private final String stockCode;

    /**
     * Constructor for the command add stock to watchlist
     *
     * @param rawCommand
     * @throws IllegalArgumentException
     */
    public AddStockCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.containsKey("s")) {
            throw new IllegalArgumentException("Stock code cannot be empty");
        }

        logger.log(Level.INFO, "Parsing stockcode from input");
        stockCode = rawCommand.extraArgs.get("s").trim();

        rawCommand.extraArgs.remove("s");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to add stock to watchlist
     */
    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        WatchList watchList = WatchList.getInstance();
        String stockName;

        logger.log(Level.INFO, "adding stock to watchlist");
        try {
            stockName = watchList.addStock(stockCode);
            assert stockName != null;
            ui.printAddStock(stockName);
        } catch (FinancialPlannerException e) {
            logger.log(Level.WARNING, "Error adding stock to watchlist");
            Ui.getInstance().showMessage(e.getMessage());
        }
    }
}
