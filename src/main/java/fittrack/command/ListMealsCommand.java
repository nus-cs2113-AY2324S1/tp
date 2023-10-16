package fittrack.command;

import fittrack.parser.CommandParser;

public class ListMealsCommand extends Command {
    public static final String COMMAND_WORD = "listmeals";
    @Override
    public CommandResult execute() {
        return new CommandResult("These are the meals you have consumed: " + "\n" + mealList.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) { //TODO error handling

    }

    @Override
    protected String getHelp() {
        return null;
    }
}
