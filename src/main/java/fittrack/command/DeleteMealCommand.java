package fittrack.command;

import fittrack.data.Meal;
import fittrack.parser.*;
import fittrack.parser.IndexOutOfBoundsException;
import fittrack.parser.NumberFormatException;

public class DeleteMealCommand extends Command {
    public static final String COMMAND_WORD = "deletemeal";
    private static final String DESCRIPTION =
            String.format("`%s` deletes your daily meal data from the list.", COMMAND_WORD);
    private static final String USAGE =
            String.format("Type `%s <INDEX>` to delete the meal by an index.", COMMAND_WORD);
    public static final String HELP = DESCRIPTION + "\n" + USAGE;

    private int mealIndex;

    @Override
    public CommandResult execute() {

        try {
            Meal toDelete = mealList.getMeal(mealIndex);
            mealList.deleteMeal(mealIndex);
            return new CommandResult("I've deleted the following meal:" + "\n" + toDelete.toString());
        } catch (java.lang.IndexOutOfBoundsException | IndexOutOfBoundsException e) {
            return new CommandResult("This is invalid, meal needs to be in list!");
        }

    }

    @Override
    public void setArguments(String args, CommandParser parser)
            throws PatternMatchFailException, NumberFormatException, IndexOutOfBoundsException, NegativeNumberException {
        mealIndex = parser.parseDeleteMeal(args);
    }

    @Override
    protected String getHelp() {
        return HELP;
    }
}
