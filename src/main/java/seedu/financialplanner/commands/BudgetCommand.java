package seedu.financialplanner.commands;

import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to manage the budget.
 */
@SuppressWarnings("unused")
public class BudgetCommand extends Command {
    public static final String NAME = "budget";

    public static final String USAGE =
            "budget set </b BUDGET>" + "\n" +
            "budget update </b BUDGET>" + "\n" +
            "budget delete" + "\n" +
            "budget reset" + "\n" +
            "budget view";
    public static final String EXAMPLE =
            "budget set /b 500" + "\n" +
            "budget reset";
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private final Ui ui = Ui.getInstance();
    private double budget;
    private final String command;

    /**
     * Constructs a new BudgetCommand and checks if the user input is a valid command.
     *
     * @param rawCommand The raw command containing the arguments and parameters given by the user.
     * @throws FinancialPlannerException If there is an issue with the command provided.
     */
    public BudgetCommand(RawCommand rawCommand) throws FinancialPlannerException {
        if (rawCommand.args.isEmpty()) {
            throw new FinancialPlannerException("Budget operation cannot be empty.");
        }
        command = String.join(" ", rawCommand.args).trim();
        if (command.equals("delete") || command.equals("reset") || command.equals("view")) {
            return;
        }
        validateCommandFormat(rawCommand);
        validateBudget(rawCommand);

        assert budget > 0 && budget <= Cashflow.getBalance() : "Budget should be greater than 0 and less than " +
                "or equal to total balance";
        rawCommand.extraArgs.remove("b");

        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Checks if budget is a positive number and is lower than or equal to total balance.
     *
     * @param rawCommand The raw command containing arguments and parameters given by the user.
     * @throws FinancialPlannerException If there is an issue with budget format.
     */
    private void validateBudget(RawCommand rawCommand) throws FinancialPlannerException {
        try {
            logger.log(Level.INFO, "Parsing budget as double");
            budget = Double.parseDouble(rawCommand.extraArgs.get("b"));
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid value for budget");
            throw new IllegalArgumentException("Budget must be a number.");
        }
        if (budget <= 0) {
            logger.log(Level.WARNING, "Invalid value for budget.");
            throw new FinancialPlannerException("Budget should be greater than 0.");
        }

        if (budget > Cashflow.getBalance()) {
            logger.log(Level.WARNING, "Invalid value for budget");
            throw new FinancialPlannerException("Budget should be lower than or equal to total balance.");
        }
    }

    /**
     * Checks that the command is valid and in the right format.
     *
     * @param rawCommand The raw command containing arguments and parameters given by the user.
     * @throws FinancialPlannerException If there is an issue with the command or format.
     */
    private void validateCommandFormat(RawCommand rawCommand) throws FinancialPlannerException {
        if (!command.equals("set") && !command.equals("update")) {
            logger.log(Level.WARNING, "Invalid arguments for budget command");
            throw new FinancialPlannerException("Budget operation must be one of the following: set, update, " +
                    "delete, reset, view.");
        }

        if (command.equals("set") && Budget.hasBudget()) {
            logger.log(Level.WARNING, "Invalid command: Trying to set existing budget");
            throw new FinancialPlannerException("There is an existing budget, try budget update instead.");
        } else if (command.equals("update") && !Budget.hasBudget()) {
            logger.log(Level.WARNING, "Invalid command: Trying to update non-existent budget");
            throw new FinancialPlannerException("There is no budget set yet, try budget set instead.");
        }

        if (!rawCommand.extraArgs.containsKey("b")) {
            logger.log(Level.WARNING, "Missing argument /b in command");
            throw new IllegalArgumentException("Missing /b argument.");
        }
    }

    /**
     * Executes the budget command based on the specified operation.
     */
    @Override
    public void execute() {
        assert command.equals("set") || command.equals("update") || command.equals("delete") ||
                command.equals("reset") || command.equals("view");

        switch (command) {
        case "set":
            setBudget();
            break;
        case "update":
            updateBudget();
            break;
        case "delete":
            deleteBudget();
            break;
        case "reset":
            resetBudget();
            break;
        case "view":
            viewBudget();
            break;
        default:
            logger.log(Level.SEVERE, "Unreachable default case reached");
            ui.showMessage("Unknown command.");
        }
    }

    private void viewBudget() {
        if (Budget.hasBudget()) {
            ui.printBudget();
        } else {
            ui.printBudgetError("view");
        }
    }

    private void resetBudget() {
        if (Budget.getInitialBudget() != Budget.getCurrentBudget()) {
            if (Budget.getInitialBudget() > Cashflow.getBalance()) {
                Budget.setInitialBudget(Cashflow.getBalance());
                ui.printBudgetExceedBalance();
            }
            Budget.resetBudget();
            ui.printResetBudget();
        } else if (!Budget.hasBudget()) {
            ui.printBudgetError("delete");
        } else {
            ui.printBudgetError("reset");
        }
    }

    private void deleteBudget() {
        if (Budget.hasBudget()) {
            Budget.deleteBudget();
            ui.printDeleteBudget();
        } else {
            ui.printBudgetError("delete");
        }
    }

    private void updateBudget() {
        logger.log(Level.INFO, "Updating budget");
        ui.printBudgetBeforeUpdate();
        Budget.updateBudget(budget);
        ui.printBudgetAfterUpdate();
    }

    private void setBudget() {
        logger.log(Level.INFO, "Setting budget");
        Budget.setBudget(budget);
        ui.printSetBudget();
    }
}
