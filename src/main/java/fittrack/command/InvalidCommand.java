package fittrack.command;

import fittrack.parser.CommandParser;
import fittrack.parser.ParseException;

public class InvalidCommand extends Command {
    public static final String MESSAGE_INVALID_COMMAND = "`%s` is an invalid command.";

    private String helpMessage;
    private String exceptionMessage = "";

    public InvalidCommand() {
    }

    public InvalidCommand(ParseException e) {
        if (e.getMessage() != null) {
            this.exceptionMessage = e.getMessage();
        }
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(helpMessage);
    }

    @Override
    public void setArguments(String inputLine, CommandParser parser) {
        HelpCommand helpCommand = new HelpCommand();
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
