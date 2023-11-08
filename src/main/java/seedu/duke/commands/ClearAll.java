package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Income;

import java.util.ArrayList;

/**
 * The ClearAll class is responsible for clearing both the list of incomes and expenses.
 * It provides a method to clear all incomes and expenses from the corresponding list.
 */
public class ClearAll {
    private final ArrayList<Income> incomes;
    private final ArrayList<Expense> expenses;

    /**
     * Constructs a ClearAll object with the given lists of incomes and expenses.
     *
     * @param incomes   The list of incomes to be cleared.
     * @param expenses  The list of expenses to be cleared.
     */
    public ClearAll(ArrayList<Income> incomes, ArrayList<Expense> expenses) {
        assert incomes != null : "incomes should not be null";
        assert expenses != null : "expenses should not be null";
        this.incomes = incomes;
        this.expenses = expenses;
    }

    /**
     * Clears all incomes and expenses from their respective lists and prints a message to confirm the action.
     */
    public void clearAllIncomeAndExpense() {
        incomes.clear();
        expenses.clear();
        System.out.println("Both income and expense lists cleared");
    }
}
