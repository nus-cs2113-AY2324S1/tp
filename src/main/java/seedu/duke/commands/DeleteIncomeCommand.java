package seedu.duke.commands;

import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * The DeleteIncomeCommand class is responsible for deleting an income record from the list of incomes.
 */
public class DeleteIncomeCommand extends Commands {
    /**
     * Constructs a DeleteIncomeCommand object.
     */
    public DeleteIncomeCommand() {
    }

    /**
     * Executes the DeleteIncomeCommand to remove an income record from the list of incomes.
     *
     * @param incomes     The list of income records to be modified.
     * @param fullcommand The full command entered by the user.
     * @param ui          The user interface for displaying messages.
     * @throws KaChinnnngException If there is a problem with the command execution, such as missing arguments,
     *                            invalid index, or a non-existent income record.
     */
    public void execute(ArrayList<Income> incomes, String fullcommand, Ui ui) throws KaChinnnngException {
        int index = 0;
        try {
            String[] tokens = fullcommand.split(" ", 3);
            index = Integer.parseInt(tokens[2]);
            Income removedIncome = incomes.get(index-1);
            incomes.remove(index-1);
            System.out.println("Noted. This income record has been deleted:");
            System.out.println(removedIncome);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KaChinnnngException("You're missing an argument");
        } catch (NullPointerException | NumberFormatException e) {
            throw new KaChinnnngException("Oops! An integer index is expected");
        } catch (IndexOutOfBoundsException e) {
            throw new KaChinnnngException("Oops! Income " + index + " does not exist");
        }

    }
}
