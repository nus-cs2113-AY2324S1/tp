package seedu.cafectrl.command;

import seedu.cafectrl.ui.Ui;

//@@author ziyi105
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    protected Ui ui;

    public HelpCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.printLine();
        ui.showHelp();
    }
}
