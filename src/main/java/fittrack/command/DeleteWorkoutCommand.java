package fittrack.command;

import fittrack.data.Workout;
import fittrack.parser.CommandParser;
import fittrack.parser.IndexOutOfBoundsException;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;

public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "deleteworkout";
    private static final String DESCRIPTION =
            String.format("`%s` deletes your daily workout data from the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <INDEX>` to delete the workout by an index.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private int workoutIndex;

    public DeleteWorkoutCommand(String commandLine) {
        super(commandLine);
    }

    // @@author marklin2234
    @Override
    public CommandResult execute() {
        if (!workoutList.isIndexValid(workoutIndex)) {
            return new CommandParser()
                    .getInvalidCommand(commandLine, new IndexOutOfBoundsException())
                    .execute();
        }

        Workout toDelete = workoutList.getWorkout(workoutIndex);
        workoutList.deleteWorkout(workoutIndex);
        return new CommandResult("I've deleted the following workout:" + "\n" + toDelete.toString());
    }

    // @@author marklin2234
    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException, NumberFormatException {
        workoutIndex = parser.parseIndex(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
