package seedu.duke.commands;

import seedu.duke.commands.goal.AchieveGoalCommand;
import seedu.duke.commands.goal.AchievementCommand;
import seedu.duke.commands.goal.GoalCommand;
import seedu.duke.commands.goal.ViewGoalCommand;
import seedu.duke.commands.logcommands.LogCommand;
import seedu.duke.commands.logcommands.ViewLogCommand;
import seedu.duke.commands.logcommands.UpdateLogCommand;
import seedu.duke.commands.logcommands.DeleteLogCommand;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        String HelpMsg = HelpCommand.MESSAGE_USAGE + "\n";
        HelpMsg += LogCommand.MESSAGE_USAGE + "\n";
        HelpMsg += DeleteLogCommand.MESSAGE_USAGE + "\n";
        HelpMsg += UpdateLogCommand.MESSAGE_USAGE + "\n";
        HelpMsg += ViewLogCommand.MESSAGE_USAGE + "\n";
        HelpMsg += GoalCommand.MESSAGE_USAGE + "\n";
        HelpMsg += GoalCommand.MESSAGE_USAGE + "\n";
        HelpMsg += DeleteLogCommand.MESSAGE_USAGE + "\n";
        HelpMsg += ViewGoalCommand.MESSAGE_USAGE + "\n";
        HelpMsg += AchieveGoalCommand.MESSAGE_USAGE + "\n";
        HelpMsg += AchievementCommand.MESSAGE_USAGE;

        return new CommandResult(
                HelpMsg

        //                        + "\n" + DeleteCommand.MESSAGE_USAGE
        //                        + "\n" + ClearCommand.MESSAGE_USAGE
        //                        + "\n" + FindCommand.MESSAGE_USAGE
        //                        + "\n" + ListCommand.MESSAGE_USAGE
        //                        + "\n" + ViewCommand.MESSAGE_USAGE
        //                        + "\n" + ViewAllCommand.MESSAGE_USAGE
        );
    }
}
