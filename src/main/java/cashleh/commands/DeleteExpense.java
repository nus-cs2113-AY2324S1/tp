package cashleh.commands;

import exceptions.CashLehMissingTransactionException;
import cashleh.transaction.ExpenseStatement;
import cashleh.Ui;

public class DeleteExpense extends Command {
    private final int expenseIndex;
    private final ExpenseStatement expenseStatement;
    public DeleteExpense(int expenseIndex, ExpenseStatement expenseStatement) {
        this.expenseIndex = expenseIndex;
        this.expenseStatement = expenseStatement;
    }

    @Override
    public void execute() throws CashLehMissingTransactionException {
        try {
            String expenseBeingDeleted = expenseStatement.getExpense(expenseIndex - 1).toString();
            expenseStatement.deleteExpense(expenseIndex - 1);
            Ui.printMultipleText(new String[] {
                "Noted! CashLeh has removed the following expense:",
                expenseBeingDeleted
            });
        } catch (CashLehMissingTransactionException e) {
            throw new CashLehMissingTransactionException();
        }

    }
}

//    public static void deleteExpense(ArrayList<Expense> expenseList, String userInput) {
//        try {
//            int DELETE_START_INDEX = 13;
//            if (userInput.length() <= DELETE_START_INDEX) {
//                throw new CashLehException("Invalid input format. Please provide a task index to delete.");
//            }
//            int expenseIndex = Integer.parseInt(userInput.substring(DELETE_START_INDEX).trim()) - 1;
//            if (expenseIndex >= 0 && expenseIndex < expenseList.size()) {
//                System.out.println("Noted. I've removed this expense:");
//                System.out.println(expenseList.get(expenseIndex));
//                System.out.println("Now you have " + (expenseList.size() - 1) + " expense(s) in the list.");
//                expenseList.remove(expenseIndex);
//            } else {
//                if (expenseList.isEmpty()) {
//                    throw new CashLehException("Expense list is empty.");
//                }
//                throw new CashLehException("Invalid expense index. Please provide valid expense index" +
//                " < " + (expenseList.size() + 1) + " to delete expense.");
//            }
//        } catch (CashLehException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
