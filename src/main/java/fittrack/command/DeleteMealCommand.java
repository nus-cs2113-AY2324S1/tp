package fittrack.command;

import fittrack.Meal;
import fittrack.MealList;
import fittrack.parser.CommandParser;

public class DeleteMealCommand extends Command {
    public static final String COMMAND_WORD = "deletemeal";
    int mealIndex;

    @Override
    public CommandResult execute() {
        Meal toDelete = mealList.getMeal(mealIndex);
        MealList.deleteMeal(mealIndex);
        return new CommandResult("I've deleted the following meal:" + "\n" + toDelete.toString());

    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        mealIndex = Integer.parseInt(args);

    }

    @Override
    protected String getHelp() {
        return null;
    }
}
