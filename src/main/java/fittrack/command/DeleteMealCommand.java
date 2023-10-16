package fittrack.command;

import fittrack.Meal;
import fittrack.MealList;
import fittrack.parser.CommandParser;

public class DeleteMealCommand extends Command {
    public static final String COMMAND_WORD = "deletemeal";

    private int mealIndex;

    @Override
    public CommandResult execute() {
        Meal toDelete = mealList.getMeal(mealIndex);
        MealList.deleteMeal(mealIndex);
        return new CommandResult("I've deleted the following meal:" + "\n" + toDelete.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) {
        // TODO: Try to make parse method in CommandParser and
        // TODO: use the method by parser.parseXXX();
        // TODO: Refer to CommandParser.parseProfile().

        mealIndex = Integer.parseInt(args);
    }

    @Override
    protected String getHelp() {
        // TODO: Write help. Refer to HelpCommand or ViewMealsCommand.
        return null;
    }
}
