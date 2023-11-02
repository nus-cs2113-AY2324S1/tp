package seedu.financialplanner.utils;

import seedu.financialplanner.commands.Command;
import seedu.financialplanner.commands.AddStockCommand;
import seedu.financialplanner.commands.HelpCommand;
import seedu.financialplanner.commands.OverviewCommand;
import seedu.financialplanner.commands.AddCashflowCommand;
import seedu.financialplanner.commands.DeleteCashflowCommand;
import seedu.financialplanner.commands.ExitCommand;
import seedu.financialplanner.commands.FindCommand;
import seedu.financialplanner.commands.InvalidCommand;
import seedu.financialplanner.commands.ListCommand;
import seedu.financialplanner.commands.RawCommand;
import seedu.financialplanner.commands.WatchListCommand;
import seedu.financialplanner.commands.VisCommand;
import seedu.financialplanner.commands.BudgetCommand;
import seedu.financialplanner.commands.AddReminderCommand;
import seedu.financialplanner.commands.SetGoalCommand;
import seedu.financialplanner.commands.BalanceCommand;
import seedu.financialplanner.commands.DeleteStockCommand;
import seedu.financialplanner.commands.MarkGoalCommand;
import seedu.financialplanner.commands.MarkReminderCommand;
import seedu.financialplanner.commands.DeleteGoalCommand;
import seedu.financialplanner.commands.DeleteReminderCommand;
import seedu.financialplanner.commands.ReminderListCommand;
import seedu.financialplanner.commands.WishListCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Parser {
    public static final String EXIT_COMMAND = "exit";
    public static final String WATCHLIST_COMMAND = "watchlist";
    public static final String ADD_CASHFLOW_COMMAND = "add";
    public static final String DELETE_CASHFLOW_COMMAND = "delete";
    public static final String ADD_STOCK_COMMAND = "addstock";
    public static final String DELETE_STOCK_COMMAND = "deletestock";
    public static final String FIND_COMMAND = "find";
    public static final String OVERVIEW_COMMAND = "overview";
    public static final String BUDGET_COMMAND = "budget";
    public static final String VISUALIZATION_COMMAND = "vis";
    public static final String ADD_REMINDER_COMMAND = "addreminder";
    public static final String LIST_COMMAND = "list";
    public static final String SET_GOAL_COMMAND = "set";
    public static final String BALANCE_COMMAND = "balance";
    public static final String MARK_GOAL_COMMAND = "markgoal";
    public static final String MARK_REMINDER_COMMAND = "markreminder";
    public static final String DELETE_GOAL_COMMAND = "deletegoal";
    public static final String DELETE_REMINDER_COMMAND = "deletereminder";
    public static final String REMINDER_LIST_COMMAND = "reminderlist";
    public static final String WISH_LIST_COMMAND = "wishlist";
    public static final String HELP_COMMAND = "help";

    public static Command parseCommand(String input) throws FinancialPlannerException {
        RawCommand rawCommand = parseRawCommand(input);
        return parseCommand(rawCommand);
    }

    public static Command parseCommand(RawCommand rawCommand) throws FinancialPlannerException {
        switch (rawCommand.getCommandName()) {
        case EXIT_COMMAND:
            return new ExitCommand(rawCommand);
        case WATCHLIST_COMMAND:
            return new WatchListCommand(rawCommand);
        case ADD_CASHFLOW_COMMAND:
            return new AddCashflowCommand(rawCommand);
        case DELETE_CASHFLOW_COMMAND:
            return new DeleteCashflowCommand(rawCommand);
        case ADD_STOCK_COMMAND:
            return new AddStockCommand(rawCommand);
        case DELETE_STOCK_COMMAND:
            return new DeleteStockCommand(rawCommand);
        case FIND_COMMAND:
            return new FindCommand(rawCommand);
        case BUDGET_COMMAND:
            return new BudgetCommand(rawCommand);
        case VISUALIZATION_COMMAND:
            return new VisCommand(rawCommand);
        case OVERVIEW_COMMAND:
            return new OverviewCommand(rawCommand);
        case ADD_REMINDER_COMMAND:
            return new AddReminderCommand(rawCommand);
        case LIST_COMMAND:
            return new ListCommand(rawCommand);
        case SET_GOAL_COMMAND:
            return new SetGoalCommand(rawCommand);
        case BALANCE_COMMAND:
            return new BalanceCommand(rawCommand);
        case MARK_GOAL_COMMAND:
            return new MarkGoalCommand(rawCommand);
        case MARK_REMINDER_COMMAND:
            return new MarkReminderCommand(rawCommand);
        case DELETE_GOAL_COMMAND:
            return new DeleteGoalCommand(rawCommand);
        case DELETE_REMINDER_COMMAND:
            return new DeleteReminderCommand(rawCommand);
        case REMINDER_LIST_COMMAND:
            return new ReminderListCommand(rawCommand);
        case WISH_LIST_COMMAND:
            return new WishListCommand(rawCommand);
        case HELP_COMMAND:
            return new HelpCommand(rawCommand);
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
                    savePreviousExtraArgument(extraArgs, currentExtraArgumentName, extraArgumentContentBuffer);
                }
                if (next.length() == 1) {
                    throw new IllegalArgumentException("Extra argument name cannot be empty");
                }

                currentExtraArgumentName = next.substring(1);

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
            savePreviousExtraArgument(extraArgs, currentExtraArgumentName, extraArgumentContentBuffer);
        }

        return new RawCommand(commandName, args, extraArgs);
    }

    private static void savePreviousExtraArgument(Map<String, String> extraArgs
            , String currentExtraArgumentName, List<String> extraArgumentContentBuffer) {
        if (extraArgs.containsKey(currentExtraArgumentName)) {
            throw new IllegalArgumentException(
                    String.format("Duplicate extra argument name: %s", currentExtraArgumentName));
        } else {
            extraArgs.put(currentExtraArgumentName, String.join(" ", extraArgumentContentBuffer));
            extraArgumentContentBuffer.clear();
        }
    }
}
