package seedu.duke.commands.goal;

import seedu.duke.commands.Command;

public class ViewGoalCommand extends Command {

    public static final String COMMAND_WORD = "viewg";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List out the current goal list to see.\n"
            + "\tExample: " + COMMAND_WORD;
    public String feedbackToUser;

    public ViewGoalCommand(String cmd) {
        super(cmd);
    }
}
