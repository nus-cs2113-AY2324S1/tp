package fittrack.command;

import fittrack.parser.CommandParser;


public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    private static final String DESCRIPTION = "`" + COMMAND_WORD + "` makes you to exit this program.";
    private static final String USAGE = "Type `exit` to exit.";
    public static final String HELP = DESCRIPTION + "\n" + USAGE;
    private static final String MESSAGE_EXIT = "Goodbye! Hope to see you soon!";

    public ExitCommand(String commandLine) {
        super(commandLine);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT);
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
