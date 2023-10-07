package seedu.financialplanner.commands;

import seedu.financialplanner.utils.Ui;

public class Invalid extends Command {
    public Invalid() {
    }

    @Override
    public void execute(Ui ui) {
        ui.showMessage("\tUnknown command. Please try again.");
    }
}
