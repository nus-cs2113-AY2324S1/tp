package seedu.cafectrl.parser;

import seedu.cafectrl.command.AddDishCommand;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.command.IncorrectCommand;
import seedu.cafectrl.command.DeleteDishCommand;
import seedu.cafectrl.command.EditPriceCommand;
import seedu.cafectrl.command.ExitCommand;
import seedu.cafectrl.command.ListIngredientCommand;
import seedu.cafectrl.command.ListMenuCommand;

import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.ui.UserOutput;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser {
    public static final Pattern COMMAND_ARGUMENT_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    // Command Argument Patterns
    private static final String ADD_ARGUMENT_STRING = "name/(\\w+) price/(\\d+(\\.\\d+)?)"
                                                        + " (ingredient/\\w+ qty/\\d+(\\.\\d+)?(?:, )?)+";
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

        case EditPriceCommand.COMMAND_WORD:
            return prepareEditPriceCommand(menu, arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            return new IncorrectCommand(UserOutput.UNKNOWN_COMMAND_MESSAGE.message);
        }
    }

    // All prepareCommand Classes
    private static Command prepareListMenu() {
        return new ListMenuCommand();
    }

    private static Command prepareEditListCommand(String arguments) {
        return null;
    }

    /**
     * Parse argument in the context of edit price command
     * @param arguments string that matches group arguments
     * @return new EditDishCommand
     */
    private static Command prepareEditPriceCommand(Menu menu, String arguments) {
        Pattern editDishArgumentsPattern = Pattern.compile(EDIT_PRICE_ARGUMENT_STRING);
        Matcher matcher = editDishArgumentsPattern.matcher(arguments);

        // Checks whether the overall pattern of edit price arguments is correct
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MISSING_ARGUMENT_FOR_EDIT_PRICE);
        }

        try {
            int dishIndex = Integer.parseInt(matcher.group(1));
            float newPrice = Float.parseFloat(matcher.group(2));

            // Check whether the dish index is valid
            if (!menu.isValidDishIndex(dishIndex)) {
                return new IncorrectCommand(Messages.INVALID_DISH_INDEX);
            }
            return new EditPriceCommand(dishIndex, newPrice);
        } catch (IllegalArgumentException e) {
            return new IncorrectCommand(Messages.WRONG_ARGUMENT_TYPE_FOR_EDIT_PRICE);
        }
    }

    private static Command prepareAdd(String arguments) {
        final Pattern addArgumentPatter = Pattern.compile(ADD_ARGUMENT_STRING);
        Matcher matcher = addArgumentPatter.matcher(arguments);

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
     * @param arguments Input from the user
     * @return Command to be executed
     */
    private static Command prepareDelete(String arguments) {
        Pattern deleteDishArgumentsPattern = Pattern.compile(DELETE_ARGUMENT_STRING);
        Matcher matcher = deleteDishArgumentsPattern.matcher(arguments.trim());

        // Checks whether the overall pattern of delete price arguments is correct
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MISSING_ARGUMENT_FOR_DELETE);
        }

        try {
            int listIndexArgGroup = 1;
            int listIndex = Integer.parseInt(matcher.group(listIndexArgGroup));
            return new DeleteDishCommand(listIndex);
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
