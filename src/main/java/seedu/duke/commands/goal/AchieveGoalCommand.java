package seedu.duke.commands.goal;

import seedu.duke.commands.Command;

public class AchieveGoalCommand extends Command {
    public static final String COMMAND_WORD = "achieve";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Achieve a goal from the current goal list.\n"
            + "\tExample: " + COMMAND_WORD + " 2";
    public String feedbackToUser;

    public AchieveGoalCommand(String cmd) {
        super(cmd);
    }
}
