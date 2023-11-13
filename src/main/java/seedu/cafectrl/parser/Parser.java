package seedu.cafectrl.parser;


import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.command.AddDishCommand;
import seedu.cafectrl.command.AddOrderCommand;
import seedu.cafectrl.command.BuyIngredientCommand;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.command.DeleteDishCommand;
import seedu.cafectrl.command.EditPriceCommand;
import seedu.cafectrl.command.ExitCommand;
import seedu.cafectrl.command.HelpCommand;
import seedu.cafectrl.command.IncorrectCommand;
import seedu.cafectrl.command.ListIngredientCommand;
import seedu.cafectrl.command.ListMenuCommand;
import seedu.cafectrl.command.NextDayCommand;
import seedu.cafectrl.command.PreviousDayCommand;
import seedu.cafectrl.command.ListTotalSalesCommand;
import seedu.cafectrl.command.ListSaleByDayCommand;
import seedu.cafectrl.command.ViewTotalStockCommand;

import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.Order;
import seedu.cafectrl.data.OrderList;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.parser.exception.ParserException;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parse everything received from the users on terminal
 * into a format that can be interpreted by other core classes
 */
public class Parser implements ParserUtil {
    private static final String COMMAND_ARGUMENT_REGEX = "(?<commandWord>\\S+)\\s*(?<arguments>.*)";

    //@@author DextheChik3n
    /** Add Dish Command Handler Patterns*/
    private static final String ADD_ARGUMENT_FORMAT_REGEX = "name/(?<dishName>.*) "
            + "price/(?<dishPrice>\\s*\\S*)\\s+(?<ingredients>ingredient/.*)";
    private static final String DISH_NAME_MATCHER_GROUP_LABEL = "dishName";
    private static final String PRICE_MATCHER_GROUP_LABEL = "dishPrice";
    private static final String INGREDIENTS_MATCHER_GROUP_LABEL = "ingredients";
    private static final String INGREDIENT_ARGUMENT_FORMAT_REGEX = "\\s*ingredient/(?<ingredientName>.*) "
            + "qty/\\s*(?<ingredientQty>.*)\\s*";
    private static final String INGREDIENT_NAME_REGEX_GROUP_LABEL = "ingredientName";
    private static final String INGREDIENT_QTY_REGEX_GROUP_LABEL = "ingredientQty";
    private static final String INGREDIENT_QTY_FORMAT_REGEX = "^\\s*(?<value>[0-9]*)\\s*(?<unit>[a-zA-z]*)\\s*$";
    private static final String INGREDIENT_QTY_VALUE_REGEX_GROUP_LABEL = "value";
    private static final String INGREDIENT_QTY_UNIT_REGEX_GROUP_LABEL = "unit";
    private static final String ADD_DISH_NAME_ARGUMENT = "name/";
    private static final String ADD_DISH_PRICE_ARGUMENT = "price/";
    private static final String INGREDIENT_ARGUMENT = "ingredient/";
    private static final String QTY_ARGUMENT = "qty/";
    private static final String INGREDIENT_DIVIDER_REGEX = ",";

    /** Add Order Command Handler Patterns*/
    private static final int DISH_NAME_MATCHER_GROUP_NUM = 1;
    private static final int ORDER_QTY_MATCHER_GROUP_NUM = 2;
    private static final String ADD_ORDER_ARGUMENT_STRING = "name/([A-Za-z0-9\\s]+) "
            + "qty/([A-Za-z0-9\\s]+)";

    /** The rest of Command Handler Patterns*/

    private static final String LIST_INGREDIENTS_ARGUMENT_STRING = "dish/(.+)";
    private static final String DELETE_ARGUMENT_STRING = "(.+)";
    private static final String EDIT_PRICE_ARGUMENT_STRING = "dish/(.*)\\sprice/(.*)";
    private static final String BUY_INGREDIENT_ARGUMENT_STRING = "(ingredient/[A-Za-z0-9\\\\s]+ qty/.+"
            + "(?:, ingredient/[A-Za-z0-9\\\\s]+ qty/.+)*)";
    private static final String SHOW_SALE_BY_DAY_ARGUMENT_STRING = "day/(.+)";
    private static final int MIN_QTY = 1;
    private static final int MAX_QTY = 1000000;
    private static final String GRAMS_UNIT = "g";
    private static final String ML_UNIT = "ml";
    private static final String PRICE_INPUT_REGEX = "^-?[0-9]\\d*(\\.\\d{0,2})?$";
    private static Logger logger = Logger.getLogger(CafeCtrl.class.getName());

