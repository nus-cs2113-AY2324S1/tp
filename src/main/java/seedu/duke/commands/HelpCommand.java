package seedu.duke.commands;

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
        return new CommandResult(
                HelpCommand.MESSAGE_USAGE + "\n" + LogCommand.MESSAGE_USAGE + "\n" +
                        DeleteLogCommand.MESSAGE_USAGE + "\n" + UpdateLogCommand.MESSAGE_USAGE + "\n" +
                ViewLogCommand.MESSAGE_USAGE
        //                        + "\n" + DeleteCommand.MESSAGE_USAGE
        //                        + "\n" + ClearCommand.MESSAGE_USAGE
        //                        + "\n" + FindCommand.MESSAGE_USAGE
        //                        + "\n" + ListCommand.MESSAGE_USAGE
        //                        + "\n" + ViewCommand.MESSAGE_USAGE
        //                        + "\n" + ViewAllCommand.MESSAGE_USAGE
        );
    }
}
