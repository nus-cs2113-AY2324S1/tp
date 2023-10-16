package seedu.financialplanner.commands;

import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.CashflowList;

public class BudgetCommand extends AbstractCommand {
    private double budget;

    public BudgetCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.containsKey("b")) {
            throw new IllegalArgumentException("Missing /b argument.");
        }
        try {
            budget = Double.parseDouble(rawCommand.extraArgs.get("b"));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Budget must be a number.");
        }
        rawCommand.extraArgs.remove("b");
    }

    @Override
    public void execute() throws Exception {
        CashflowList.INSTANCE.setBudget(new Budget(budget));
    }
}
