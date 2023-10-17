package cashleh.commands;

import cashleh.ExpenseStatement;
import cashleh.IncomeStatement;

import java.util.logging.Level;

public class ViewIncomes extends Command {
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        assert incomeStatement != null;
        incomeStatement.getIncomes();
        logger.log(Level.INFO, "income statement was successfully displayed");
    }
}
