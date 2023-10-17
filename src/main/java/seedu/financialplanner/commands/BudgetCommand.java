package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BudgetCommand extends AbstractCommand {
    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    private double budget;
    private String command;

    public BudgetCommand(RawCommand rawCommand) throws FinancialPlannerException {
        command = String.join(" ", rawCommand.args);
        if (!command.equals("set") && !command.equals("update")) {
            logger.log(Level.WARNING, "Invalid arguments for budget");
            throw new FinancialPlannerException("Please indicate whether budget is to be set or update.");
        }

        if (command.equals("set") && Budget.hasBudget()) {
            logger.log(Level.INFO, "Trying to set existing budget");
            throw new FinancialPlannerException("There is an existing budget, did you mean update?");
        }

        if (command.equals("update") && !Budget.hasBudget()) {
            logger.log(Level.INFO, "Trying to update non-existent budget");
            throw new FinancialPlannerException("There is no budget set yet, did you mean set?");
        }

        if (!rawCommand.extraArgs.containsKey("b")) {
            logger.log(Level.WARNING, "Missing arguments b in command");
            throw new IllegalArgumentException("Missing /b argument.");
        }

        try {
            budget = Double.parseDouble(rawCommand.extraArgs.get("b"));
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid value for budget");
            throw new IllegalArgumentException("Budget must be a number.");
        }
        rawCommand.extraArgs.remove("b");
    }

    @Override
    public void execute() {
        assert command.equals("set") || command.equals("update") : "command should be set or update only";

        switch (command) {
        case "set":
            Budget.setBudget(budget);
            Ui.INSTANCE.showMessage("A monthly budget of " + String.format("%.2f", Budget.getInitialBudget())
                    + " has been set.");
            break;
        case "update":
            Ui.INSTANCE.showMessage("Budget has been updated:\nOld initial budget: " +
                    String.format("%.2f", Budget.getInitialBudget()) + "\nOld current budget: " +
                    String.format("%.2f", Budget.getCurrentBudget()));
            Budget.updateBudget(budget);
            Ui.INSTANCE.showMessage("New initial budget: " + String.format("%.2f", Budget.getInitialBudget()) +
                    "\nNew current budget: " + String.format("%.2f", Budget.getCurrentBudget()));
            if (Budget.getCurrentBudget() <= 0) {
                Ui.INSTANCE.showMessage("You have exceeded your budget, please update to a larger budget or " +
                        "reset the current budget to initial budget.");
            }
            break;
        default:
            logger.log(Level.WARNING, "command should never reach default");
            Ui.INSTANCE.showMessage("Unknown command.");
        }
    }
}
