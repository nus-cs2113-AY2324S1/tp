package cashleh.commands;

import cashleh.ExpenseStatement;
import cashleh.Income;
import cashleh.IncomeStatement;

import java.time.LocalDate;

public class AddIncome extends Command {
    private final Income incomeToAdd;
    public AddIncome(int amount, String description, LocalDate date) {
        this.incomeToAdd = new Income(amount, description, date);
    }
    public AddIncome(Income incomeToAdd) {
        this.incomeToAdd = incomeToAdd;
    }
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        incomeStatement.add(incomeToAdd);
//        System.out.println("The following income was added:\n" + getIncome());
    }
}
