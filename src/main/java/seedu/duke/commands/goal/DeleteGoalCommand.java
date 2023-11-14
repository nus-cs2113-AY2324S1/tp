package seedu.duke.commands.goal;

import seedu.duke.commands.Command;

public class DeleteGoalCommand extends Command {
    public static final String COMMAND_WORD = "deleteg";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete an goal from the current goal list.\n"
            + "\tExample: " + COMMAND_WORD + " 1";
    public String feedbackToUser;

    public DeleteGoalCommand(String cmd) {
        super(cmd);
    }
}
