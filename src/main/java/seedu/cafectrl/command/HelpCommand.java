package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.ui.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(Menu menu, Ui ui) {
        ui.printLine();
        ui.showHelp();
    }
}
