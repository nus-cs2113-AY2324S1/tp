package seedu.cafectrl.command;

import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    protected Ui ui;
    protected Pantry pantry;

    public ExitCommand(Ui ui, Pantry pantry) {
        this.ui = ui;
        this.pantry = pantry;
    }

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
    public void execute() {
        ui.printLine();
        ui.showGoodbye();
    }
}
