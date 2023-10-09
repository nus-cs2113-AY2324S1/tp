package seedu.stocker.commands;

public class FindCommand  extends Command{

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds drug in inventory \n"
            + "Example: " + COMMAND_WORD + " panadol";

    //edit function to give find functionality
    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE
                        + "\n" + HelpCommand.MESSAGE_USAGE
                        + "\n" + ListCommand.MESSAGE_USAGE
                        + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
