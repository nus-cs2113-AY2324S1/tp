package seedu.duke.commands.meal;

import java.util.List;
import java.util.ArrayList;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.data.meal.Meal;

public class MealCommand extends Command {
    protected static ArrayList<Meal> meals;

    public MealCommand() {
        super();
    }

    public MealCommand(List<String> meals) {

    }

    public static void setMeals(ArrayList<Meal> meals) {
        MealCommand.meals = meals;
    }

    protected void checkArgument(List<String> arguments, int[] validArgumentAmounts) throws Exception {
        Boolean isValid = false;
        if (arguments != null) {
            int size = arguments.size();
            if (arguments.get(0) == "") {
                size = 0;
            }
            for (int i = 0; i < validArgumentAmounts.length; i++) {
                if (size == validArgumentAmounts[i]) {
                    isValid = true;
                    break;
                }
            }
        }

        if (!isValid) {
            throw new Exception("Incorrect amount of the arguments.");
        }
    }

    @Override
    public CommandResult execute() throws Exception {
        throw new UnsupportedOperationException("Meal command that hasn't been implemented.");
    }
}
