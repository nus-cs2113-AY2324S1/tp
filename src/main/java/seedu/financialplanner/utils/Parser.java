package seedu.financialplanner.utils;


import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.Entry;
import seedu.financialplanner.commands.Exit;
import seedu.financialplanner.commands.WatchListCommand;
import seedu.financialplanner.commands.Invalid;
import seedu.financialplanner.commands.AddStockCommand;
import seedu.financialplanner.commands.Find;

public class Parser {
    private static final String EXIT_COMMAND = "exit";
    private static final String WATCHLIST_COMMAND = "watchlist";
    private static final String ADD_ENTRY_COMMAND = "add";
    private static final String ADD_STOCK_COMMAND = "addstock";
    private static final String FIND_COMMAND = "find";

    public static Command parse(String input) {
        String[] split = input.split(" ", 2);
        String command = split[0].toLowerCase();
        String restOfInput = split.length > 1 ? split[1] : ""; // checks if rest of input is empty

        switch (command) {
        case EXIT_COMMAND:
            return new Exit();
        case WATCHLIST_COMMAND:
            return new WatchListCommand();
        case ADD_ENTRY_COMMAND:
            return new Entry(restOfInput);
        case ADD_STOCK_COMMAND:
            return parseAddStock(restOfInput);
        case FIND_COMMAND:
            return new Find(restOfInput);
        default:
            return new Invalid();
        }
    }

    private static Command parseAddStock(String restOfInput) {
        String[] split = restOfInput.trim().split("m/|s/");
        // TODO: check error here
        String exchange = split[1].trim();
        String stockCode = split[2].trim();
        return new AddStockCommand(exchange, stockCode);
    }
}
