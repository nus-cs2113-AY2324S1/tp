package seedu.cafectrl.command;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.ui.Ui;

import java.util.logging.Logger;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command{
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());
    public final String feedbackToUser;
    protected Ui ui;

    public IncorrectCommand(String feedbackToUser, Ui ui) {
        this.feedbackToUser = feedbackToUser;
        this.ui = ui;
    }

    @Override
    public void execute() {
        logger.warning("Executing IncorrectCommand: " + feedbackToUser);
        ui.showToUser(feedbackToUser);
    }
}
