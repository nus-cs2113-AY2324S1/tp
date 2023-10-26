package fittrack.command;

import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;
import fittrack.storage.Storage;

public class InvalidCommand extends Command {
    public static final String MESSAGE_INVALID_COMMAND = "`%s` is an invalid command.";

    private String helpMessage;
    private String exceptionMessage = "";

    public InvalidCommand(String commandLine) {
        super(commandLine);
    }

    public InvalidCommand(String commandLine, ParseException e) {
        this(commandLine);
        if (e.getMessage() != null) {
            this.exceptionMessage = e.getMessage();
        }
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(helpMessage);
    }

    @Override
    public void setArguments(String inputLine, CommandParser parser) throws Storage.StorageOperationException {
        HelpCommand helpCommand = new HelpCommand(inputLine);
        helpCommand.setArguments(inputLine, parser);
        String message = helpCommand.execute().getFeedback();

        if (helpCommand.getCommandType() == InvalidCommand.class) {
            helpMessage = message;
        } else {
            String invalidCommandMessage = getInvalidCommandMessage(inputLine) + " " + this.exceptionMessage;
            helpMessage = invalidCommandMessage + "\n" + message;
        }
    }

    @Override
    protected String getHelp() {
        assert false;
        throw new UnsupportedOperationException();
    }

    static String getInvalidCommandMessage(String line) {
        return String.format(MESSAGE_INVALID_COMMAND, line);
    }
}
