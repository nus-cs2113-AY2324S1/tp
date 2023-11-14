package seedu.duke.commands.goal;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.goal.GoalList;
import seedu.duke.data.exception.IncorrectFormatException;
import seedu.duke.ui.TextUi;

public class ViewGoalCommand extends Command {

    public static final String COMMAND_WORD = "viewg";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List out the current goal list to see.\n"
            + "\tExample: " + COMMAND_WORD;
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
        } catch (Exception e) {
            feedbackToUser = "Something went wrong, please try again.";
        }

        return new CommandResult(feedbackToUser);
    }

}
