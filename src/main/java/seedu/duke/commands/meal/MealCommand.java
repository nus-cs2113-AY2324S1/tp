package seedu.duke.commands.meal;

import java.util.List;
import java.util.ArrayList;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.data.meal.Meal;

public class MealCommand extends Command {
    public static final String COMMAND_WORD = "test";
    protected static ArrayList<Meal> meals;

    public MealCommand() {
        super();
    }

    public MealCommand(List<String> meals) {

    }

    public static void setMeals(List<Meal> meals) {
        MealCommand.meals = new ArrayList<>(meals);
    }

    protected void checkArgument(String[] arguments, int validArgumentAmount) throws Exception {
        if (arguments == null || arguments.length != 2) {
            throw new Exception("Incorrect amount of the arguments.");
        }
    }

    @Override
    public CommandResult execute() throws Exception {
        throw new UnsupportedOperationException("meal command");
    }
}
