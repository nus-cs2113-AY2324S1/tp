package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;

//@@author ziyi105
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = "To view all commands:\n" + COMMAND_WORD;

    private static final Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    protected Ui ui;


    public HelpCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute() {
        logger.info("Executing HelpCommand...");

        ui.printLine();
        ui.showHelp();
    }
}
