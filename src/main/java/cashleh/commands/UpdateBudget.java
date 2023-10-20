package cashleh.commands;

import cashleh.Ui;
import cashleh.budget.Budget;
import cashleh.budget.BudgetHandler;
import cashleh.exceptions.CashLehBudgetException;

import java.util.logging.Level;

/**
 * This class extends the Command class and is used to
 * encapsulate the action of updating a budget.
 * When executed, it updates the budget amount and logs the operation.
 */
public class UpdateBudget extends Command {
    private final BudgetHandler budgetHandler;
    private final Budget budget;

    /**
     * Constructs an UpdateBudget object with the specified parameters.
     * @param budgetHandler The object handling the budget.
     */
    public UpdateBudget(Budget budget, BudgetHandler budgetHandler) {
        this.budgetHandler = budgetHandler;
        this.budget = budget;
    }

    /**
     * Executes the command to edit the budget of the application.
     * This method updates the budget,
     * prints a message indicating the successful edit,
     * and logs the operation at the INFO level.
     */
    @Override
    public void execute() throws CashLehBudgetException {
        assert budget.isActive();
        if (budget.getBudget() <= 0) {
            throw new CashLehBudgetException("Invalid budget parameter. Please provide a different budget.");
        }
        budgetHandler.setBudget(budget);
        Ui.printMultipleText(new String[] {"The budget was updated to:", String.valueOf(budget.getBudget())});
        logger.log(Level.INFO, "budget was successfully updated");
    }

}
