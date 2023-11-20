package seedu.duke.commands.meal;

import seedu.duke.commands.CommandResult;
import seedu.duke.meal.Category;
import seedu.duke.meal.CategoryParser;
import seedu.duke.meal.Meal;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends MealCommand {
    public static final String COMMAND_WORD = "meal_list";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all the meals that have been recorded.\n"
            + "\tExample: " + COMMAND_WORD;
    private static final int[] validArgumentAmounts = new int[] { 0, 1 };
    private final Category category;

    public ListCommand(List<String> arguments) throws Exception {
        checkArgument(arguments, validArgumentAmounts);
        if (arguments.size() == 1 && arguments.get(0) != "") {
            category = CategoryParser.Parse(arguments.get(0));
        } else {
            category = null;
        }
    }

    @Override
    public CommandResult execute() throws Exception {
        ArrayList<Meal> selectedMeals = new ArrayList<Meal>();
        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            if (category == null || meal.category == category) {
                selectedMeals.add(meal);
            }
        }

        if (selectedMeals.size() == 0) {
            if (category == null) {
                return new CommandResult("The meal list is empty!");
            }
            return new CommandResult("The selected meal list with category '" + category.toString() + "' is empty!");
        }
        String listString = "";
        int total = 0;
        for (int i = 0; i < selectedMeals.size(); i++) {
            Meal meal = selectedMeals.get(i);
            listString += "\n" + (i + 1) + "." + meal.toString();
            total += meal.calories;
        }
        listString += "\nTotal calories: " + total;
        return new CommandResult(
                (category == null ? "Here's the meal list:"
                        : "Here's the selected meal list with category '" + category.toString() + "':") + listString);
    }
}