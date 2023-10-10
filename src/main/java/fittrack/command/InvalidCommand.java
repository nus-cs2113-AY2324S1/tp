package fittrack.command;

import fittrack.parser.CommandParser;

public class InvalidCommand extends Command {
    private final String inputWord;
    private HelpCommand helpCommand;

    public InvalidCommand(String inputWord) {
        this.inputWord = inputWord;
    }

    @Override
    public CommandResult execute() {
        return helpCommand.execute();
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        helpCommand = new HelpCommand();
        helpCommand.setArguments(inputWord, parser);
    }

    @Override
    protected String getHelp() {
        throw new UnsupportedOperationException();
    }
}
