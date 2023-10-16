package fittrack.command;

import fittrack.parser.CommandParser;

public class InvalidCommand extends Command {
    private final String inputLine;
    private HelpCommand helpCommand;

    public InvalidCommand(String inputLine) {
        this.inputLine = inputLine;
    }

    @Override
    public CommandResult execute() {
        return helpCommand.execute();
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        helpCommand = new HelpCommand();
        helpCommand.setArguments(inputLine, parser);
    }

    @Override
    protected String getHelp() {
        throw new UnsupportedOperationException();
    }
}
