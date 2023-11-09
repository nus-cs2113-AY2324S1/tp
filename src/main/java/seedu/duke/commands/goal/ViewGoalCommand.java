package seedu.duke.commands.goal;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.data.GoalList;
import seedu.duke.data.exception.IncorrectFormatException;
import seedu.duke.ui.TextUi;

public class ViewGoalCommand extends Command {

    public static final String COMMAND_WORD = "viewg";
    public String feedbackToUser;

    public ViewGoalCommand(String cmd) {
        super(cmd);
    }

    @Override
    public CommandResult execute() {
        try {
            GoalList.verifyViewGoalInput(this.userCommand);
            feedbackToUser = TextUi.showGoalList();
        } catch (IncorrectFormatException ife) {
            feedbackToUser = ife.getMessage();
        }

        return new CommandResult(feedbackToUser);
    }

}
