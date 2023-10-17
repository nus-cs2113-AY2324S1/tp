package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

public class BudgetCommand extends AbstractCommand {
    private double budget;
    private String command;

    public BudgetCommand(RawCommand rawCommand) throws FinancialPlannerException {
        command = String.join(" ", rawCommand.args);
        if (!command.equals("set") && !command.equals("update")) {
            throw new FinancialPlannerException("Please indicate whether budget is to be set or update.");
        }

        if (command.equals("set") && Budget.hasBudget()) {
            throw new FinancialPlannerException("There is an existing budget, did you mean update?");
        }

        if (command.equals("update") && !Budget.hasBudget()) {
            throw new FinancialPlannerException("There is no budget set yet, did you mean set?");
        }

        if (!rawCommand.extraArgs.containsKey("b")) {
            throw new IllegalArgumentException("Missing /b argument.");
        }

        try {
            budget = Double.parseDouble(rawCommand.extraArgs.get("b"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Budget must be a number.");
        }
        rawCommand.extraArgs.remove("b");

        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        switch (command) {
        case "set":
            Budget.setBudget(budget);
            Ui.INSTANCE.showMessage("A monthly budget of " + Budget.getInitialBudgetString()
                    + " has been set.");
            break;
        case "update":
            Ui.INSTANCE.printBudgetBeforeUpdate();
            Budget.updateBudget(budget);
            Ui.INSTANCE.printBudgetAfterUpdate();

            if (Budget.getCurrentBudget() <= 0) {
                Ui.INSTANCE.showMessage("You have exceeded your budget, please update to a larger budget or " +
                        "reset the current budget to initial budget.");
            }
            break;
        default:
            Ui.INSTANCE.showMessage("Unknown command.");
        }
    }
}
