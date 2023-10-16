package cashleh.commands;

import cashleh.Expense;

public class ViewExpenses extends Command {

    public static final String COMMAND = "viewExpenses";
    public ViewExpenses() {
    }
    @Override
    public void execute() {
        System.out.println("The current sum of all your expenses amounts to: "
                + Expense.getTotalExpense() + System.lineSeparator() +
                "Here are the expenses in your list:" + getExpenseStatement());
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
