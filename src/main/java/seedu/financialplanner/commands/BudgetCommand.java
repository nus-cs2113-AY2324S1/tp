package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class BudgetCommand extends Command {
    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    private double budget;
    private String command;

    public BudgetCommand(RawCommand rawCommand) throws FinancialPlannerException {
        command = String.join(" ", rawCommand.args);
        if (command.equals("delete") || command.equals("reset") || command.equals("view")) {
            return;
        }
        if (!command.equals("set") && !command.equals("update")) {
            logger.log(Level.WARNING, "Invalid arguments for budget command");
            throw new FinancialPlannerException("Budget command must be one of the following: set, update, " +
                    "delete, reset, view.");
        }

        if (command.equals("set") && Budget.hasBudget()) {
            logger.log(Level.WARNING, "Invalid command: Trying to set existing budget");
            throw new FinancialPlannerException("There is an existing budget, did you mean update?");
        } else if (command.equals("update") && !Budget.hasBudget()) {
            logger.log(Level.WARNING, "Invalid command: Trying to update non-existent budget");
            throw new FinancialPlannerException("There is no budget set yet, did you mean set?");
        }

        if (!rawCommand.extraArgs.containsKey("b")) {
            logger.log(Level.WARNING, "Missing argument /b in command");
            throw new IllegalArgumentException("Missing /b argument.");
        }

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
            throw new FinancialPlannerException("Budget should be lower than total balance.");
        }

        assert budget > 0 && budget <= Cashflow.getBalance() : "Budget should be greater than 0 and less than " +
                "or equal to total balance";
        rawCommand.extraArgs.remove("b");

        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        assert command.equals("set") || command.equals("update") || command.equals("delete") ||
                command.equals("reset") || command.equals("view");

        Ui ui = Ui.getInstance();
        switch (command) {
        case "set":
            logger.log(Level.INFO, "Setting budget");
            Budget.setBudget(budget);
            ui.showMessage("A monthly budget of " + Budget.getInitialBudgetString()
                    + " has been set.");
            break;
        case "update":
            logger.log(Level.INFO, "Updating budget");
            ui.printBudgetBeforeUpdate();
            Budget.updateBudget(budget);
            ui.printBudgetAfterUpdate();
            break;
        case "delete":
            if (Budget.hasBudget()) {
                Budget.deleteBudget();
                ui.printDeleteBudget();
            } else {
                ui.showMessage("Budget has not been set yet.");
            }
            break;
        case "reset":
            if (Budget.getInitialBudget() != Budget.getCurrentBudget()) {
                if (Budget.getInitialBudget() > Cashflow.getBalance()) {
                    Budget.setInitialBudget(Cashflow.getBalance());
                    ui.showMessage("Since initial budget exceeds current balance, " +
                            "budget will be reset to current balance.");
                }
                Budget.resetBudget();
                ui.printResetBudget();
            } else {
                ui.showMessage("Budget has not been spent yet.");
            }
            break;
        case "view":
            if (Budget.hasBudget()) {
                ui.printBudget();
            } else {
                ui.showMessage("There is no existing budget.");
            }
            break;
        default:
            logger.log(Level.SEVERE, "Unreachable default case reached");
            ui.showMessage("Unknown command.");
        }
    }
}
