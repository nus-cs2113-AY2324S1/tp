package seedu.cafectrl.command;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.ui.Ui;

public class ExitCommand extends Command{
    public static final String COMMAND_WORD = "bye";
    protected static boolean isExit = false;

    @Override
    public void execute(Menu menu, Ui ui) {
        isExit = true;
    }

    public static boolean isExit() {
        return isExit;
    }
}
