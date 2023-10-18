package cashleh.commands;

import cashleh.transaction.IncomeStatement;

import java.util.logging.Level;

public class ViewIncomes extends Command {
    private final IncomeStatement incomeStatement;

    public ViewIncomes(IncomeStatement incomeStatement) {
        this.incomeStatement = incomeStatement;
    }
    @Override
    public void execute() {
        assert incomeStatement != null;
        incomeStatement.printIncomes();
        logger.log(Level.INFO, "income statement was successfully displayed");
    }
}
