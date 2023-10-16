package fittrack.command;

import fittrack.parser.CommandParser;

public class ViewDailyCalorieSurplusLimitCommand extends Command {
    public static final String COMMAND_WORD = "viewlimit";

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
