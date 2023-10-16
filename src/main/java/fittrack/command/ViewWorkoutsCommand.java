package fittrack.command;

import fittrack.parser.CommandParser;

public class ViewWorkoutsCommand extends Command {
    public static final String COMMAND_WORD = "viewWorkouts";
    private static final String DESCRIPTION =
            String.format("'%s' shows the list of all workouts.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type '%s' to view the list of your workouts.", COMMAND_WORD);
    private static final String HELP = DESCRIPTION + "\n" + USAGE;

    /**
     * Execute the command
     * @return list of workouts
     */
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

