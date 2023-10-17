package cashleh.commands;

import cashleh.transaction.IncomeStatement;

public class ViewIncomes extends Command {
    private IncomeStatement incomeStatement;

    public ViewIncomes(IncomeStatement incomeStatement) {
        this.incomeStatement = incomeStatement;
    }
    @Override
    public void execute() {
        incomeStatement.printIncomes();
    }
}
