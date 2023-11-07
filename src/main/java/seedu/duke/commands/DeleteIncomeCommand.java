package seedu.duke.commands;

import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * The DeleteIncomeCommand class is responsible for deleting an income record from the list of incomes.
 */
public class DeleteIncomeCommand extends Command {
    /**
     * Constructs a DeleteIncomeCommand object.
     */
    public DeleteIncomeCommand() {
    }

    /**
     * Executes the DeleteIncomeCommand to remove an income record from the list of incomes.
     *
     * @param incomes     The list of income records to be modified.
     * @param fullCommand The full command entered by the user.
     * @throws KaChinnnngException If there is a problem with the command execution, such as missing arguments,
     *                            invalid index, or a non-existent income record.
     */
    public void execute(ArrayList<Income> incomes, String fullCommand) throws KaChinnnngException {
        int index = 0;
        try {
            String[] tokens = fullCommand.split(" ", 3);
            index = Integer.parseInt(tokens[2])-1;
            Income removedIncome = incomes.get(index);
            incomes.remove(index);
            Ui.showLineDivider();
            System.out.println("Noted. This income record has been deleted:");
            System.out.println(removedIncome);
            Ui.showLineDivider();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new KaChinnnngException("You're missing an argument");
        } catch (NullPointerException | NumberFormatException e) {
            throw new KaChinnnngException("Oops! An integer index is expected");
        } catch (IndexOutOfBoundsException e) {
            throw new KaChinnnngException("Oops! Income " + (index+1) + " does not exist");
        }

    }
}
