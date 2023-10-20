package seedu.duke.commands.meal;

import java.util.ArrayList;

import seedu.duke.commands.Command;
import seedu.duke.data.meal.Meal;

public class MealCommand extends Command {
    protected static ArrayList<Meal> meals;

    public static void SetMeals(ArrayList<Meal> meals) {
        MealCommand.meals = meals;
    }

    protected void CheckArgument(String[] arguments, int validArgumentAmount) throws Exception {
        if (arguments == null || arguments.length != 2) {
            throw new Exception("Incorrect amount of the arguments.");
        }
    }
}
