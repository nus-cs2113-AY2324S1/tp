package seedu.cafectrl.command;

import seedu.cafectrl.ui.Ui;

/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command{
    public final String feedbackToUser;
    protected Ui ui;

    public IncorrectCommand(String feedbackToUser, Ui ui) {
        this.feedbackToUser = feedbackToUser;
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.showToUser(feedbackToUser);
    }
}
