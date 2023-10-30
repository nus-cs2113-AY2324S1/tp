package quizhub.command;
/**
 * Represents a command to exit the program.
 */
public class CommandExit extends Command {
    public static final String COMMAND_WORD = "bye";
    /**
     * Creates a new exit command to terminate program.
     */
    public CommandExit() {
        super(CommandType.EXIT);
    }
}
