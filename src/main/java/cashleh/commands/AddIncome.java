package cashleh.commands;

import cashleh.ExpenseStatement;
import cashleh.Income;
import cashleh.IncomeStatement;
import cashleh.Ui;

import java.time.LocalDate;

public class AddIncome extends Command {
    private final Income incomeToAdd;
    private final Ui ui = new Ui();
//    public AddIncome(int amount, String description, LocalDate date) {
//        this.incomeToAdd = new Income(amount, description, date);
//    }
    public AddIncome(Income incomeToAdd) {
        this.incomeToAdd = incomeToAdd;
    }
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        incomeStatement.addIncome(incomeToAdd);
        ui.printMultipleText(new String[] {
                "The following income was added:",
                incomeToAdd.toString()
        });
    }
}
