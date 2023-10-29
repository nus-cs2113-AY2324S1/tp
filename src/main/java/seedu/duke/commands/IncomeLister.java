package seedu.duke.commands;

import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;

import java.io.File;
import java.util.ArrayList;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

/**
 * Represents the command that when executed, lists all incomes.
 * This class is a child class of the Command class.
 *
 */
public class IncomeLister extends Commands {
    private static final Logger LOGGER = Logger.getLogger(IncomeLister.class.getName());
    private final ArrayList<Income> incomes;
    private final Ui ui;

    static {
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/IncomeLister.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }

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

        LOGGER.log(Level.INFO, ("Initialised IncomeLister"));
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
            Ui.showLineDivider();
            ui.printMessage("You have no recorded incomes.");
            Ui.showLineDivider();
            return;
        }

        Ui.showLineDivider();
        ui.printMessage("Here are your incomes:");
        for (int i = 0; i < incomes.size(); i++) {
            ui.printMessage((i + 1) + ". " + incomes.get(i).toString());
        }
        Ui.showLineDivider();
        LOGGER.log(Level.INFO, ("Incomes listed successfully"));
    }
}
