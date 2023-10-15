package cashleh.commands;

import cashleh.ExpenseStatement;
import cashleh.Income;
import cashleh.IncomeStatement;
import cashleh.Ui;

public class AddIncome extends Command {
    private final Income incomeToAdd;
    private final Ui ui = new Ui();
    public AddIncome(Income incomeToAdd) {
        this.incomeToAdd = incomeToAdd;
    }
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        incomeStatement.addIncome(incomeToAdd);
        ui.printMultipleText(new String[] {"The following income was added:", incomeToAdd.toString()});
    }
}
