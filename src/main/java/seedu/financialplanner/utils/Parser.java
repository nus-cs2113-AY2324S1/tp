package seedu.financialplanner.utils;

import seedu.financialplanner.commands.Entry;
import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.Exit;
import seedu.financialplanner.commands.Invalid;
import seedu.financialplanner.commands.WatchListCommand;

public class Parser {
    private static final String EXIT_COMMAND = "exit";

    private static final String WATCHLIST_COMMAND = "watchlist";

    private static final String ADD_ENTRY_COMMAND = "add";

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
        default:
            return new Invalid();
        }
    }
}
