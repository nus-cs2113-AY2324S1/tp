package cashleh.commands;

import cashleh.Ui;
import cashleh.budget.BudgetHandler;
import cashleh.exceptions.CashLehBudgetException;

/**
 * This class extends the Command class and is used to
 * encapsulate the action of deleting a budget previously set on the application.
 * When executed, it removes the specified budget by setting its status to not active, logs the operation,
 * and prints a confirmation message.
 */
public class DeleteBudget extends Command {
    private final BudgetHandler budgetHandler;
    public DeleteBudget(BudgetHandler budgetHandler) {
        this.budgetHandler = budgetHandler;
    }

    /**
     * Executes the command to delete the budget from the application,
     * logs the operation, and prints a confirmation message.
     */
    @Override
    public void execute() throws CashLehBudgetException {
        if (budgetHandler.getBudget().isActive()) {
            budgetHandler.deleteBudget();
            assert !budgetHandler.getBudget().isActive();
            Ui.printMultipleText(new String[] {"Alright, CashLeh has just deleted your previous budget!", "Watch out "
                    + "though as spending without budget ain't smart..."});
            logger.log(loggerLevel, "budget was successfully deleted");
        } else {
            String text = "Please create a new budget as you haven't"
                    + " set one yet or deleted the previous one.";
            throw new CashLehBudgetException(text);
        }
    }
}
