package cashleh.commands;

import cashleh.transaction.Income;
import cashleh.transaction.IncomeStatement;
import cashleh.Ui;

import java.util.logging.Level;

public class AddIncome extends Command {
    private final Income incomeToAdd;
    private final IncomeStatement incomeStatement;
    public AddIncome(Income incomeToAdd, IncomeStatement incomeStatement) {
        this.incomeToAdd = incomeToAdd;
        this.incomeStatement = incomeStatement;
    }
    @Override
    public void execute() {
        incomeStatement.addIncome(incomeToAdd);
        Ui.printMultipleText(new String[] {"The following income was added:", incomeToAdd.toString()});
        logger.log(Level.INFO, "income entry was successfully added to the income statement");
    }
}
