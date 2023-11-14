package cashleh.commands;

import cashleh.transaction.ExpenseStatement;

/**
 * This class extends the Command class and is
 * used to encapsulate the action of viewing expenses from the application's expense statement.
 * When executed, it prints the list of expenses and logs the operation.
 */
public class ViewExpenses extends Command {
    private final ExpenseStatement expenseStatement;

    public ViewExpenses(ExpenseStatement expenseStatement) {
        this.expenseStatement = expenseStatement;
    }
    @Override
    public void execute() {
        assert expenseStatement != null;
        expenseStatement.printExpenses();
        logger.log(loggerLevel, "expense statement was successfully displayed");
    }
}
