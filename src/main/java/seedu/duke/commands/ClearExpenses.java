package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;

import java.util.ArrayList;

/**
 * The ClearExpenses class is responsible for clearing the list of expenses.
 * It provides a method to clear all expenses from the list.
 */
public class ClearExpenses {
    private ArrayList<Expense> expenses;

    /**
     * Constructs a ClearExpenses object with the given list of expenses.
     *
     * @param expenses The list of expenses to be cleared.
     */
    public ClearExpenses(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    /**
     * Clears all expenses from the list and prints a message to confirm the action.
     */
    public void clearAllExpenses(){
        expenses.clear();
        System.out.println("You have cleared the expense list");
    }
}
