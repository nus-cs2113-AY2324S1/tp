package cashleh.commands;

import java.time.LocalDate;
import java.util.logging.Level;
import cashleh.ExpenseStatement;
import cashleh.Income;
import cashleh.IncomeStatement;
import cashleh.Ui;

public class AddIncome extends Command {
    private final Income incomeToAdd;
    public AddIncome(String description, double amount) {
        this.incomeToAdd = new Income(description, amount);
    }
    private final Ui ui = new Ui();
    public AddIncome(Income incomeToAdd) {
        this.incomeToAdd = incomeToAdd;
    }
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        assert incomeToAdd != null;
        incomeStatement.addIncome(incomeToAdd);
        assert incomeStatement.getNumberOfEntries() > 0;
        ui.printMultipleText(new String[] {"The following income was added:", incomeToAdd.toString()});
        logger.log(Level.INFO, "a new income was created and added to the incomeStatement");
    }
}
