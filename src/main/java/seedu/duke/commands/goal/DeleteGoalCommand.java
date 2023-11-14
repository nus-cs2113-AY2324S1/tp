package seedu.duke.commands.goal;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.data.GoalList;
import seedu.duke.data.exception.IncorrectFormatException;

import java.io.IOException;

public class DeleteGoalCommand extends Command {
    public static final String COMMAND_WORD = "deleteg";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete an goal from the current goal list.\n"
            + "\tExample: " + COMMAND_WORD + " 1";
    public String feedbackToUser;

    public DeleteGoalCommand(String cmd) {
        super(cmd);
    }

    /**
     * execute to remove a goal object from global goal list, by indexing
     * If failed to delete a goal, tells user the specific problem.
     * @return feedback to user of either success or fail
     */
    @Override
    public CommandResult execute() {
        try {
            feedbackToUser = GoalList.deleteGoal(this.userCommand);

        } catch (NumberFormatException nfe) {
            feedbackToUser = "Please input a valid number for delete index.";
        } catch (IncorrectFormatException ife) {
            feedbackToUser = ife.getMessage();
        } catch (IOException io) {
            feedbackToUser = "Failed to save data. Please check the output file and restart the app.";
        } catch (Exception e) {
            feedbackToUser = "Something went wrong, please try again.";
        }


        return new CommandResult(feedbackToUser);
    }


}
