package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class BudgetCommand extends AbstractCommand {
    private static Logger logger = Logger.getLogger("Financial Planner Logger");
    private double budget;
    private String command;

    public BudgetCommand(RawCommand rawCommand) throws FinancialPlannerException {
        command = String.join(" ", rawCommand.args);
        if (!command.equals("set") && !command.equals("update")) {
            logger.log(Level.WARNING, "Invalid arguments for budget command");
            throw new FinancialPlannerException("Please indicate whether budget is to be set or update.");
        }

        if (command.equals("set") && Budget.hasBudget()) {
            logger.log(Level.WARNING, "Invalid command: Trying to set existing budget");
            throw new FinancialPlannerException("There is an existing budget, did you mean update?");
        }

        if (command.equals("update") && !Budget.hasBudget()) {
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
        assert command.equals("set") || command.equals("update") : "Command should be set or update only";

        switch (command) {
        case "set":
            logger.log(Level.INFO, "Setting budget");
            Budget.setBudget(budget);
            Ui.INSTANCE.showMessage("A monthly budget of " + Budget.getInitialBudgetString()
                    + " has been set.");
            break;
        case "update":
            logger.log(Level.INFO, "Updating budget");
            Ui.INSTANCE.printBudgetBeforeUpdate();
            Budget.updateBudget(budget);
            Ui.INSTANCE.printBudgetAfterUpdate();
            break;
        default:
            logger.log(Level.SEVERE, "Unreachable default case reached");
            Ui.INSTANCE.showMessage("Unknown command.");
        }
    }
}
