package seedu.financialplanner.utils;

import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.Exit;
import seedu.financialplanner.commands.Invalid;

public class Parser {
    private static final String EXIT_COMMAND = "exit";

    public static Command parse(String input) {
        String[] split = input.split(" ", 2);
        String command = split[0].toLowerCase();
        String restOfInput = split.length > 1 ? split[1] : ""; // checks if rest of input is empty

        switch (command) {
        case EXIT_COMMAND:
            return new Exit();
        default:
            return new Invalid();
        }
    }
}
