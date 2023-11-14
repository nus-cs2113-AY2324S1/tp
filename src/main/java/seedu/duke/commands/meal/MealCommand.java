package seedu.duke.commands.meal;

import java.util.List;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;

public class MealCommand extends Command {

    public MealCommand() {
        super();
    }

    public MealCommand(List<String> meals) {

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
