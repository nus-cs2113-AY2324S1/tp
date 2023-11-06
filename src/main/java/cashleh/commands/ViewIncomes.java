package cashleh.commands;

import cashleh.transaction.IncomeStatement;

/**
 * This class extends the Command class and is
 * used to encapsulate the action of viewing incomes from the application's income statement.
 * When executed, it prints the list of incomes and logs the operation.
 */
public class ViewIncomes extends Command {
    private final IncomeStatement incomeStatement;

    public ViewIncomes(IncomeStatement incomeStatement) {
        this.incomeStatement = incomeStatement;
    }
    @Override
    public void execute() {
        assert incomeStatement != null;
        incomeStatement.printIncomes();
        logger.log(loggerLevel, "income statement was successfully displayed");
    }
}
