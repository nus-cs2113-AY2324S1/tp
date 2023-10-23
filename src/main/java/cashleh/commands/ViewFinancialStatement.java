package cashleh.commands;

import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.FinancialStatement;
import cashleh.transaction.IncomeStatement;

import java.util.logging.Level;

/**
 * This class extends the Command class and is used to encapsulate the action of viewing
 * data relative to the financial statement. When executed, it displays
 * both the incomes and the expenses as contained in the financial statement.
 * Additionally, it prints the cash on hand which corresponds to income minus expenses and
 * can be interpreted as liquid capital available to the user.
 */
public class ViewFinancialStatement extends Command {
    private FinancialStatement financialStatement;
    public ViewFinancialStatement(IncomeStatement incomeStatement, ExpenseStatement expenseStatement) {
        this.financialStatement = new FinancialStatement(incomeStatement, expenseStatement);
    }
    @Override
    public void execute() {
        assert financialStatement != null;
        financialStatement.printTransactions();
        logger.log(Level.INFO, "financial statement was successfully displayed");
    }
}
