package seedu.financialplanner.commands;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

public class BudgetCommand extends Command {
    private static final String BUDGET_DELIMITTER = "b/";
    private String input;

    public BudgetCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(Ui ui, FinancialList financialList, WatchList watchList) throws FinancialPlannerException {
        int budgetIndex = input.indexOf(BUDGET_DELIMITTER);
        if (budgetIndex == -1) {
            throw new FinancialPlannerException("Please ensure b/ is included in the command.");
        }
        String budgetString = input.substring(budgetIndex + BUDGET_DELIMITTER.length()).trim();
        double budget = Double.parseDouble(budgetString); //todo: add error handling here

        financialList.setBudget(new Budget(budget));
    }
}
