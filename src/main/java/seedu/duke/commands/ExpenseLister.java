package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;
import seedu.duke.ui.Ui;
import java.util.ArrayList;

/**
 * Represents the command that when executed, lists all expenses.
 * This class is a child class of the Command class.
 *
 */
public class ExpenseLister extends Commands {
    private final ArrayList<Expense> expenses;
    private final Ui ui;

    /**
     * Constructor for ExpenseLister.
     *
     * @param expenses
     * @param ui
     */
    public ExpenseLister(ArrayList<Expense> expenses, Ui ui) {
        this.expenses = expenses;
        this.ui = ui;
    }

    /**
     * This method is used to execute the command.
     */
    @Override
    public void execute() {
        listExpenses();
    }

    /**
     * This method lists all incomes.
     */
    public void listExpenses() {
        if (expenses.isEmpty()) {
            ui.showLineDivider();
            System.out.println("You have no recorded expenses.");
            ui.showLineDivider();
            return;
        }

        ui.showLineDivider();
        System.out.println("Here are your expenses:");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i).toString());
        }
        ui.showLineDivider();
    }
}
