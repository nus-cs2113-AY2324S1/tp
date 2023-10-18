package fittrack.command;

import fittrack.Workout;
import fittrack.WorkoutList;
import fittrack.parser.CommandParser;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;

public class AddWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "addworkout";
    private static final String DESCRIPTION =
            String.format("`%s` adds your daily workout data to the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <WORKOUT_NAME> c/ <CALORIES>` to add a workout.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Workout newWorkout;

    @Override
    public CommandResult execute() {
        WorkoutList.addToList(newWorkout);
        return new CommandResult("I've added the following workout:" + "\n" + newWorkout.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException, NumberFormatException {
        newWorkout = parser.parseWorkout(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
