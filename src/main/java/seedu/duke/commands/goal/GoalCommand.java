package seedu.duke.commands.goal;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.data.GoalList;
import seedu.duke.data.exception.IncorrectFormatException;


public class GoalCommand extends Command {

    public static final String COMMAND_WORD = "set";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a new goal into the goal list\n"
            + "\tExample: " + COMMAND_WORD + " 123 on 18/12/2023";
    public String feedbackToUser;


    public GoalCommand(String cmd) {
        super(cmd);
    }

    /**
     * The execution of goalCommand new a goal record into the goal list.
     * If error occurs in creating goal record, possibly includes
     * incorrect format of command or invalid number is input.
     * @return result of adding goal successfully message
     */
    @Override
    public CommandResult execute() {
        try {
            feedbackToUser = GoalList.addGoal(this.userCommand);
        } catch (IncorrectFormatException ife) {
            feedbackToUser = ife.getMessage();
        } catch (NumberFormatException nfe) {
            feedbackToUser = "Please input a valid number for calories.";
        } catch (Exception e) {
            feedbackToUser = "Sth. went wrong, please try again.";
        }

        return new CommandResult(feedbackToUser);
    }

}
