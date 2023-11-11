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
    private static final String ADD_ARGUMENT_FORMAT_REGEX = "name/(?<dishName>.*) price/(?<dishPrice>\\s*\\S*)\\s+(?<ingredients>ingredient/.*)";
    private static final String DISH_NAME_MATCHER_GROUP_LABEL = "dishName";
    private static final String PRICE_MATCHER_GROUP_LABEL = "dishPrice";
    private static final String INGREDIENTS_MATCHER_GROUP_LABEL = "ingredients";
    private static final String INGREDIENT_ARGUMENT_FORMAT_REGEX = "\\s*ingredient/(?<ingredientName>.*) qty/\\s*(?<ingredientQty>.*)\\s*";
    private static final String INGREDIENT_NAME_REGEX_GROUP_LABEL = "ingredientName";
    private static final String INGREDIENT_QTY_REGEX_GROUP_LABEL = "ingredientQty";
    public static final String INGREDIENT_QTY_FORMAT_REGEX = "^\\s*(?<value>[0-9]*)(?<unit>[a-zA-z]*)\\s*$";
    private static final String INGREDIENT_QTY_VALUE_REGEX_GROUP_LABEL = "value";
    private static final String INGREDIENT_QTY_UNIT_REGEX_GROUP_LABEL = "unit";
    public static final String ADD_DISH_NAME_ARGUMENT = "name/";
    public static final String ADD_DISH_PRICE_ARGUMENT = "price/";
    public static final String INGREDIENT_ARGUMENT = "ingredient/";
    public static final String QTY_ARGUMENT = "qty/";
    private static final String INGREDIENT_DIVIDER_REGEX = ",";

    /** Add Order Command Handler Patterns*/
    private static final int DISH_NAME_MATCHER_GROUP_NUM = 1;
    private static final int ORDER_QTY_MATCHER_GROUP_NUM = 2;
    private static final String ADD_ORDER_ARGUMENT_STRING = "name/([A-Za-z0-9\\s]+) "
            + "qty/([A-Za-z0-9\\s]+)";

    /** The rest of Command Handler Patterns*/
    private static final String LIST_INGREDIENTS_ARGUMENT_STRING = "(.+)";
    private static final String DELETE_ARGUMENT_STRING = "(\\d+)";
    private static final String EDIT_PRICE_ARGUMENT_STRING = "dish/(.*)\\sprice/(.*)";
    private static final String BUY_INGREDIENT_ARGUMENT_STRING = "(ingredient/[A-Za-z0-9\\s]+ qty/[A-Za-z0-9\\s]+"
            + "(?:, ingredient/[A-Za-z0-9\\s]+ qty/[A-Za-z0-9\\s]+)*)";
    private static final String SHOW_SALE_BY_DAY_ARGUMENT_STRING = "day/(.+)";
    private static final int MIN_QTY = 1;
    private static final int MAX_QTY = 1000000;
    private static final String GRAMS_UNIT = "g";
    private static final String ML_UNIT = "ml";
    public static final String PRICE_INPUT_REGEX = "^-?[0-9]\\d*(\\.\\d{0,2})?$";
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
        userInput = userInput.toLowerCase();
        Pattern userInputPattern = Pattern.compile(COMMAND_ARGUMENT_REGEX);
        final Matcher matcher = userInputPattern.matcher(userInput.trim());

        if (!matcher.matches()) {
            logger.warning("Unmatching regex!");
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
            return prepareBuyIngredient(arguments, ui, pantry);

        case HelpCommand.COMMAND_WORD:
            return prepareHelpCommand(ui);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand(ui, pantry);

        case AddOrderCommand.COMMAND_WORD:
            return prepareOrder(menu, arguments, ui, pantry, sales, currentDate);

        case NextDayCommand.COMMAND_WORD:
            return prepareNextDay(ui, sales, currentDate);

        case PreviousDayCommand.COMMAND_WORD:
            return preparePreviousDay(ui, currentDate);

        case ListTotalSalesCommand.COMMAND_WORD:
            return prepareShowSales(sales, menu, ui);

        case ListSaleByDayCommand.COMMAND_WORD:
            return prepareShowSalesByDay(arguments, ui, sales, menu);

        default:
            return new IncorrectCommand(ErrorMessages.UNKNOWN_COMMAND_MESSAGE, ui);
        }
    }

    //All prepareCommand Classes
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
     * @param menu menu of the current session
     * @param arguments string that matches group arguments
     * @return new EditDishCommand
     */
    private static Command prepareEditPriceCommand(Menu menu, String arguments, Ui ui) {
        Pattern editDishArgumentsPattern = Pattern.compile(EDIT_PRICE_ARGUMENT_STRING);
        Matcher matcher = editDishArgumentsPattern.matcher(arguments);

        // Checks whether the overall pattern of edit price arguments is correct
        if (!matcher.find()) {
            return new IncorrectCommand(ErrorMessages.MISSING_ARGUMENT_FOR_EDIT_PRICE, ui);
        }

        int dishIndexGroup = 1;
        int newPriceGroup = 2;
        int dishIndex;
        float newPrice;

        try {
            String dishIndexText = matcher.group(dishIndexGroup).trim();

            // Check whether the index is empty
            if (dishIndexText.equals("")) {
                return new IncorrectCommand(ErrorMessages.MISSING_DISH_IN_EDIT_PRICE, ui);
            }

            dishIndex = Integer.parseInt(dishIndexText);

            // Check whether the dish index is valid
            if (!menu.isValidDishIndex(dishIndex)) {
                return new IncorrectCommand(ErrorMessages.INVALID_DISH_INDEX, ui);
            }
        } catch (NumberFormatException e) {
            return new IncorrectCommand(ErrorMessages.WRONG_DISH_INDEX_TYPE_FOR_EDIT_PRICE, ui);
        }

        try {
            newPrice = parsePriceToFloat(matcher.group(newPriceGroup).trim());
        } catch (ParserException e) {
            return new IncorrectCommand(e.getMessage(), ui);
        }

        return new EditPriceCommand(dishIndex, newPrice, menu, ui);
    }

    //@@author DextheChik3n
    /**
     * Parses the user input text into ingredients to form a <code>Dish</code> that is added to the <code>Menu</code>
     * @param arguments string that matches group arguments
     * @param menu Menu of the current session
     * @param ui Ui of the current session
     * @return new AddDishCommand
     */
    private static Command prepareAdd(String arguments, Menu menu, Ui ui) {
        try {
            Matcher matcher = detectErrorInPreAddParse(arguments);

            // To retrieve specific arguments from arguments
            //the dishName needs .trim() because the regex accepts whitespaces in the "name/" argument
            String dishName = matcher.group(DISH_NAME_MATCHER_GROUP_LABEL).trim();
            float dishPrice = parsePriceToFloat(matcher.group(PRICE_MATCHER_GROUP_LABEL));
            String ingredientsListString = matcher.group(INGREDIENTS_MATCHER_GROUP_LABEL);

            detectErrorPostDishNameParse(dishName, menu);

            ArrayList<Ingredient> ingredients = parseIngredients(ingredientsListString, true);

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
            throw new ParserException(ErrorMessages.REPEATED_NAME_ARGUMENT);
        } else if (isRepeatedArgument(arguments, ADD_DISH_PRICE_ARGUMENT)) {
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
            throw new ParserException(ErrorMessages.MISSING_DISH_NAME);
        } else if (isNameLengthInvalid(dishName)) {
            logger.warning("Invalid name length!");
            throw new ParserException(ErrorMessages.INVALID_DISH_NAME_LENGTH_MESSAGE);
        } else if (isRepeatedDishName(dishName, menu)) {
            logger.warning("Repeated dish!");
            throw new ParserException(ErrorMessages.REPEATED_DISH_MESSAGE);
        } else if (containsSpecialChar(dishName)) {
            throw new ParserException(ErrorMessages.NAME_CANNOT_CONTAIN_SPECIAL_CHAR);
        }
    }

    /**
     * Parses the user's input text ingredients.
     * @param ingredientsListString user's input string of ingredients, multiple ingredients seperated by ',' is allowed
     * @return list of ingredients that consists of the dish
     * @throws ParserException if the input string does not match the constraints
     * @throws NumberFormatException if the string value of the ingredient qty does not contain a parsable integer.
     */
    private static ArrayList<Ingredient> parseIngredients(
            String ingredientsListString, boolean isExcludeRepeatedIngredients)
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
            Matcher ingredientMatcher = detectErrorPreIngredientParse(inputIngredient);

            String ingredientName = ingredientMatcher.group(INGREDIENT_NAME_REGEX_GROUP_LABEL).trim();
            //ingredientQtyString contains the input text after the "qty/" argument
            String ingredientQtyString = ingredientMatcher.group(INGREDIENT_QTY_REGEX_GROUP_LABEL).trim();

            //check the formatting of ingredient qty
            final Pattern ingredientQtyFormatPattern = Pattern.compile(INGREDIENT_QTY_FORMAT_REGEX);
            Matcher ingredientQtyMatcher = ingredientQtyFormatPattern.matcher(ingredientQtyString);
            if (!ingredientQtyMatcher.matches()) {
                throw new ParserException(ErrorMessages.INVALID_INGREDIENT_QTY_FORMAT);
            }

            String ingredientUnit = ingredientQtyMatcher.group(INGREDIENT_QTY_UNIT_REGEX_GROUP_LABEL);
            int ingredientQty = Integer.parseInt(ingredientQtyMatcher.group(INGREDIENT_QTY_VALUE_REGEX_GROUP_LABEL));
            detectErrorPostIngredientParse(isExcludeRepeatedIngredients, ingredientName, ingredientQty, ingredientUnit, ingredients);

            Ingredient ingredient = new Ingredient(ingredientName, ingredientQty, ingredientUnit);
            ingredients.add(ingredient);
        }

        return ingredients;
    }

    private static Matcher detectErrorPreIngredientParse(String inputIngredient) throws ParserException {
        if (isRepeatedArgument(inputIngredient, INGREDIENT_ARGUMENT)) {
            throw new ParserException(ErrorMessages.REPEATED_INGREDIENT_ARGUMENT);
        }

        if (isRepeatedArgument(inputIngredient, QTY_ARGUMENT)) {
            throw new ParserException(ErrorMessages.REPEATED_QTY_ARGUMENT);
        }

        final Pattern ingredientPattern = Pattern.compile(INGREDIENT_ARGUMENT_FORMAT_REGEX);
        Matcher ingredientMatcher = ingredientPattern.matcher(inputIngredient);

        if (!ingredientMatcher.matches()) {
            throw new ParserException(ErrorMessages.INVALID_INGREDIENT_ARGUMENTS);
        }

        return ingredientMatcher;
    }

    private static void detectErrorPostIngredientParse(
            boolean isExcludeRepeatedIngredients, String ingredientName, int ingredientQty,
            String ingredientUnit, ArrayList<Ingredient> ingredients) throws ParserException {

        if (ingredientName.isEmpty()) {
            throw new ParserException(ErrorMessages.MISSING_INGREDIENT_NAME);
        } else if (isNameLengthInvalid(ingredientName)) {
            throw new ParserException(ErrorMessages.INVALID_INGREDIENT_NAME_LENGTH_MESSAGE);
        } else if (isInvalidQty(ingredientQty)) {
            throw new ParserException(ErrorMessages.INVALID_INGREDIENT_QTY);
        } else if (isEmptyUnit(ingredientUnit)) {
            throw new ParserException(ErrorMessages.EMPTY_UNIT_MESSAGE);
        } else if (!isValidUnit(ingredientUnit)) {
            throw new ParserException(ErrorMessages.INVALID_UNIT_MESSAGE);
        } else if (containsSpecialChar(ingredientName)) {
            throw new ParserException(ErrorMessages.NAME_CANNOT_CONTAIN_SPECIAL_CHAR);
        }

        //unusual case
        //user input repeated ingredient name for add dish command
        if (isExcludeRepeatedIngredients && isRepeatedIngredientName(ingredientName, ingredients)) {
            throw new ParserException(ErrorMessages.REPEATED_INGREDIENT_NAME);
        }
    }

    /**
     * Converts text of price to float while also checking if the price input is within reasonable range
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
            throw new ParserException(ErrorMessages.MISSING_PRICE);
        } else if (!priceMatcher.matches()) {
            throw new ParserException(ErrorMessages.WRONG_PRICE_TYPE_FOR_EDIT_PRICE);
        }

        float price;
        try {
            price = Float.parseFloat(trimmedPriceText);
        } catch (NumberFormatException e) {
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

            if (menuDishName.equals(inputDishName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks in the ingredient list if the ingredient name already exists.
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
     * @param inputText text to be checked
     * @param argument argument to be checked for multiple occurrences
     * @return true if there is > 1 match of the argument, false otherwise
     */
    public static boolean isRepeatedArgument(String inputText, String argument) {
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
    * @param menu menu of the current session
    * @param arguments string that matches group arguments
    * @return the prepared command
    */
    private static Command prepareListIngredient(Menu menu, String arguments, Ui ui) {
        final Pattern prepareListPattern = Pattern.compile(LIST_INGREDIENTS_ARGUMENT_STRING);
        Matcher matcher = prepareListPattern.matcher(arguments.trim());

        if (!matcher.matches()) {
            logger.warning("Unmatching regex!");
            return new IncorrectCommand(ErrorMessages.MISSING_ARGUMENT_FOR_LIST_INGREDIENTS
                    + ListIngredientCommand.MESSAGE_USAGE, ui);
        }

        try {
            int dishIndex = Integer.parseInt(matcher.group(1));

            if (!menu.isValidDishIndex(dishIndex)) {
                return new IncorrectCommand(ErrorMessages.UNLISTED_DISH, ui);
            }

            return new ListIngredientCommand(dishIndex, menu, ui);
        } catch (NumberFormatException e) {
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
            logger.warning("Unmatching regex!");
            return new IncorrectCommand(ErrorMessages.MISSING_ARGUMENT_FOR_DELETE, ui);
        }

        int listIndexArgGroup = 1;
        int dishIndex = Integer.parseInt(matcher.group(listIndexArgGroup));

        if (!menu.isValidDishIndex(dishIndex)) {
            return new IncorrectCommand(ErrorMessages.INVALID_DISH_INDEX, ui);
        }

        return new DeleteDishCommand(dishIndex, menu, ui);
    }

    private static Command prepareViewTotalStock(Ui ui, Pantry pantry) {
        return new ViewTotalStockCommand(pantry, ui);
    }

    private static Command prepareBuyIngredient(String arguments, Ui ui, Pantry pantry) {
        Pattern buyIngredientArgumentsPattern = Pattern.compile(BUY_INGREDIENT_ARGUMENT_STRING);
        Matcher matcher = buyIngredientArgumentsPattern.matcher(arguments.trim());

        if (!matcher.matches()) {
            logger.warning("Unmatching regex!");
            return new IncorrectCommand(ErrorMessages.MISSING_ARGUMENT_FOR_BUY_INGREDIENT
                    + BuyIngredientCommand.MESSAGE_USAGE, ui);
        }

        String ingredientsListString = matcher.group(0);

        try {
            ArrayList<Ingredient> ingredients = parseIngredients(ingredientsListString, false);
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

    //@@author ziyi105
    /**
     * Check whether a text contains special character
     * @param text text to be checked
     * @return true if it contains special character, false otherwise
     */
    public static boolean containsSpecialChar(String text) {
        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    //@@author ziyi105
    private static Command prepareHelpCommand(Ui ui) {
        return new HelpCommand(ui);
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
    private static Command prepareShowSales(Sales sale, Menu menu, Ui ui) {
        return new ListTotalSalesCommand(sale, ui, menu);
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
            int day = Integer.parseInt(matcher.group(1));
            return new ListSaleByDayCommand(day, ui, sales, menu);
        } catch (NumberFormatException e) {
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
