package cashleh.commands;

import cashleh.CashLehException;

public class DeleteExpense extends Command {
    public static final String COMMAND = "deleteExpense";

    public DeleteExpense(int expenseIndexToDelete) throws CashLehException {
        super(expenseIndexToDelete);
        if (expenseIndexToDelete > expenseStatement.getNumberOfExpenses()) {
            throw new CashLehException("Invalid input format. Please provide a valid task index to delete.");
        }
    }

    @Override
    public void execute() {
        String expenseBeingDeleted = getExpense().toString();
        expenseStatement.deleteExpense(getIndex());
        System.out.println("The following income was deleted:\n" + expenseBeingDeleted);
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
//                throw new CashLehException("Invalid expense index. Please provide valid expense index" + " < " + (expenseList.size() + 1) + " to delete expense.");
//            }
//        } catch (CashLehException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
