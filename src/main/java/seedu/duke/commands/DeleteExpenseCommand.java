package seedu.duke.commands;

import seedu.duke.financialrecords.Expense;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class DeleteExpenseCommand extends Commands {
    public DeleteExpenseCommand() {
    }

    public void execute(ArrayList<Expense> expenses, String fullcommand, Ui ui) throws KaChinnnngException {
        int index = 0;
        try {
            String[] tokens = fullcommand.split(" ", 3);
            index = Integer.parseInt(tokens[2]);
            Expense removedExpense = expenses.get(index-1);
            expenses.remove(index-1);
            System.out.println("Noted. This expense record has been deleted:");
            System.out.println(removedExpense);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KaChinnnngException("You're missing an argument");
        } catch (NullPointerException | NumberFormatException e) {
            throw new KaChinnnngException("Oops! An integer index is expected");
        } catch (IndexOutOfBoundsException e) {
            throw new KaChinnnngException("Oops! Expense " + index + " does not exist");
        }

    }
}
