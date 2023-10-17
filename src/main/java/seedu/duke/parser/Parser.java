package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ListIngredientCommand;
import seedu.duke.command.ListMenuCommand;
import seedu.duke.command.AddDishCommand;
import seedu.duke.command.DeleteDishCommand;
import seedu.duke.command.IncorrectCommand;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser {
    public static final Pattern COMMAND_ARGUMENT_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    // Command Argument Patterns
    private static final String ADD_ARGUMENT_STRING = "name/([A-Za-z]+) price/(\\d+(\\.\\d+)?) (ingredient/[A-Za-z]+ qty/(\\d+(\\.\\d+)?)(?:, )?)+";
    private static final String LIST_INGREDIENTS_ARGUMENT_STRING = "(\\d+)";
    private static final String DELETE_ARGUMENT_STRING = "(\\d+)";
    private static final String EDIT_PRICE_ARGUMENT_STRING = "index/(\\d+) price/(\\d+(\\.\\d+)?)";

    /**
     * Parse userInput and group it under commandWord and arguments
     * use commandWord to find the matching command and prepare the command
     * @param userInput full user input
     * @return command requested by the user
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = COMMAND_ARGUMENT_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Incorrect command format!");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddDishCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case DeleteDishCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ListIngredientCommand.COMMAND_WORD:
            return prepareListIngredient(arguments);

        case ListMenuCommand.COMMAND_WORD:
            return prepareListMenu();

        default:
            return new IncorrectCommand("Incorrect command format!");
        }
    }

    private Command prepareListMenu() {
        // To be implemented by xx
        return null;
    }

    private Command prepareListIngredient(String arguments) {
        // To be implemented by xx
        return null;
    }

    private Command prepareDelete(String arguments) {
        // To be implemented by xx
        return null;
    }

    private Command prepareAdd(String arguments) {
        final Pattern ADD_ARGUMENT_PATTERN = Pattern.compile(ADD_ARGUMENT_STRING);
        Matcher matcher = ADD_ARGUMENT_PATTERN.matcher(arguments);

        // Checks whether the overall pattern of add arguments is correct
        if (matcher.matches()) {
            return new IncorrectCommand("Error: Missing arguments for the add command.");
        }

        try {
            // To retrieve specific arguments from arguments
            String dishName = matcher.group(1);
            float price = Float.parseFloat(matcher.group(2));

            // Capture the list of ingredients and quantities
            ArrayList<String> ingredients = new ArrayList<>();
            ArrayList<String> quantities = new ArrayList<>();

            // Find all matches for ingredients and quantities
            Pattern ingredientPattern = Pattern.compile("ingredient/([A-Za-z]+) qty/([A-Za-z]+)");
            Matcher ingredientMatcher = ingredientPattern.matcher(arguments);

            while (ingredientMatcher.find()) {
                String ingredient = ingredientMatcher.group(1);
                String quantity = ingredientMatcher.group(2);
                ingredients.add(ingredient);
                quantities.add(quantity);
            }

            // Todo: Implement error handling for checking the size of ingredients quantities
            // I am not sure if this is necessary as we have already checked
            // the overall command pattern in line 62

            // Todo: Add the attributes in AddDishCommand
            // Todo: Overload the constructor of Dish such that
            // it can take in ingredients list and quantities list
            // and create an arrayList of ingredient objects
            // return new AddDishCommand(dishName, price, ingredients, quantities);
        } catch (Exception e) {
            // Todo: Add error handling for invalid price type etc.
        }

        return new IncorrectCommand("The specific details are " +
                "to be implemented by Dexter");
    }
}
