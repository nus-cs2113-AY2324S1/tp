package fittrack.command;

import fittrack.Meal;
import fittrack.MealList;
import fittrack.parser.CommandParser;

public class AddMealCommand extends Command {
    public static final String COMMAND_WORD = "addmeal";

    Meal newMeal;

    @Override
    public CommandResult execute() {
        MealList.addToList(newMeal);
        return new CommandResult("I've added the following meal:" + "\n" + newMeal.toString());
    }

    @Override

    public void setArguments(String args, CommandParser parser) {
        // why is there a need for a command parser,
        // a argument parser makes more sense here since command is already known
        // TODO error handling
        String[] mealArgs = args.split("/cals");
        newMeal = new Meal(mealArgs[0], Float.parseFloat(mealArgs[1]));

    }

    @Override
    protected String getHelp() {
        return null;
    }
}
