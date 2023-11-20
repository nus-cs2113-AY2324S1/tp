package seedu.duke.commands;

import seedu.duke.commands.goal.*;
import seedu.duke.commands.logcommands.LogCommand;
import seedu.duke.commands.logcommands.ViewLogCommand;
import seedu.duke.commands.logcommands.UpdateLogCommand;
import seedu.duke.commands.logcommands.DeleteLogCommand;
import seedu.duke.commands.meal.*;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        String helpMsg = HelpCommand.MESSAGE_USAGE + "\n";

        return new CommandResult(
                helpMsg
                        + "\n" + LogCommand.MESSAGE_USAGE
                        + "\n" + DeleteLogCommand.MESSAGE_USAGE
                        + "\n" + UpdateLogCommand.MESSAGE_USAGE
                        + "\n" + ViewLogCommand.MESSAGE_USAGE
                        + "\n" + DeleteLogCommand.MESSAGE_USAGE
                        + "\n" + ViewGoalCommand.MESSAGE_USAGE
                        + "\n" + AchieveGoalCommand.MESSAGE_USAGE
                        + "\n" + AchievementCommand.MESSAGE_USAGE
                        + "\n" + AddCommand.MESSAGE_USAGE
                        + "\n" + DeleteCommand.MESSAGE_USAGE
                        + "\n" + ListCommand.MESSAGE_USAGE
                        + "\n" + ExitCommand.MESSAGE_USAGE);
    }
}
