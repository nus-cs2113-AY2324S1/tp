package seedu.duke.commands.goal;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.data.GoalList;
import seedu.duke.data.exception.IncorrectFormatException;
import seedu.duke.ui.TextUi;

public class AchievementCommand extends Command {
    public static final String COMMAND_WORD = "achievement";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Show all your achievements\n"
            + "\tExample: " + COMMAND_WORD;
    public String feedbackToUser;

    public AchievementCommand(String cmd) {
        super(cmd);
    }

    @Override
    public CommandResult execute() {
        try {
            GoalList.verifyViewAchievementInput(this.userCommand);
            feedbackToUser = TextUi.showAchievement();

        } catch (IncorrectFormatException ife) {
            feedbackToUser = ife.getMessage();
        } catch (Exception e) {
            feedbackToUser = "Something went wrong, please try again.";
        }

        return new CommandResult(feedbackToUser);
    }


}
