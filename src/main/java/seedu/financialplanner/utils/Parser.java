package seedu.financialplanner.utils;


import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.AddCashflowCommand;
import seedu.financialplanner.commands.DeleteCashflowCommand;
import seedu.financialplanner.commands.Exit;
import seedu.financialplanner.commands.WatchListCommand;
import seedu.financialplanner.commands.Invalid;
import seedu.financialplanner.commands.AddStockCommand;
import seedu.financialplanner.commands.Find;


public class Parser {
    private static final String EXIT_COMMAND = "exit";
    private static final String WATCHLIST_COMMAND = "watchlist";
    private static final String ADD_ENTRY_COMMAND = "add";
    private static final String DELETE_ENTRY_COMMAND = "delete";
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
            return parseAddCashflow(restOfInput);
        case DELETE_ENTRY_COMMAND:
            return new DeleteCashflowCommand(restOfInput);
        case ADD_STOCK_COMMAND:
            return parseAddStock(restOfInput);
        case FIND_COMMAND:
            return new Find(restOfInput);
        default:
            return new Invalid();
        }
    }

    private static Command parseAddStock(String restOfInput) {
        String[] split = restOfInput.trim().split("s/");
        // TODO: check error here
        String stockCode = split[1].trim();
        return new AddStockCommand(stockCode);
    }
    private static int determineRecur(String parameters) {
        if (parameters.contains("r/")) {
            int indexOfRecur = parameters.indexOf("r/");
            String recur = parameters.substring(indexOfRecur + 2).trim();
            return Integer.parseInt(recur);
        }
        return 0;
    }
    public static Command parseAddCashflow(String restOfInput) {
        String type;
        double amount;
        int recur;

        String[] split = restOfInput.split(" ", 2);
        String cashflowType = split[0];
        String parameters = split[1];
        recur = determineRecur(parameters);
        int indexOfAmount = parameters.indexOf("a/");
        int indexOfType = parameters.indexOf("t/");
        amount = Double.parseDouble(parameters.substring(indexOfAmount + 2, indexOfType).trim());
        if (recur == 0) {
            type = parameters.substring(indexOfType + 2).trim();
        } else {
            int indexOfRecur = parameters.indexOf("r/");
            type = parameters.substring(indexOfType + 2, indexOfRecur).trim();
        }

        return new AddCashflowCommand(cashflowType, amount, type, recur);
    }
}
