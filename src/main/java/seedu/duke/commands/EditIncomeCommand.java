package seedu.duke.commands;

import seedu.duke.financialrecords.Income;

import java.util.ArrayList;


public class EditIncomeCommand extends Commands {

    ArrayList<Income> incomes;
    String fullCommand;

    int index;

    /**
     * Constructs an EditIncomeCommand with the specified income records and full command.
     *
     * @param incomes      The ArrayList of Income objects containing income records.
     * @param fullCommand  The full command string entered by the user.
     */
    public EditIncomeCommand(ArrayList<Income> incomes, String fullCommand) {
        this.incomes = incomes;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the edit income command. It parses the user input, retrieves the new income information,
     * updates the income record, and prints the changes.
     *
     * @throws KaChinnnngException If there is an error in executing the command, such as missing arguments,
     *                            incorrect index, or if the specified income record does not exist.
     */
    @Override
    public void execute() throws KaChinnnngException  {
        assert incomes != null : "Incomes ArrayList must not be null";
        assert fullCommand != null : "Full command string must not be null";

        try {
            index = getIndex(fullCommand);
            IncomeManager incomeCommand = new IncomeManager(fullCommand);
            incomeCommand.execute();
            Income newIncome = incomeCommand.getNewIncome();
            Income removedIncome = incomes.get(index);
            incomes.set(index, newIncome);
            System.out.println("Noted. The income record you've specified has been changed:");
            System.out.println("Before: " + removedIncome);
            System.out.println("After: " + newIncome);
        } catch (IndexOutOfBoundsException e) {
            throw new KaChinnnngException("Oops! Income " + (index+1) + " does not exist");
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
