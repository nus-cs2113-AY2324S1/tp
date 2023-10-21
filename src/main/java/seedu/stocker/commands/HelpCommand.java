package seedu.stocker.commands;


/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions. "
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(
                System.lineSeparator() + AddCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + DeleteCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + HelpCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + ListCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + FindCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + LoginCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + RegisterCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + SaveCommand.MESSAGE_USAGE
                + System.lineSeparator()
                + System.lineSeparator() + ExitCommand.MESSAGE_USAGE

        );
    }
}
