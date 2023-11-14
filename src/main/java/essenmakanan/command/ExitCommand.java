package essenmakanan.command;

/**
 * Represents an exit command.
 */
public class ExitCommand extends Command {

    /**
     * Creates an exit command.
     */
    public ExitCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand() {
        ui.bye();
    }

    /**
     * Checks if it is an exit command.
     *
     * @param command The given command
     * @return Confirmation if the command is an exit command.
     */
    public static boolean isExitCommand(Command command) {
        return (command instanceof ExitCommand);
    }
}
