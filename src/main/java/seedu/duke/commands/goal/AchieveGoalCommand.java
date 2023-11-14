package seedu.duke.commands.goal;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;

import seedu.duke.goal.GoalList;
import seedu.duke.data.exception.IncorrectFormatException;

public class AchieveGoalCommand extends Command {
    public static final String COMMAND_WORD = "achieve";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Achieve a goal from the current goal list.\n"
            + "\tExample: " + COMMAND_WORD + " 2";
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
        } catch (Exception e) {
            feedbackToUser = "Something went wrong, please try again.";
        }

        return new CommandResult(feedbackToUser);
    }

}
