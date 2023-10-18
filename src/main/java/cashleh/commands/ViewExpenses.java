package cashleh.commands;

import cashleh.transaction.ExpenseStatement;

import java.util.logging.Level;

public class ViewExpenses extends Command {
    private final ExpenseStatement expenseStatement;

    public ViewExpenses(ExpenseStatement expenseStatement) {
        this.expenseStatement = expenseStatement;
    }
    @Override
    public void execute() {
        assert expenseStatement != null;
        expenseStatement.printExpenses();
        logger.log(Level.INFO, "expense statement was successfully displayed");
    }
}
