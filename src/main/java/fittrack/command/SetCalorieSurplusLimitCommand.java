package fittrack.command;

import fittrack.parser.CommandParser;

public class SetCalorieSurplusLimitCommand extends Command {
    public static final String COMMAND_WORD = "setlimit";

    @Override
    public CommandResult execute() {
        return null;
    }

    @Override
    public void setArguments(String args, CommandParser parser) {

    }

    @Override
    protected String getHelp() {
        return null;
    }
}
