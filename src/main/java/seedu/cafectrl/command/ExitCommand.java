package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Overrides the isExit() method which returns false
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Menu menu, Ui ui, Pantry pantry) {
        ui.printLine();
        ui.showGoodbye();
        pantry.writeToPantryStorage();
    }
}
