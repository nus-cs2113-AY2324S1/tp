package seedu.cafectrl.parser;

import seedu.cafectrl.command.AddDishCommand;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.command.DeleteDishCommand;
import seedu.cafectrl.command.ExitCommand;
import seedu.cafectrl.command.IncorrectCommand;
import seedu.cafectrl.command.ListIngredientCommand;
import seedu.cafectrl.command.ListMenuCommand;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser {
    public static final Pattern COMMAND_ARGUMENT_FORMAT = Pattern.compile("(?<commandWord>\\S+) (?<arguments>.*)");

    // Command Argument Patterns
//    private static final String ADD_ARGUMENT_STRING = "add name/(\\w+) price/(\\d+(\\.\\d+)?) (ingredient/\\w+ qty/\\d+(\\.\\d+)?(?:, )?)+";
    private static final String ADD_ARGUMENT_STRING = "name/([A-Za-z0-9\\s]+) price/([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))? (ingredient/[A-Za-z0-9\\s]+ qty/[A-Za-z0-9\\s]+(?:, ingredient/[A-Za-z0-9\\s]+ qty/[A-Za-z0-9\\s]+)*)";
    private static final String LIST_INGREDIENTS_ARGUMENT_STRING = "(\\d+)";
    private static final String DELETE_ARGUMENT_STRING = "(\\d+)";
    private static final String EDIT_PRICE_ARGUMENT_STRING = "index/(\\d+) price/(\\d+(\\.\\d+)?)";

    /**
     * Parse userInput and group it under commandWord and arguments
     * use commandWord to find the matching command and prepare the command
     *
     * @param userInput full user input
     * @param menu The arraylist object created that stores current tasks
     * @return command requested by the user
     */
    public static Command parseCommand(Menu menu, String userInput) {
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

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            return new IncorrectCommand("Your command has left me scratching my virtual head. " +
                    "Let's try that again, shall we?");
        }
    }

    // All prepareCommand Classes
    private static Command prepareListMenu() {
        // To be implemented by xx
        return null;
    }

    private static Command prepareAdd(String arguments) {
        final Pattern addArgumentPattern = Pattern.compile(ADD_ARGUMENT_STRING);
        Matcher matcher = addArgumentPattern.matcher(arguments);

        // Checks whether the overall pattern of add arguments is correct
        if (!matcher.matches()) {
            return new IncorrectCommand("Error: Incorrect format for the add command.");
        }

        try {
            // To retrieve specific arguments from arguments
            String dishName = matcher.group(1);
            float price = Float.parseFloat(matcher.group(2));
            String ingredientsListString = matcher.group(4);

            // Capture the list of ingredients
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            String[] ingredientListInputText = {ingredientsListString};

            //check if there is more than 1 ingredient
            if (ingredientsListString.contains(",")) {
                //split the ingredients into separate individual ingredients
                ingredientListInputText = ingredientsListString.split(", ");
            }

            for (String inputIngredientText: ingredientListInputText) {
                final Pattern ingredientPattern = Pattern.compile("ingredient/(?<name>[A-Za-z0-9\\s]+) qty/(?<qty>[A-Za-z0-9\\s]+)");
                Matcher ingredientMatcher = ingredientPattern.matcher(inputIngredientText);

                if (!ingredientMatcher.matches()) {
                    return new IncorrectCommand("Error: Incorrect format for the ingredients");
                }

                String ingredientName = ingredientMatcher.group("name");
                String ingredientQty = ingredientMatcher.group("qty");

                Ingredient ingredient = new Ingredient(ingredientName, ingredientQty);

                ingredients.add(ingredient);
            }

            Dish dish = new Dish(dishName, ingredients, price);
            return new AddDishCommand(dish);
        } catch (Exception e) {
            // Todo: Add error handling for invalid price type etc.
            return new IncorrectCommand("MESSAGE_INVALID_ADD_COMMAND_FORMAT");
        }


    }
  
    /**
    * Parses arguments in the context of the ListIngredient command.
    * @param userInput arguments string to parse as index number
    * @return the prepared command
    */
    private static Command prepareListIngredient(String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(userInput, ListIngredientCommand.COMMAND_WORD);
            return new ListIngredientCommand(listIndex);
        } catch (ParseException e) {
            return new IncorrectCommand("MESSAGE_INVALID_COMMAND_FORMAT" + ListIngredientCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("MESSAGE_INVALID_TASK_DISPLAYED_INDEX");
        }
    }

    /**
     * Parses arguments in the context of the Delete command.
     *
     * @param userInput Input from the user
     * @return Command to be executed
     */
    private static Command prepareDelete(String userInput) {
        try {
            final int listIndex = parseArgsAsDisplayedIndex(userInput, DeleteDishCommand.COMMAND_WORD);
            return new DeleteDishCommand();
        } catch (ParseException e) {
            return new IncorrectCommand("MESSAGE_INVALID_COMMAND_FORMAT" + DeleteDishCommand.MESSAGE_USAGE);
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand("MESSAGE_INVALID_TASK_DISPLAYED_INDEX");
        }
    }

    /**
     * Parses the given arguments string to identify task index number.
     *
     * @param userInput arguments string to parse as index number
     * @param command expected String name of the command called
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private static int parseArgsAsDisplayedIndex(String userInput, String command)
            throws ParseException, NumberFormatException {
        String formattedString = userInput.replace(command, "").trim();
        return Integer.parseInt(formattedString);
    }
}
