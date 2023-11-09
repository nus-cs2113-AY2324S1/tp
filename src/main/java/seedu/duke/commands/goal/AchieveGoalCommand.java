package seedu.duke.commands.goal;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;

import seedu.duke.data.GoalList;
import seedu.duke.data.exception.IncorrectFormatException;

public class AchieveGoalCommand extends Command {
    public static final String COMMAND_WORD = "achieve";
    public String feedbackToUser;

    public AchieveGoalCommand(String cmd) {
        super(cmd);
    }

    @Override
    public CommandResult execute() {
        try {
            feedbackToUser = GoalList.achieveGoal(this.userCommand);

        } catch (NumberFormatException nfe) {
            feedbackToUser = "Please use a valid arabic number as index.";
        } catch (IncorrectFormatException ife) {
            feedbackToUser = ife.getMessage();
        }

        return new CommandResult(feedbackToUser);
    }

}
