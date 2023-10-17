package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.ui.Ui;
import seedu.cafectrl.ui.UserOutput;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    @Override
    public void execute(Menu menu, Ui ui) {
        ui.printLine();
        ui.showGoodbye();
    }
}
