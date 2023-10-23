package seedu.cafectrl.parser;

import seedu.cafectrl.command.AddDishCommand;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.command.DeleteDishCommand;
import seedu.cafectrl.command.EditPriceCommand;
import seedu.cafectrl.command.ExitCommand;
import seedu.cafectrl.command.HelpCommand;
import seedu.cafectrl.command.IncorrectCommand;
import seedu.cafectrl.command.ListIngredientCommand;
import seedu.cafectrl.command.ListMenuCommand;
import seedu.cafectrl.ui.Messages;
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
    public static final Pattern COMMAND_ARGUMENT_FORMAT = Pattern.compile("(?<commandWord>\\S+)\\s?(?<arguments>.*)");

    // Command Argument Patterns
    public static final String INGREDIENT_ARGUMENT_STRING = "ingredient/(?<name>[A-Za-z0-9\\s]+) "
            + "qty/(?<qty>[A-Za-z0-9\\s]+)";
    public static final String INGREDIENT_DIVIDER_REGEX = ", ";
    public static final String INGREDIENT_DIVIDER_STRING = ",";
    public static final String INGREDIENT_NAME_REGEX_GROUP_LABEL = "name";
    public static final String INGREDIENT_QTY_REGEX_GROUP_LABEL = "qty";
    public static final int DISH_NAME_MATCHER_GROUP_NUM = 1;
    public static final int PRICE_MATCHER_GROUP_NUM = 2;
    public static final int INGREDIENT_LIST_MATCHER_GROUP_NUM = 4;
    private static final String ADD_ARGUMENT_STRING = "name/([A-Za-z0-9\\s]+) "
            + "price/([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))? "
            + "(ingredient/[A-Za-z0-9\\s]+ qty/[A-Za-z0-9\\s]+"
            + "(?:, ingredient/[A-Za-z0-9\\s]+ qty/[A-Za-z0-9\\s]+)*)";
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
            return prepareDelete(menu, arguments);

        case ListIngredientCommand.COMMAND_WORD:
            return prepareListIngredient(menu, arguments);

        case ListMenuCommand.COMMAND_WORD:
            return prepareListMenu();

        case EditPriceCommand.COMMAND_WORD:
            return prepareEditPriceCommand(menu, arguments);

        case HelpCommand.COMMAND_WORD:
            return prepareHelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            return new IncorrectCommand(Messages.UNKNOWN_COMMAND_MESSAGE);
        }
    }

    /** All prepareCommand Classes */
    private static Command prepareListMenu() {
        return new ListMenuCommand();
    }

    /**
     * Parse argument in the context of edit price command
     * @param menu menu of the current session
     * @param arguments string that matches group arguments
     * @return new EditDishCommand
     */
    private static Command prepareEditPriceCommand(Menu menu, String arguments) {
        Pattern editDishArgumentsPattern = Pattern.compile(EDIT_PRICE_ARGUMENT_STRING);
        Matcher matcher = editDishArgumentsPattern.matcher(arguments);

        // Checks whether the overall pattern of edit price arguments is correct
        if (!matcher.find()) {
            return new IncorrectCommand(Messages.MISSING_ARGUMENT_FOR_EDIT_PRICE);
        }

        try {
            int dishIndexGroup = 1;
            int newPriceGroup = 2;
            int dishIndex = Integer.parseInt(matcher.group(dishIndexGroup));
            float newPrice = Float.parseFloat(matcher.group(newPriceGroup));

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
        if (!matcher.matches()) {
            return new IncorrectCommand("Error: Incorrect format for the add command.\n"
                    + AddDishCommand.MESSAGE_USAGE);
        }

        try {
            // To retrieve specific arguments from arguments
            String dishName = matcher.group(DISH_NAME_MATCHER_GROUP_NUM);
            float price = Float.parseFloat(matcher.group(PRICE_MATCHER_GROUP_NUM));
            String ingredientsListString = matcher.group(INGREDIENT_LIST_MATCHER_GROUP_NUM);

            IncorrectCommand incorrectCommand1 = checkNegativePrice(price);
            if (incorrectCommand1 != null) {
                return incorrectCommand1;
            }

            // Capture the list of ingredients
            ArrayList<Ingredient> ingredients = new ArrayList<>();

            IncorrectCommand incorrectCommand2 = ingredientParsing(ingredientsListString, ingredients);
            if (incorrectCommand2 != null) {
                return incorrectCommand2;
            }

            Dish dish = new Dish(dishName, ingredients, price);

            return new AddDishCommand(dish);
        } catch (Exception e) {
            return new IncorrectCommand("MESSAGE_INVALID_ADD_COMMAND_FORMAT"
                    + AddDishCommand.MESSAGE_USAGE);
        }
    }

    private static IncorrectCommand checkNegativePrice(float price) {
        if (price < 0) {
            return new IncorrectCommand("MESSAGE_INVALID_ADD_COMMAND_FORMAT"
                    + AddDishCommand.MESSAGE_USAGE);
        }
        return null;
    }

    private static IncorrectCommand ingredientParsing(String ingredientsListString, ArrayList<Ingredient> ingredients) {
        String[] ingredientListInputText = {ingredientsListString};

        //check if there is more than 1 ingredient
        if (ingredientsListString.contains(INGREDIENT_DIVIDER_STRING)) {
            //split the ingredients into separate individual ingredients
            ingredientListInputText = ingredientsListString.split(INGREDIENT_DIVIDER_REGEX);
        }

        for (String inputIngredientText: ingredientListInputText) {
            final Pattern ingredientPattern = Pattern.compile(INGREDIENT_ARGUMENT_STRING);
            Matcher ingredientMatcher = ingredientPattern.matcher(inputIngredientText);

            if (!ingredientMatcher.matches()) {
                return new IncorrectCommand("Error: Incorrect format for the ingredients\n"
                        + AddDishCommand.MESSAGE_USAGE);
            }

            String ingredientName = ingredientMatcher.group(INGREDIENT_NAME_REGEX_GROUP_LABEL);
            String ingredientQty = ingredientMatcher.group(INGREDIENT_QTY_REGEX_GROUP_LABEL);

            Ingredient ingredient = new Ingredient(ingredientName, ingredientQty);

            ingredients.add(ingredient);
        }
        return null;
    }


    /**
    * Parses arguments in the context of the ListIngredient command.
    * @param menu menu of the current session
    * @param arguments string that matches group arguments
    * @return the prepared command
    */
    private static Command prepareListIngredient(Menu menu, String arguments) {
        final Pattern prepareListPattern = Pattern.compile(LIST_INGREDIENTS_ARGUMENT_STRING);
        Matcher matcher = prepareListPattern.matcher(arguments.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MISSING_ARGUMENT_FOR_LIST_INGREDIENTS);
        }

        int dishIndex = Integer.parseInt(matcher.group(1));

        if (!menu.isValidDishIndex(dishIndex)) {
            return new IncorrectCommand(Messages.INVALID_DISH_INDEX);
        }

        return new ListIngredientCommand(dishIndex);
    }

    /**
     * Parses arguments in the context of the Delete command.
     *
     * @param menu menu of the current session
     * @param arguments string that matches group arguments
     * @return DeleteDishCommand if command is valid, IncorrectCommand otherwise
     */
    private static Command prepareDelete(Menu menu, String arguments) {
        Pattern deleteDishArgumentsPattern = Pattern.compile(DELETE_ARGUMENT_STRING);
        Matcher matcher = deleteDishArgumentsPattern.matcher(arguments.trim());

        // Checks whether the overall pattern of delete price arguments is correct
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.MISSING_ARGUMENT_FOR_DELETE);
        }

        int listIndexArgGroup = 1;
        int dishIndex = Integer.parseInt(matcher.group(listIndexArgGroup));

        if (!menu.isValidDishIndex(dishIndex)) {
            return new IncorrectCommand(Messages.INVALID_DISH_INDEX);
        }

        return new DeleteDishCommand(dishIndex);
    }

    private static Command prepareHelpCommand() {
        return new HelpCommand();
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
