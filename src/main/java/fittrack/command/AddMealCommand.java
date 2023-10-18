package fittrack.command;

import fittrack.Meal;
import fittrack.MealList;
import fittrack.parser.CommandParser;
import fittrack.parser.NumberFormatException;
import fittrack.parser.PatternMatchFailException;

public class AddMealCommand extends Command {
    public static final String COMMAND_WORD = "addmeal";

    private static final String DESCRIPTION =
            "Add in your meals and their calories!";

    private static final String USAGE =
            String.format("Type `%s` <mealName> c/<calories> to add your meal.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private Meal newMeal;

    @Override
    public CommandResult execute() {
        MealList.addToList(newMeal);
        return new CommandResult("I've added the following meal:" + "\n" + newMeal.toString());
    }

    @Override
    public void setArguments(String args, CommandParser parser) throws NumberFormatException, PatternMatchFailException {

        newMeal = parser.parseAddMeal(args);

    }

    @Override
    protected String getHelp() {
        // TODO: Write help. Refer to HelpCommand or ViewMealsCommand.
        return HELP;
    }
}
