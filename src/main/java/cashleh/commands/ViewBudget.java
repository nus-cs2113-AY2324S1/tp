package cashleh.commands;

import cashleh.budget.BudgetHandler;
import cashleh.exceptions.CashLehBudgetException;

import java.util.logging.Level;

/**
 * This class extends the Command class and is
 * used to encapsulate the action of viewing the budget and its progress.
 * When executed, it prints the budget together with some data and a progress bar chart
 * and logs the operation.
 */
public class ViewBudget extends Command {
    private final BudgetHandler budgetHandler;
    public ViewBudget(BudgetHandler budgetHandler) {
        this.budgetHandler = budgetHandler;
    }
    /**
     * Executes the command to view the budget of the application.
     * This method displays the current budget, cash on hand as well
     * as a bar chart showing how much of the budget has been used.
     * Upon successful execution, it prints a confirmation message
     * and logs the operation at the INFO level.
     */
    @Override
    public void execute() throws CashLehBudgetException {
        assert budgetHandler.getBudget().isActive();
        budgetHandler.printBudget();
        logger.log(Level.INFO, "budget was successfully executed.");
    }
}
