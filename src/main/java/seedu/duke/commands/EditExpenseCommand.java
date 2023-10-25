package seedu.duke.commands;


import seedu.duke.financialrecords.Expense;

import java.util.ArrayList;

public class EditExpenseCommand extends Commands {
    ArrayList<Expense> expenses;
    String fullCommand;
    int index;

    /**
     * Constructs an EditExpenseCommand with the specified expense records and full command.
     *
     * @param expenses     The ArrayList of Expense objects containing expense records.
     * @param fullCommand  The full command string entered by the user.
     * @throws KaChinnnngException If there is an error in the command creation, such as missing arguments.
     */
    public EditExpenseCommand(ArrayList<Expense> expenses, String fullCommand) throws KaChinnnngException {
        this.expenses = expenses;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the edit expense command. It parses the user input, retrieves the new expense information,
     * updates the expense record, and prints the changes.
     *
     * @throws KaChinnnngException If there is an error in executing the command, such as missing arguments,
     *                            incorrect index, or if the specified expense record does not exist.
     */
    @Override
    public void execute() throws KaChinnnngException  {
        assert expenses != null : "Incomes ArrayList must not be null";
        assert fullCommand != null : "Full command string must not be null";

        try {
            index = getIndex(fullCommand);
            ExpenseManager expenseCommand = new ExpenseManager(fullCommand);
            expenseCommand.execute();
            Expense newExpense = expenseCommand.getNewExpense();
            Expense removedExpense = expenses.get(index);
            expenses.set(index, newExpense);
            System.out.println("Noted. The expense record you've specified has been changed:");
            System.out.println("Before: " + removedExpense);
            System.out.println("After: " + newExpense);
        } catch (IndexOutOfBoundsException e) {
            throw new KaChinnnngException("Oops! Expense " + (index+1) + " does not exist");
        }
    }

    /**
     * Extracts and returns the index from the full command string.
     *
     * @param fullCommand The full command string.
     * @return The index of the income record to be edited.
     * @throws KaChinnnngException If there is an error in extracting the index, such as missing arguments
     *                            or if the index is not a valid integer.
     */
    private int getIndex(String fullCommand) throws KaChinnnngException {
        try {
            String[] tokens = fullCommand.split(" ", 4);
            assert tokens.length >= 4 : "Command must have at least 4 tokens";
            return Integer.parseInt(tokens[2])-1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KaChinnnngException("You're missing an argument");
        } catch (NullPointerException | NumberFormatException e) {
            throw new KaChinnnngException("Oops! An integer index is expected");
        }
    }
}
