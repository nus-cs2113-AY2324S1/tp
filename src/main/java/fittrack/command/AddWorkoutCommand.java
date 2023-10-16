package fittrack.command;

import fittrack.Meal;
import fittrack.Workout;
import fittrack.WorkoutList;
import fittrack.parser.CommandParser;

public class AddWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "addworkout";

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
