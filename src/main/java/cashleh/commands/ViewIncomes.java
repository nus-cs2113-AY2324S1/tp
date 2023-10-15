package cashleh.commands;

import cashleh.ExpenseStatement;
import cashleh.IncomeStatement;

public class ViewIncomes extends Command {
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        incomeStatement.getIncomes();
    }
}
