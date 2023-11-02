package seedu.financialplanner.commands;

import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

import static seedu.financialplanner.utils.Parser.ADD_CASHFLOW_COMMAND;
import static seedu.financialplanner.utils.Parser.ADD_REMINDER_COMMAND;
import static seedu.financialplanner.utils.Parser.ADD_STOCK_COMMAND;
import static seedu.financialplanner.utils.Parser.BALANCE_COMMAND;
import static seedu.financialplanner.utils.Parser.BUDGET_COMMAND;
import static seedu.financialplanner.utils.Parser.DELETE_CASHFLOW_COMMAND;
import static seedu.financialplanner.utils.Parser.DELETE_STOCK_COMMAND;
import static seedu.financialplanner.utils.Parser.EXIT_COMMAND;
import static seedu.financialplanner.utils.Parser.FIND_COMMAND;
import static seedu.financialplanner.utils.Parser.HELP_COMMAND;
import static seedu.financialplanner.utils.Parser.LIST_COMMAND;
import static seedu.financialplanner.utils.Parser.OVERVIEW_COMMAND;
import static seedu.financialplanner.utils.Parser.SET_GOAL_COMMAND;
import static seedu.financialplanner.utils.Parser.VISUALIZATION_COMMAND;
import static seedu.financialplanner.utils.Parser.WATCHLIST_COMMAND;

public class HelpCommand extends Command {
    private static final String EXIT_COMMAND_USAGE =
            "exit";
    private static final String WATCHLIST_COMMAND_USAGE =
            "watchlist";
    private static final String ADD_CASHFLOW_COMMAND_USAGE =
            "add <income/expense> </a AMOUNT> [/t TYPE] [/r RECURRENCE INTERVAL IN DAYS] [/d DESCRIPTION]";

    private static final String DELETE_CASHFLOW_COMMAND_USAGE =
            "delete [income/expense] <INDEX>";

    private static final String ADD_STOCK_COMMAND_USAGE =
            "addstock </s STOCK CODE>";

    private static final String DELETE_STOCK_COMMAND_USAGE =
            "deletestock </s STOCK CODE>";

    private static final String FIND_COMMAND_USAGE =
            "find <KEYWORD>";

    private static final String BUDGET_COMMAND_USAGE =
            "budget set </b BUDGET>"
            + "\n  " + "budget update </b BUDGET>"
            + "\n  " + "budget delete"
            + "\n  " + "budget reset"
            + "\n  " + "budget view";
    private static final String VISUALIZATION_COMMAND_USAGE =
            "vis </t TYPE> </c pie/bar/radar>";

    private static final String OVERVIEW_COMMAND_USAGE =
            "overview";
    private static final String ADD_REMINDER_COMMAND_USAGE =
            "addreminder </t TYPE> </d DATE>";

    private static final String LIST_COMMAND_USAGE =
            "list [income/expense]";

    private static final String SET_GOAL_COMMAND_USAGE =
            "set </g AMOUNT> </l LABEL>";

    private static final String BALANCE_COMMAND_USAGE =
            "balance";

    private static final String HELP_COMMAND_USAGE =
            "help [COMMAND]";

    private static final String HELP_MESSAGE_GENERAL =
            "<> denotes required arguments, [] denotes optional arguments"
            + "\n" + "Here are the available commands:"
            + "\n  " + EXIT_COMMAND_USAGE
            + "\n  " + WATCHLIST_COMMAND_USAGE
            + "\n  " + ADD_CASHFLOW_COMMAND_USAGE
            + "\n  " + DELETE_CASHFLOW_COMMAND_USAGE
            + "\n  " + ADD_STOCK_COMMAND_USAGE
            + "\n  " + DELETE_STOCK_COMMAND_USAGE
            + "\n  " + FIND_COMMAND_USAGE
            + "\n  " + BUDGET_COMMAND_USAGE
            + "\n  " + VISUALIZATION_COMMAND_USAGE
            + "\n  " + OVERVIEW_COMMAND_USAGE
            + "\n  " + ADD_REMINDER_COMMAND_USAGE
            + "\n  " + LIST_COMMAND_USAGE
            + "\n  " + SET_GOAL_COMMAND_USAGE
            + "\n  " + BALANCE_COMMAND_USAGE
            + "\n  " + HELP_COMMAND_USAGE;
    private final String commandName;

    public HelpCommand(RawCommand rawCommand) {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
        if (rawCommand.args.isEmpty()) {
            commandName = null;
        } else if (rawCommand.args.size() == 1) {
            commandName = rawCommand.args.get(0);
        } else {
            throw new IllegalArgumentException("Unknown arguments, type help for help");
        }
    }

    @Override
    public void execute() throws Exception {
        if (commandName == null) {
            Ui.getInstance().showMessage(HELP_MESSAGE_GENERAL);
            return;
        }
        switch (commandName) {
        case EXIT_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + EXIT_COMMAND_USAGE);
            break;
        case WATCHLIST_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + WATCHLIST_COMMAND_USAGE);
            break;
        case ADD_CASHFLOW_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + ADD_CASHFLOW_COMMAND_USAGE);
            break;
        case DELETE_CASHFLOW_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + DELETE_CASHFLOW_COMMAND_USAGE);
            break;
        case ADD_STOCK_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + ADD_STOCK_COMMAND_USAGE);
            break;
        case DELETE_STOCK_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + DELETE_STOCK_COMMAND_USAGE);
            break;
        case FIND_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + FIND_COMMAND_USAGE);
            break;
        case BUDGET_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + BUDGET_COMMAND_USAGE);
            break;
        case VISUALIZATION_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + VISUALIZATION_COMMAND_USAGE);
            break;
        case OVERVIEW_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + OVERVIEW_COMMAND_USAGE);
            break;
        case ADD_REMINDER_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + ADD_REMINDER_COMMAND_USAGE);
            break;
        case LIST_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + LIST_COMMAND_USAGE);
            break;
        case SET_GOAL_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + SET_GOAL_COMMAND_USAGE);
            break;
        case BALANCE_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + BALANCE_COMMAND_USAGE);
            break;
        case HELP_COMMAND:
            Ui.getInstance().showMessage("Usage:\n  " + HELP_COMMAND_USAGE);
            break;
        default:
            Ui.getInstance().showMessage("Unknown command, type help for help");
        }
    }
}
