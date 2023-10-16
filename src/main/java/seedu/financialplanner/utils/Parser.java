package seedu.financialplanner.utils;

import seedu.financialplanner.commands.AbstractCommand;
import seedu.financialplanner.commands.AddStockCommand;
import seedu.financialplanner.commands.EntryCommand;
import seedu.financialplanner.commands.ExitCommand;
import seedu.financialplanner.commands.FindCommand;
import seedu.financialplanner.commands.InvalidCommand;
import seedu.financialplanner.commands.RawCommand;
import seedu.financialplanner.commands.WatchListCommand;
import seedu.financialplanner.commands.VisCommand;
import seedu.financialplanner.commands.BudgetCommand;
import seedu.financialplanner.commands.AddReminderCommand;
import seedu.financialplanner.commands.SetGoalCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Parser {
    private static final String EXIT_COMMAND = "exit";
    private static final String WATCHLIST_COMMAND = "watchlist";
    private static final String ADD_ENTRY_COMMAND = "add";
    private static final String ADD_STOCK_COMMAND = "addstock";
    private static final String FIND_COMMAND = "find";
    private static final String BUDGET_COMMAND = "budget";
    private static final String VISUALIZATION_COMMAND = "vis";
    private static final String ADD_REMINDER_COMMAND = "addreminder";
    private static final String SET_GOAL_COMMAND = "set";
    public static AbstractCommand parseCommand(String input) throws FinancialPlannerException {
        RawCommand rawCommand = parseRawCommand(input);
        return parseCommand(rawCommand);
    }

    public static AbstractCommand parseCommand(RawCommand rawCommand) throws FinancialPlannerException {
        switch (rawCommand.getCommandName()) {
        case EXIT_COMMAND:
            return new ExitCommand(rawCommand);
        case WATCHLIST_COMMAND:
            return new WatchListCommand(rawCommand);
        case ADD_ENTRY_COMMAND:
            return new EntryCommand(rawCommand);
        case ADD_STOCK_COMMAND:
            return new AddStockCommand(rawCommand);
        case FIND_COMMAND:
            return new FindCommand(rawCommand);
        case BUDGET_COMMAND:
            return new BudgetCommand(rawCommand);
        case VISUALIZATION_COMMAND:
            return new VisCommand(rawCommand);
        case ADD_REMINDER_COMMAND:
            return new AddReminderCommand(rawCommand);
        case SET_GOAL_COMMAND:
            return new SetGoalCommand(rawCommand);
        default:
            return new InvalidCommand();
        }
    }

    public static RawCommand parseRawCommand(String input) throws IllegalArgumentException{
        Iterator<String> iterator = Arrays.stream(input.split(" ")).iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }
        String commandName = iterator.next();
        List<String> args = new ArrayList<>();
        Map<String, String> extraArgs = new HashMap<>();

        List<String> extraArgumentContentBuffer = new ArrayList<>();
        String currentExtraArgumentName = null;

        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.startsWith("/")) {
                // Save previous extra argument when next extra argument is found
                if (currentExtraArgumentName != null) {
                    if (extraArgs.containsKey(currentExtraArgumentName)) {
                        throw new IllegalArgumentException(
                                String.format("Duplicate extra argument name: %s", currentExtraArgumentName));
                    } else {
                        extraArgs.put(currentExtraArgumentName, String.join(" ", extraArgumentContentBuffer));
                        extraArgumentContentBuffer.clear();
                    }
                }

                if (next.length() == 1) {
                    throw new IllegalArgumentException("Extra argument name cannot be empty");
                }

                currentExtraArgumentName =next.substring(1);

            } else {
                if (currentExtraArgumentName == null) {
                    args.add(next);
                } else {
                    extraArgumentContentBuffer.add(next);
                }
            }
        }
        // Save previous extra argument at the very end
        if (currentExtraArgumentName != null) {
            if (extraArgs.containsKey(currentExtraArgumentName)) {
                throw new IllegalArgumentException(
                        String.format("Duplicate extra argument name: %s", currentExtraArgumentName));
            } else {
                extraArgs.put(currentExtraArgumentName, String.join(" ", extraArgumentContentBuffer));
                extraArgumentContentBuffer.clear();
            }
        }

        return new RawCommand(commandName, args, extraArgs);
    }
}