    //@@author ziyi105
    /**
     * Parse userInput and group it under commandWord and arguments
     * use commandWord to find the matching command and prepare the command
     *
     * @param menu The arraylist object created that stores current tasks
     * @param userInput The full user input String
     * @param ui The ui object created that handles I/O with the user
     * @param pantry The arraylist object created that stores current ingredients in stock
     * @return command requested by the user
     */
    public Command parseCommand(Menu menu, String userInput, Ui ui,
            Pantry pantry, Sales sales, CurrentDate currentDate) {
        logger.info("Received user input: " + userInput);

        Pattern userInputPattern = Pattern.compile(COMMAND_ARGUMENT_REGEX);
        final Matcher matcher = userInputPattern.matcher(userInput.trim());

        if (!matcher.matches()) {
            logger.warning("Unmatched regex!");
            return new IncorrectCommand(ErrorMessages.UNKNOWN_COMMAND_MESSAGE, ui);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddDishCommand.COMMAND_WORD:
            return prepareAdd(arguments, menu, ui);

        case DeleteDishCommand.COMMAND_WORD:
            return prepareDelete(menu, arguments, ui);

        case ListIngredientCommand.COMMAND_WORD:
            return prepareListIngredient(menu, arguments, ui);

        case ListMenuCommand.COMMAND_WORD:
            return prepareListMenu(menu, ui);

        case EditPriceCommand.COMMAND_WORD:
            return prepareEditPriceCommand(menu, arguments, ui);

        case ViewTotalStockCommand.COMMAND_WORD:
            return prepareViewTotalStock(ui, pantry);

        case BuyIngredientCommand.COMMAND_WORD:
            return prepareBuyIngredient(arguments, ui, pantry, menu);

        case HelpCommand.COMMAND_WORD:
            return prepareHelpCommand(ui, arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand(ui, pantry);

        case AddOrderCommand.COMMAND_WORD:
            return prepareOrder(menu, arguments, ui, pantry, sales, currentDate);

        case NextDayCommand.COMMAND_WORD:
            return prepareNextDay(ui, sales, currentDate);

        case PreviousDayCommand.COMMAND_WORD:
            return preparePreviousDay(ui, currentDate);

        case ListTotalSalesCommand.COMMAND_WORD:
            return prepareShowSales(sales, menu, ui, arguments);

        case ListSaleByDayCommand.COMMAND_WORD:
            return prepareShowSalesByDay(arguments, ui, sales, menu);

        default:
            logger.warning(ErrorMessages.UNKNOWN_COMMAND_MESSAGE);
            return new IncorrectCommand(ErrorMessages.UNKNOWN_COMMAND_MESSAGE, ui);
        }
    }

    //@@author Cazh1
    /**
     * Prepares the ListMenuCommand
     *
     * @param menu menu of the current session
     * @param ui ui of the current session
     * @return new ListMenuCommand
     */
    private static Command prepareListMenu(Menu menu, Ui ui) {
        return new ListMenuCommand(menu, ui);
    }

    //@@author ziyi105
    /**
     * Parse argument in the context of edit price command
     *
     * @param menu menu of the current session
     * @param arguments string that matches group arguments
     * @return new EditDishCommand
     */
    private static Command prepareEditPriceCommand(Menu menu, String arguments, Ui ui) {
        Pattern editDishArgumentsPattern = Pattern.compile(EDIT_PRICE_ARGUMENT_STRING);
        Matcher matcher = editDishArgumentsPattern.matcher(arguments);

        // Checks whether the overall pattern of edit price arguments is correct
        if (!matcher.find()) {
            logger.log(Level.WARNING, "Unmatched regex!");
            return new IncorrectCommand(ErrorMessages.MISSING_ARGUMENT_FOR_EDIT_PRICE, ui);
        }

        int dishIndexGroup = 1;
        int newPriceGroup = 2;
        int dishIndex;
        float newPrice;

        try {
            String dishIndexText = matcher.group(dishIndexGroup).trim();

            // Check whether the index is empty
            if (dishIndexText.isEmpty()) {
                logger.warning("Empty dish index!");
                return new IncorrectCommand(ErrorMessages.MISSING_DISH_IN_EDIT_PRICE, ui);
            }

            dishIndex = Integer.parseInt(dishIndexText);

            // Check whether the dish index is valid
            if (!menu.isValidDishIndex(dishIndex)) {
                logger.warning("Invalid dish index!");
                return new IncorrectCommand(ErrorMessages.INVALID_DISH_INDEX, ui);
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid dish index type!", e);
            return new IncorrectCommand(ErrorMessages.WRONG_DISH_INDEX_TYPE_FOR_EDIT_PRICE, ui);
        }

        try {
            newPrice = parsePriceToFloat(matcher.group(newPriceGroup).trim());
        } catch (ParserException e) {
            logger.log(Level.WARNING, "Invalid price!", e);
            return new IncorrectCommand(e.getMessage(), ui);
        }

        return new EditPriceCommand(dishIndex, newPrice, menu, ui);
    }

    //@@author DextheChik3n
    /**
     * Parses the user input text into ingredients to form a <code>Dish</code>
     * that is added to the <code>Menu</code>
     *
     * @param arguments string that matches group arguments
     * @param menu Menu of the current session
     * @param ui Ui of the current session
     * @return new AddDishCommand
     */
    private static Command prepareAdd(String arguments, Menu menu, Ui ui) {
        try {
            Matcher matcher = detectErrorInPreAddParse(arguments);
            // Checks whether the overall pattern of add arguments is correct
            if (!matcher.matches()) {
                logger.log(Level.WARNING, "Unmatched regex!");
                return new IncorrectCommand(ErrorMessages.INVALID_ADD_DISH_FORMAT
                        + AddDishCommand.MESSAGE_USAGE, ui);
            }

            // To retrieve specific arguments from arguments
            //the dishName needs .trim() because the regex accepts whitespaces in the "name/" argument
            String dishName = matcher.group(DISH_NAME_MATCHER_GROUP_LABEL).trim();
            float dishPrice = parsePriceToFloat(matcher.group(PRICE_MATCHER_GROUP_LABEL));
            String ingredientsListString = matcher.group(INGREDIENTS_MATCHER_GROUP_LABEL);

            detectErrorPostDishNameParse(dishName, menu);

            ArrayList<Ingredient> ingredients = parseIngredients(ingredientsListString, true, menu);
            Dish dish = new Dish(dishName, ingredients, dishPrice);

            return new AddDishCommand(dish, menu, ui);
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            return new IncorrectCommand(ErrorMessages.NULL_NAME_DETECTED_MESSAGE, ui);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            return new IncorrectCommand(ErrorMessages.INVALID_INGREDIENT_QTY, ui);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            return new IncorrectCommand(e.getMessage(), ui);
        }
    }

    private static Matcher detectErrorInPreAddParse(String arguments) throws ParserException {
        if (isRepeatedArgument(arguments, ADD_DISH_NAME_ARGUMENT)) {
            logger.log(Level.WARNING, "Repeated dish/ argument!");
            throw new ParserException(ErrorMessages.REPEATED_NAME_ARGUMENT);
        } else if (isRepeatedArgument(arguments, ADD_DISH_PRICE_ARGUMENT)) {
            logger.log(Level.WARNING, "Repeated price/ argument!");
            throw new ParserException(ErrorMessages.REPEATED_PRICE_ARGUMENT);
        }

        // Checks whether the overall pattern of add arguments is correct
        Pattern addArgumentPattern = Pattern.compile(ADD_ARGUMENT_FORMAT_REGEX);
        Matcher matcher = addArgumentPattern.matcher(arguments);

        if (!matcher.matches()) {
            logger.log(Level.WARNING, "Unmatched regex!");
            throw new ParserException(ErrorMessages.INVALID_ADD_DISH_FORMAT + AddDishCommand.MESSAGE_USAGE);
        }

        return matcher;
    }

    private static void detectErrorPostDishNameParse(String dishName, Menu menu) throws ParserException {
        if (dishName.isEmpty()) {
            logger.warning("Dish name empty!");
            throw new ParserException(ErrorMessages.MISSING_DISH_NAME);
        } else if (isNameLengthInvalid(dishName)) {
            logger.warning("Invalid name length!");
            throw new ParserException(ErrorMessages.INVALID_DISH_NAME_LENGTH_MESSAGE);
        } else if (isRepeatedDishName(dishName, menu)) {
            logger.warning("Repeated dish!");
            throw new ParserException(ErrorMessages.REPEATED_DISH_MESSAGE);
        } else if (containsSpecialChar(dishName)) {
            logger.warning("Special character in dish name!");
            throw new ParserException(ErrorMessages.NAME_CANNOT_CONTAIN_SPECIAL_CHAR);
        }
    }

    /**
     * Parses the user's input text ingredients.
     *
     * @param ingredientsListString user's input string of ingredients,
     *                              multiple ingredients seperated by ',' is allowed
     * @param menu
     * @return list of ingredients that consists of the dish
     * @throws ParserException if the input string does not match the constraints
     * @throws NumberFormatException if the string value of the ingredient qty does not contain a parsable integer.
     */
    private static ArrayList<Ingredient> parseIngredients(
            String ingredientsListString, boolean isExcludeRepeatedIngredients, Menu menu)
            throws ParserException, NumberFormatException {
        logger.info("Parsing ingredients...");
        String[] inputIngredientList = {ingredientsListString};
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        //check if there is more than 1 ingredient
        if (ingredientsListString.contains(INGREDIENT_DIVIDER_REGEX)) {
            //split the whole string of ingredients into separate individual ingredients
            inputIngredientList = ingredientsListString.split(INGREDIENT_DIVIDER_REGEX);
        }

        //Parsing each ingredient
        for (String inputIngredient: inputIngredientList) {
            parseIngredient(isExcludeRepeatedIngredients, menu, inputIngredient, ingredients);
        }

        return ingredients;
    }

    private static void parseIngredient(
            boolean isExcludeRepeatedIngredients, Menu menu,
            String inputIngredient, ArrayList<Ingredient> ingredients)
            throws ParserException {
        Matcher ingredientMatcher = detectErrorPreIngredientParse(inputIngredient);

        String ingredientName = ingredientMatcher.group(INGREDIENT_NAME_REGEX_GROUP_LABEL).trim();

        //ingredientQtyString contains the input text after the "qty/" argument
        String ingredientQtyString = ingredientMatcher.group(INGREDIENT_QTY_REGEX_GROUP_LABEL).trim();

        //check the formatting of text after ingredient qty argument (qty/)
        final Pattern ingredientQtyFormatPattern = Pattern.compile(INGREDIENT_QTY_FORMAT_REGEX);
        Matcher ingredientQtyMatcher = ingredientQtyFormatPattern.matcher(ingredientQtyString);

        if (!ingredientQtyMatcher.matches()) {
            throw new ParserException(ErrorMessages.INVALID_INGREDIENT_QTY_FORMAT);
        }

        String ingredientUnit = ingredientQtyMatcher.group(INGREDIENT_QTY_UNIT_REGEX_GROUP_LABEL);
        int ingredientQty = Integer.parseInt(ingredientQtyMatcher
                .group(INGREDIENT_QTY_VALUE_REGEX_GROUP_LABEL));

        detectErrorPostIngredientParse(isExcludeRepeatedIngredients,
                ingredientName, ingredientQty, ingredientUnit, ingredients);

        Ingredient ingredient = new Ingredient(ingredientName, ingredientQty, ingredientUnit);

        checkForMismatchUnit(menu, ingredient);

        ingredients.add(ingredient);
    }

    private static Matcher detectErrorPreIngredientParse(String inputIngredient)
            throws ParserException {
        if (isRepeatedArgument(inputIngredient, INGREDIENT_ARGUMENT)) {
            logger.log(Level.WARNING, "Repeated ingredient/ argument!");
            throw new ParserException(ErrorMessages.REPEATED_INGREDIENT_ARGUMENT);
        } else if (isRepeatedArgument(inputIngredient, QTY_ARGUMENT)) {
            logger.log(Level.WARNING, "Repeated qty/ argument!");
            throw new ParserException(ErrorMessages.REPEATED_QTY_ARGUMENT);
        }

        final Pattern ingredientPattern = Pattern.compile(INGREDIENT_ARGUMENT_FORMAT_REGEX);
        Matcher ingredientMatcher = ingredientPattern.matcher(inputIngredient);

        if (!ingredientMatcher.matches()) {
            logger.log(Level.WARNING, "Mismatched ingredient arguments!");
            throw new ParserException(ErrorMessages.INVALID_INGREDIENT_ARGUMENTS);
        }

        return ingredientMatcher;
    }

    private static void detectErrorPostIngredientParse(
            boolean isExcludeRepeatedIngredients, String ingredientName, int ingredientQty,
            String ingredientUnit, ArrayList<Ingredient> ingredients) throws ParserException {

        //error case
        if (ingredientName.isEmpty()) {
            logger.log(Level.WARNING, "Missing ingredient name!");
            throw new ParserException(ErrorMessages.MISSING_INGREDIENT_NAME);
        } else if (isNameLengthInvalid(ingredientName)) {
            logger.log(Level.WARNING, "Exceed max ingredient name length!");
            throw new ParserException(ErrorMessages.INVALID_INGREDIENT_NAME_LENGTH_MESSAGE);
        } else if (isInvalidQty(ingredientQty)) {
            logger.log(Level.WARNING, "Exceed ingredient qty range!");
            throw new ParserException(ErrorMessages.INVALID_INGREDIENT_QTY);
        } else if (isEmptyUnit(ingredientUnit)) {
            logger.log(Level.WARNING, "Missing ingredient qty unit!");
            throw new ParserException(ErrorMessages.EMPTY_UNIT_MESSAGE);
        } else if (!isValidUnit(ingredientUnit)) {
            logger.log(Level.WARNING, "Invalid ingredient qty unit!");
            throw new ParserException(ErrorMessages.INVALID_UNIT_MESSAGE);
        } else if (containsSpecialChar(ingredientName)) {
            logger.log(Level.WARNING, "Special character in ingredient name!");
            throw new ParserException(ErrorMessages.NAME_CANNOT_CONTAIN_SPECIAL_CHAR);
        }

        //unusual case
        //user input repeated ingredient name for add dish command
        if (isExcludeRepeatedIngredients && isRepeatedIngredientName(ingredientName, ingredients)) {
            logger.log(Level.WARNING, "Repeated ingredient name for AddDishCommand!");
            throw new ParserException(ErrorMessages.REPEATED_INGREDIENT_NAME);
        }
    }

    /**
     * Converts text of price to float while also checking if the price input is within reasonable range
     *
     * @param priceText text input for price argument
     * @return price in float format
     * @throws ParserException if price is not within reasonable format and range
     */
    public static float parsePriceToFloat(String priceText) throws ParserException {
        String trimmedPriceText = priceText.trim();

        final Pattern pricePattern = Pattern.compile(PRICE_INPUT_REGEX);
        Matcher priceMatcher = pricePattern.matcher(trimmedPriceText);

        // Check whether price text is empty
        if (priceText.isEmpty()) {
            logger.log(Level.WARNING, "Missing dish price!");
            throw new ParserException(ErrorMessages.MISSING_PRICE);
        } else if (!priceMatcher.matches()) {
            logger.log(Level.WARNING, "Exceed price valid range!");
            throw new ParserException(ErrorMessages.WRONG_PRICE_TYPE_FOR_EDIT_PRICE);
        }

        float price;
        try {
            price = Float.parseFloat(trimmedPriceText);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            throw new ParserException(ErrorMessages.WRONG_PRICE_TYPE_FOR_EDIT_PRICE);
        }

        // Specify max and min value for price
        float maxPriceValue = (float) 1000000.00;
        float minPriceValue = (float) 0;

        // Check whether the price has up to 2 decimal place
        if (price > maxPriceValue) {
            throw new ParserException(ErrorMessages.LARGE_PRICE_MESSAGE);
        } else if (price < minPriceValue) {
            throw new ParserException(ErrorMessages.NEGATIVE_PRICE_MESSAGE);
        }

        return price;
    }

    /**
     * Checks in the menu if the dish name already exists.
     *
     * @param inputDishName dish name entered by the user
     * @param menu contains all the existing Dishes
     * @return true if dish name already exists in menu, false otherwise
     * @throws NullPointerException if the input string is null
     */
    public static boolean isRepeatedDishName(String inputDishName, Menu menu) throws NullPointerException {
        if (inputDishName == null) {
            throw new NullPointerException();
        }

        for (Dish dish: menu.getMenuItemsList()) {
            String menuDishName = dish.getName();

            if (menuDishName.equalsIgnoreCase(inputDishName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks in the ingredient list if the ingredient name already exists.
     *
     * @param inputName dish name entered by the user
     * @param ingredients contains all the existing Ingredients
     * @return true if ingredient name already exists in menu, false otherwise
     * @throws NullPointerException if the input string is null
     */
    public static boolean isRepeatedIngredientName(String inputName, ArrayList<Ingredient> ingredients)
            throws NullPointerException {
        if (inputName == null) {
            throw new NullPointerException();
        }

        for (Ingredient ingredient: ingredients) {
            String ingredientNameLowerCase = ingredient.getName().toLowerCase();
            String inputIngredientNameLowerCase = inputName.toLowerCase();

            if (ingredientNameLowerCase.equals(inputIngredientNameLowerCase)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks the length of the name is too long
     *
     * @param inputName name
     * @return true if the name is more than max character limit set, false otherwise
     * @throws NullPointerException if the input string is null
     */
    public static boolean isNameLengthInvalid(String inputName) throws NullPointerException {
        int maxNameLength = 35;

        if (inputName == null) {
            throw new NullPointerException();
        } else if (inputName.length() > maxNameLength) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the argument is entered more than once.
     *
     * @param inputText text to be checked
     * @param argument argument to be checked for multiple occurrences
     * @return true if there is > 1 match of the argument, false otherwise
     */
    public static boolean isRepeatedArgument(String inputText, String argument) {
        if (inputText == null || argument == null) {
            throw new NullPointerException(ErrorMessages.NULL_STRING_IN_REPEAT_ARGUMENT);
        }

        Pattern argumentPattern = Pattern.compile(argument);
        Matcher matcher = argumentPattern.matcher(inputText);
        int maxNumberOfMatches = 1;

        long argumentMatches = matcher.results().count();
        if (argumentMatches > maxNumberOfMatches) {
            return true;
        }

        return false;
    }

    //@@author NaychiMin
    /**
     * Parses arguments in the context of the ListIngredient command.
     *
     * @param menu menu of the current session
     * @param arguments string that matches group arguments
     * @return the prepared command
    */
    private static Command prepareListIngredient(Menu menu, String arguments, Ui ui) {
        final Pattern prepareListPattern = Pattern.compile(LIST_INGREDIENTS_ARGUMENT_STRING);
        Matcher matcher = prepareListPattern.matcher(arguments.trim());

        if (!matcher.matches()) {
            logger.warning("Unmatched regex!");
            return new IncorrectCommand(ErrorMessages.MISSING_ARGUMENT_FOR_LIST_INGREDIENTS
                    + ListIngredientCommand.MESSAGE_USAGE, ui);
        }

        try {
            int dishIndex = Integer.parseInt(matcher.group(1).trim());

            if (dishIndex < 0) {
                throw new Exception();
            }

            if (!menu.isValidDishIndex(dishIndex)) {
                return new IncorrectCommand(ErrorMessages.UNLISTED_DISH, ui);
            }

            return new ListIngredientCommand(dishIndex, menu, ui);
        } catch (Exception e) {
            return new IncorrectCommand(ErrorMessages.INVALID_DISH_INDEX_TO_LIST, ui);
        }
    }

    //@@author ShaniceTang
    /**
     * Parses arguments in the context of the Delete command.
     *
     * @param menu menu of the current session
     * @param arguments string that matches group arguments
     * @return DeleteDishCommand if command is valid, IncorrectCommand otherwise
     */
    private static Command prepareDelete(Menu menu, String arguments, Ui ui) {
        Pattern deleteDishArgumentsPattern = Pattern.compile(DELETE_ARGUMENT_STRING);
        Matcher matcher = deleteDishArgumentsPattern.matcher(arguments.trim());

        // Checks whether the overall pattern of delete price arguments is correct
        if (!matcher.matches()) {
            logger.warning("Unmatched regex!");
            return new IncorrectCommand(ErrorMessages.MISSING_ARGUMENT_FOR_DELETE, ui);
        }

        int listIndexArgGroup = 1;

        try {
            int dishIndex = Integer.parseInt(matcher.group(listIndexArgGroup));
            if (!menu.isValidDishIndex(dishIndex)) {
                return new IncorrectCommand(ErrorMessages.INVALID_DISH_INDEX, ui);
            }
            return new DeleteDishCommand(dishIndex, menu, ui);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(ErrorMessages.DISH_INDEX_NOT_INT, ui);
        }
    }

    /**
     * Prepares a command to view the total stock in the pantry.
     *
     * @param ui     The user interface to interact with the user.
     * @param pantry The pantry containing ingredient stock information.
     * @return A command to view the total stock.
     */
    private static Command prepareViewTotalStock(Ui ui, Pantry pantry) {
        return new ViewTotalStockCommand(pantry, ui);
    }

    /**
     * Prepares a command to buy ingredients based on the provided arguments.
     *
     * @param arguments The user input arguments for buying ingredients.
     * @param ui        The user interface to interact with the user.
     * @param pantry    The pantry to update with bought ingredients.
     * @param menu      The menu containing information about available dishes.
     * @return A command to buy ingredients or an incorrect command if arguments are invalid.
     */
    private static Command prepareBuyIngredient(String arguments, Ui ui, Pantry pantry, Menu menu) {
        Pattern buyIngredientArgumentsPattern = Pattern.compile(BUY_INGREDIENT_ARGUMENT_STRING);
        Matcher matcher = buyIngredientArgumentsPattern.matcher(arguments.trim());

        if (!matcher.matches()) {
            logger.warning("Unmatched regex!");
            return new IncorrectCommand(ErrorMessages.INVALID_INGREDIENT_ARGUMENTS, ui);
        }

        String ingredientsListString = matcher.group(0);

        try {
            ArrayList<Ingredient> ingredients = parseIngredients(ingredientsListString, false, menu);
            return new BuyIngredientCommand(ingredients, ui, pantry);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(ErrorMessages.INVALID_INGREDIENT_QTY, ui);
        } catch (Exception e) {
            return new IncorrectCommand(e.getMessage(), ui);
        }
    }

    public static boolean isValidUnit(String ingredientUnit) {
        return ingredientUnit.equals(GRAMS_UNIT) || ingredientUnit.equals(ML_UNIT);
    }

    public static boolean isEmptyUnit(String ingredientUnit) {
        return ingredientUnit.equals("");
    }

    public static boolean isInvalidQty(int ingredientQty) {
        return ingredientQty < MIN_QTY || ingredientQty > MAX_QTY;
    }

    /**
     * Checks for mismatched units between a new ingredient and existing ingredients in the menu.
     *
     * @param menu          The menu containing information about available dishes.
     * @param newIngredient The new ingredient to check for mismatched units.
     * @throws ParserException If a mismatch in units is detected.
     */
    public static void checkForMismatchUnit(Menu menu, Ingredient newIngredient) throws ParserException {
        logger.info("Checking for mismatched units...");
        ArrayList<Dish> dishArrayList = menu.getMenuItemsList();

        for (Dish dish : dishArrayList) {
            traverseIngredientsOfDish(newIngredient, dish);
        }
    }

    private static void traverseIngredientsOfDish(Ingredient newIngredient, Dish dish) throws ParserException {
        ArrayList<Ingredient> ingredientArrayList = dish.getIngredients();

        for (Ingredient currentIngredient : ingredientArrayList) {
            logger.info("Comparing name: " + newIngredient.getName() + " and " + currentIngredient.getName());
            compareIngredientName(newIngredient, currentIngredient);
        }
    }

    private static void compareIngredientName(Ingredient newIngredient,
            Ingredient currentIngredient) throws ParserException {
        if (currentIngredient.getName().equalsIgnoreCase(newIngredient.getName())) {
            logger.info("Comparing units: " + newIngredient.getUnit() + " and " + currentIngredient.getUnit());
            compareUnits(newIngredient, currentIngredient);
        }
    }

    private static void compareUnits(Ingredient newIngredient, Ingredient currentIngredient) throws ParserException {
        if (!currentIngredient.getUnit().equalsIgnoreCase(newIngredient.getUnit())) {
            logger.warning("Units not matching!");
            throw new ParserException(newIngredient.getName()
                    + ErrorMessages.UNIT_NOT_MATCHING
                    + currentIngredient.getUnit()
                    + ErrorMessages.RETYPE_COMMAND_MESSAGE);
        }
    }

    //@@author ziyi105
    /**
     * Check whether a text contains special character
     *
     * @param text text to be checked
     * @return true if it contains special character, false otherwise
     */
    public static boolean containsSpecialChar(String text) {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    //@@author ziyi105
    private static Command prepareHelpCommand(Ui ui, String arguments) {
        return new IncorrectCommand(ErrorMessages.WRONG_HELP_FORMAT, ui);
    }

    //@@author Cazh1
    /**
     * Parses arguments in the context of the AddOrder command.
     *
     * @param menu menu of the current session
     * @param arguments string that matches group arguments
     * @param ui
     * @return AddOrderCommand if command is valid, IncorrectCommand otherwise
     */
    private static Command prepareOrder(Menu menu, String arguments, Ui ui,
            Pantry pantry, Sales sales, CurrentDate currentDate) {
        final Pattern addOrderArgumentPatter = Pattern.compile(ADD_ORDER_ARGUMENT_STRING);
        Matcher matcher = addOrderArgumentPatter.matcher(arguments);

        // Checks whether the overall pattern of add order arguments is correct
        if (!matcher.matches()) {
            logger.warning("Unmatching regex!");
            return new IncorrectCommand(ErrorMessages.INVALID_ADD_ORDER_FORMAT_MESSAGE
                    + AddOrderCommand.MESSAGE_USAGE, ui);
        }

        OrderList orderList = setOrderList(currentDate, sales);

        try {
            // To retrieve specific arguments from arguments
            String dishName = matcher.group(DISH_NAME_MATCHER_GROUP_NUM);
            int dishQty = Integer.parseInt(matcher.group(ORDER_QTY_MATCHER_GROUP_NUM));

            Dish orderedDish = menu.getDishFromName(dishName);
            if (orderedDish == null) {
                return new IncorrectCommand(ErrorMessages.DISH_NOT_FOUND, ui);
            }

            Order order = new Order(orderedDish, dishQty);

            return new AddOrderCommand(order, ui, pantry, orderList, menu);
        } catch (Exception e) {
            return new IncorrectCommand(ErrorMessages.INVALID_ADD_ORDER_FORMAT_MESSAGE
                    + AddOrderCommand.MESSAGE_USAGE + e.getMessage(), ui);
        }
    }

    /**
     * Prepares PreviousDayCommand
     *
     * @param ui ui object of the current session
     * @param currentDate currentDate object of the current session
     * @return PreviousDayCommand if after day 1, IncorrectCommand if before
     */
    private static Command preparePreviousDay(Ui ui, CurrentDate currentDate) {
        int currentDay = currentDate.getCurrentDay();
        if (currentDay == 0) {
            return new IncorrectCommand(Messages.PREVIOUS_DAY_TIME_TRAVEL, ui);
        }
        return new PreviousDayCommand(ui, currentDate);
    }

    /**
     * Prepares NextDayCommand
     *
     * @param ui ui object of the current session
     * @param sales sales object of the current session
     * @param currentDate currentDate object of the current session
     * @return NextDayCommand
     */
    private static Command prepareNextDay(Ui ui, Sales sales, CurrentDate currentDate) {
        return new NextDayCommand(ui, sales, currentDate);
    }

    //@@author NaychiMin
    /**
     * Prepares a command to display all sales items.
     *
     * @param sale The Sales object containing sales data.
     * @param menu The Menu object representing the cafe's menu.
     * @param ui   The Ui object for user interface interactions.
     * @return A ShowSalesCommand instance for viewing all sales items.
     */
    private static Command prepareShowSales(Sales sale, Menu menu, Ui ui, String arguments) {
        if (arguments.isEmpty()) {
            return new ListTotalSalesCommand(sale, ui, menu);
        } else {
            return new IncorrectCommand(ErrorMessages.WRONG_LIST_TOTAL_SALES_FORMAT, ui);
        }

    }

    /**
     * Prepares a command to display sales items for a specific day.
     *
     * @param arguments The arguments containing the day for which sales are to be displayed.
     * @param ui        The Ui object for user interface interactions.
     * @param sales     The Sales object containing sales data.
     * @param menu      The Menu object representing the cafe's menu.
     * @return A ShowSalesByDayCommand instance for viewing sales items on a specific day.
     */
    private static Command prepareShowSalesByDay(String arguments, Ui ui, Sales sales, Menu menu) {
        final Pattern showSaleByDayPattern = Pattern.compile(SHOW_SALE_BY_DAY_ARGUMENT_STRING);
        Matcher matcher = showSaleByDayPattern.matcher(arguments.trim());

        if (!matcher.matches()) {
            logger.warning("Unmatching regex!");
            return new IncorrectCommand(ErrorMessages.INVALID_SHOW_SALE_DAY_FORMAT_MESSAGE
                    + ListSaleByDayCommand.MESSAGE_USAGE, ui);
        }

        try {
            int day = Integer.parseInt(matcher.group(1).trim());
            if (day < 0) {
                throw new Exception();
            }
            return new ListSaleByDayCommand(day, ui, sales, menu);
        } catch (Exception e) {
            return new IncorrectCommand(ErrorMessages.INVALID_DAY_FORMAT, ui);
        }
    }

    //@@author Cazh1
    /**
     * Sets the orderList according to the Day
     *
     * @param currentDate currentDate object of the current session
     * @param sales sales object of the current session, contains the orderLists
     * @return The respective orderList
     */
    private static OrderList setOrderList(CurrentDate currentDate, Sales sales) {
        int currentDay = currentDate.getCurrentDay();
        return sales.getOrderList(currentDay);
    }
}
