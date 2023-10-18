package seedu.duke.commands;

import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;
import java.util.ArrayList;

import java.util.logging.Logger;

/**
 * Represents the command that when executed, lists all incomes.
 * This class is a child class of the Command class.
 *
 */
public class IncomeLister extends Commands {
    private static final Logger LOGGER = Logger.getLogger(IncomeLister.class.getName());
    private final ArrayList<Income> incomes;
    private final Ui ui;

    /**
     * Constructor for IncomeLister.
     *
     * @param incomes ArrayList of incomes
     * @param ui      Instance of Ui
     */
    public IncomeLister(ArrayList<Income> incomes, Ui ui) {
        assert incomes != null : "incomes should not be null";
        assert ui != null : "ui should not be null";

        this.incomes = incomes;
        this.ui = ui;

        LOGGER.info("IncomeLister initialised successfully");
    }

    /**
     * This method is used to execute the command.
     */
    @Override
    public void execute() {
        listIncomes();
    }

    /**
     * This method lists all incomes.
     */
    // Updated method name to follow Java naming conventions
    public void listIncomes() {
        if (incomes.isEmpty()) {
            ui.showLineDivider();
            System.out.println("You have no recorded incomes.");
            ui.showLineDivider();
            return;
        }

        ui.showLineDivider();
        System.out.println("Here are your incomes:");
        for (int i = 0; i < incomes.size(); i++) {
            System.out.println((i + 1) + ". " + incomes.get(i).toString());
        }
        ui.showLineDivider();
        LOGGER.info("Incomes listed successfully");
    }
}
