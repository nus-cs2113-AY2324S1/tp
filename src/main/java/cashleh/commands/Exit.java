package cashleh.commands;

import cashleh.ExpenseStatement;
import cashleh.IncomeStatement;
import cashleh.Ui;

public class Exit extends Command {
    private final Ui ui = new Ui();
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        ui.printText("Bye. Hope to see you again soon!");
    }

}
