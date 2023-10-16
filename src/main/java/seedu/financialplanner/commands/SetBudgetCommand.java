package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.utils.Ui;

public class SetBudgetCommand extends AbstractCommand {
    private double budget;

    public SetBudgetCommand(RawCommand rawCommand) throws FinancialPlannerException {
        if (!rawCommand.extraArgs.containsKey("b")) {
            throw new IllegalArgumentException("Missing /b argument.");
        }
        try {
            budget = Double.parseDouble(rawCommand.extraArgs.get("b"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Budget must be a number.");
        }
        rawCommand.extraArgs.remove("b");

        if (Budget.getInitialBudget() != 0) {
            throw new FinancialPlannerException("There is an existing budget, did you mean updatebudget?");
        }
    }

    @Override
    public void execute() {
        Budget.setBudget(budget);

        Ui.INSTANCE.showMessage("A monthly budget of " + String.format("%.2f", Budget.getInitialBudget()) + " has been set.");
    }
}
