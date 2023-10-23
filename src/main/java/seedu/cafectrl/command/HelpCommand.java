package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(Menu menu, Ui ui, Pantry pantry) {
        ui.printLine();
        ui.showHelp();
    }
}
