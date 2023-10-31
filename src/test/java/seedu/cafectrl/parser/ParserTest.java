package seedu.cafectrl.parser;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.command.AddDishCommand;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.command.DeleteDishCommand;
import seedu.cafectrl.command.IncorrectCommand;
import seedu.cafectrl.command.ListIngredientCommand;
import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Junit test for Parser.java
 */
class ParserTest {
    @Test
    public void parseCommand_validCommand_successfulCommandParse() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice",
                new ArrayList<>(Arrays.asList(new Ingredient("Rice", 50, "g"),
                        new Ingredient("Chicken", 100, "g"))), 8.0F));
        menuItems.add(new Dish("Chicken Sandwich",
                new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", 100, "g"),
                        new Ingredient("Chicken", 50, "g"))), 5.0F));
        Menu menu = new Menu(menuItems);
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String userInput = "list_ingredients 1";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof ListIngredientCommand);

        ListIngredientCommand listIngredientCommand = (ListIngredientCommand) result;
        int index = listIngredientCommand.index;
        assertEquals(1, index);
    }

    @Test
    public void parseCommand_missingIndex_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_ingredients";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.MISSING_ARGUMENT_FOR_LIST_INGREDIENTS, feedbackToUser);
    }

    @Test
    public void parseCommand_invalidIndex_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_ingredients a";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.MISSING_ARGUMENT_FOR_LIST_INGREDIENTS, feedbackToUser);
    }

    @Test
    public void parseCommand_indexOutOfBounds_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_ingredients 1";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_DISH_INDEX, feedbackToUser);
    }

    @Test
    public void parseCommand_validDeleteCommand_successfulCommandParse() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice",
                new ArrayList<>(Arrays.asList(new Ingredient("Rice", 50, "g"),
                        new Ingredient("Chicken", 100, "g"))), 8.0F));
        menuItems.add(new Dish("Chicken Sandwich",
                new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", 100, "g"),
                        new Ingredient("Chicken", 50, "g"))), 5.0F));
        Menu menu = new Menu(menuItems);
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String userInput = "delete 1";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);
        assertTrue(result instanceof DeleteDishCommand);

        DeleteDishCommand deleteDishCommand = (DeleteDishCommand) result;
        int index = deleteDishCommand.index;
        assertEquals(1, index);
    }

    @Test
    public void parseCommand_missingDeleteIndex_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "delete";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.MISSING_ARGUMENT_FOR_DELETE, feedbackToUser);
    }

    @Test
    public void parseCommand_invalidDeleteIndex_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "delete a";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.MISSING_ARGUMENT_FOR_DELETE, feedbackToUser);
    }

    @Test
    public void parseCommand_deleteIndexOutOfBounds_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "delete 1";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_DISH_INDEX, feedbackToUser);
    }

    @Test
    void parseCommand_unrecognisedInput_unknownCommand() {
        Menu menu = new Menu();
        Dish testDish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(testDish);
        String testUserInput = "random input";

        ArrayList<String> actualOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        ParserUtil parserUtil = new Parser();
        Command commandReturned = parserUtil.parseCommand(menu, testUserInput, ui, pantry, sales, currentDate);
        commandReturned.execute();
        assertEquals(ErrorMessages.UNKNOWN_COMMAND_MESSAGE, actualOutput.get(0));
    }

    @Test
    void parseCommand_missingArgumentsForEditPrice_missingArgMsg() {
        Menu menu = new Menu();
        Dish testDish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(testDish);
        String testUserInput = "edit_price index/1";

        ArrayList<String> actualOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        ParserUtil parserUtil = new Parser();
        Command commandReturned = parserUtil.parseCommand(menu, testUserInput, ui, pantry, sales, currentDate);
        commandReturned.execute();
        assertEquals(ErrorMessages.MISSING_ARGUMENT_FOR_EDIT_PRICE, actualOutput.get(0));
    }

    @Test
    void parseCommand_invalidDishIndexForEditPrice_invalidIndexForEditPrice() {
        Menu menu = new Menu();
        Dish testDish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(testDish);
        String testUserInput = "edit_price index/2 price/3";

        ArrayList<String> actualOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        ParserUtil parserUtil = new Parser();
        Command commandReturned = parserUtil.parseCommand(menu, testUserInput, ui, pantry, sales, currentDate);
        commandReturned.execute();
        assertEquals(ErrorMessages.INVALID_DISH_INDEX, actualOutput.get(0));
    }
    //@@author DextheChik3n
    @Test
    void parseCommand_validDishInputForAddDish_dishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String addDishTestInput = "add name/Christmas Ham price/50.00 ingredient/Ham qty/1000g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for correct Command type returned
        assertTrue(outputCommand instanceof AddDishCommand);

        //Test for 1 Dish added to Menu
        outputCommand.execute();
        assertEquals(1, menu.getSize());

        //Test for correct parsing of dish arguments
        Dish getOutputDish = menu.getDishFromId(0);

        assertEquals("Christmas Ham", getOutputDish.getName());
        assertEquals((float) 50.0, getOutputDish.getPrice());
        assertEquals("[Ham - 1000g]", getOutputDish.getIngredients().toString());
    }

    @Test
    void parseCommand_dishWithThreeIngredientsForAddDish_dishContainsThreeIngredientAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String addDishTestInput = "add name/Chicken Rice price/2.00 "
                + "ingredient/rice qty/100g, ingredient/chicken qty/200g, ingredient/water qty/100ml";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for 3 Ingredients in the Dish added to Menu
        outputCommand.execute();
        Dish actualDish = menu.getDishFromId(0);
        assertEquals(3, actualDish.getIngredients().size());

        //Test for correct parsing of dish arguments
        Dish getOutputDish = menu.getDishFromId(0);

        assertEquals("Chicken Rice", getOutputDish.getName());
        assertEquals((float) 2.0, getOutputDish.getPrice());
        assertEquals("[rice - 100g, chicken - 200g, water - 100ml]", getOutputDish.getIngredients().toString());
    }

    @Test
    void parseCommand_invalidDishInputForAddDish_noDishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        //input name/ argument wrongly
        String addDishTestInput = "add named/Christmas Ham price/50.00 ingredient/Ham qty/1000g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertFalse(outputCommand instanceof AddDishCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_missingArgumentDishInputForAddDish_noDishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        //input name/ argument wrongly
        String addDishTestInput = "add name/Christmas Ham price/50.00 ingredient/Ham";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertFalse(outputCommand instanceof AddDishCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_invalidQuantityUnitForAddDish_noDishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/Chicken Rice price/2.50 ingredient/rice qty/1 cup";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertFalse(outputCommand instanceof AddDishCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_negativeDishPriceForAddDish_noDishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/Chicken Rice price/-2.50 ingredient/rice qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertFalse(outputCommand instanceof AddDishCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_negativeIngredientQtyForAddDish_noDishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/Chicken Rice price/2.50 ingredient/rice qty/-100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertFalse(outputCommand instanceof AddDishCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_whitespaceBetweenArgumentsForAddDish_dishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String addDishTestInput = "add name/ Christmas Ham price/ 50.00 ingredient/ Ham qty/ 1000g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for correct Command type returned
        assertTrue(outputCommand instanceof AddDishCommand);

        //Test for 1 Dish added to Menu
        outputCommand.execute();
        assertEquals(1, menu.getSize());

        //Test for correct parsing of dish arguments
        Dish getOutputDish = menu.getDishFromId(0);

        assertEquals("Christmas Ham", getOutputDish.getName());
        assertEquals((float) 50.0, getOutputDish.getPrice());
        assertEquals("[Ham - 1000g]", getOutputDish.getIngredients().toString());
    }

    @Test
    void parsePriceToFloat_validPriceString_exactFloatPrice() {
        String inputPriceString = "3.14";

        assertEquals((float) 3.14, Parser.parsePriceToFloat(inputPriceString));
    }

    @Test
    void parsePriceToFloat_largePriceString_arithmeticExceptionThrown() throws ArithmeticException {
        String inputPriceString = "99999999999.99";

        assertThrows(ArithmeticException.class, () -> Parser.parsePriceToFloat(inputPriceString));
    }
    //@@author
}
