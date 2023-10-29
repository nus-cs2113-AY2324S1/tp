package seedu.duke.commands;

import seedu.duke.financialrecords.Income;

import java.util.ArrayList;

/**
 * The ClearIncomes class is responsible for clearing the list of incomes.
 * It provides a method to clear all incomes from the list.
 */
public class ClearIncomes {
    private ArrayList<Income> incomes;

    /**
     * Constructs a ClearIncomes object with the given list of incomes.
     *
     * @param incomes The list of incomes to be cleared.
     */
    public ClearIncomes(ArrayList<Income> incomes) {
        this.incomes = incomes;
    }

    /**
     * Clears all incomes from the list and prints a message to confirm the action.
     */
    public void clearAllIncomes() {
        incomes.clear();
        System.out.println("You have cleared the income list");
    }
}


