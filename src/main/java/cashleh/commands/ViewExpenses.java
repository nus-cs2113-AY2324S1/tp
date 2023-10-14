package cashleh.commands;

import cashleh.Expense;
import cashleh.ExpenseStatement;
import cashleh.IncomeStatement;

public class ViewExpenses extends Command {
    @Override
    public void execute(ExpenseStatement expenseStatement, IncomeStatement incomeStatement) {
        expenseStatement.getExpenses();
    }
}

//    public static void viewExpenses(ArrayList<Expense> expenseList) {
//        System.out.println("Your total expense is: " + Expense.getTotalExpense());
//        System.out.println("Here are the expenses in your list:");
//        for (int i = 0; i < expenseList.size(); i++) {
//            System.out.print((i + 1) + ". ");
//            System.out.println(expenseList.get(i));
//        }
//    }
