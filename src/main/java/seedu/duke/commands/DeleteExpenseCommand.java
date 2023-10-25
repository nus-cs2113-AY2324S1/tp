package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * The `DeleteExpenseCommand` class represents a command to delete an expense record from a list of expenses.
 * It extends the `Commands` class and is used to manage expense records in the application.
 */
public class DeleteExpenseCommand extends Commands {
    /**
     * Constructs a `DeleteExpenseCommand` object. This class does not have any specific constructor logic.
     */
    public DeleteExpenseCommand() {
    }

    /**
     * Executes the `DeleteExpenseCommand` to remove an expense record from the list of expenses.
     *
     * @param expenses     The list of expense records to be modified.
     * @param fullcommand  The full command entered by the user, including the index of the expense to be deleted.
     * @param ui           The user interface for displaying messages.
     * @throws KaChinnnngException If there is a problem with the command execution, such as missing arguments,
     *                            an invalid index, or a non-existent expense record.
     */
    public void execute(ArrayList<Expense> expenses, String fullcommand, Ui ui) throws KaChinnnngException {
        int index = 0;
        try {
            String[] tokens = fullcommand.split(" ", 3);
            index = Integer.parseInt(tokens[2])-1;
            Expense removedExpense = expenses.get(index);
            expenses.remove(index);
            System.out.println("Noted. This expense record has been deleted:");
            System.out.println(removedExpense);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KaChinnnngException("You're missing an argument");
        } catch (NullPointerException | NumberFormatException e) {
            throw new KaChinnnngException("Oops! An integer index is expected");
        } catch (IndexOutOfBoundsException e) {
            throw new KaChinnnngException("Oops! Expense " + (index+1) + " does not exist");
        }
    }
}
