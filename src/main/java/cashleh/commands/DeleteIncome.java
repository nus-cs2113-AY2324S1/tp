package cashleh.commands;

import cashleh.ExpenseStatement;
import cashleh.IncomeStatement;

public class DeleteIncome extends Command {
    public static final String COMMAND = "deleteIncome";
    public DeleteIncome(int incomeIndexToDelete) {
        super(incomeIndexToDelete);
    }
//    @Override
//    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
//        String incomeBeingDeleted = getIncome().toString();
//        incomeStatement.delete(getIndex());
//        System.out.println("The following income was deleted:\n" + incomeBeingDeleted);
//    }
}
