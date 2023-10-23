package seedu.duke.commands;

import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class DeleteIncomeCommand extends Commands {
    public DeleteIncomeCommand() {
    }

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
