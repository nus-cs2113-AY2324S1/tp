package seedu.financialplanner.utils;

import seedu.financialplanner.commands.Entry;
import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.Exit;
import seedu.financialplanner.commands.Invalid;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.FinancialList;

import java.util.ArrayList;

public class Parser {
    private static final String EXIT_COMMAND = "exit";
    private static final String ADD_ENTRY = "add";



    public static Command parse(String input, FinancialList list) {
        String[] split = input.split(" ", 2);
        String command = split[0].toLowerCase();
        String restOfInput = split.length > 1 ? split[1] : ""; // checks if rest of input is empty

        switch (command) {
        case EXIT_COMMAND:
            return new Exit();
        case ADD_ENTRY:
            return new Entry(restOfInput, list);
        default:
            return new Invalid();
        }
    }
}
