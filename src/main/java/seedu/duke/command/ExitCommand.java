package seedu.duke.command;

import seedu.duke.data.Menu;
import seedu.duke.ui.Ui;

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
