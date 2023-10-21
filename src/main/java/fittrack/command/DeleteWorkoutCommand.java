package fittrack.command;

import fittrack.Workout;
import fittrack.WorkoutList;
import fittrack.parser.CommandParser;

public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "deleteworkout";
    private static final String DESCRIPTION =
            String.format("`%s` deletes your daily workout data from the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <INDEX>` to delete the workout by an index.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private int index;

    @Override
    public CommandResult execute() {
        Workout toDelete = workoutList.getWorkout(index);
        WorkoutList.deleteWorkout(index);
        return new CommandResult("I've deleted the following workout:" + "\n" + toDelete.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        index = Integer.parseInt(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
