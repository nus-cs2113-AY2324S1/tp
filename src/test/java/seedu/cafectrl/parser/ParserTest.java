package seedu.cafectrl.parser;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.command.AddDishCommand;
import seedu.cafectrl.command.DeleteDishCommand;
import seedu.cafectrl.command.ListIngredientCommand;
import seedu.cafectrl.command.ListSaleByDayCommand;
import seedu.cafectrl.command.ListTotalSalesCommand;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.command.IncorrectCommand;
import seedu.cafectrl.command.ViewTotalStockCommand;

import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.parser.exception.ParserException;
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
    public void parseCommand_validListIngredientsCommand_successfulCommandParse() {
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

        String userInput = "list_ingredients dish/1";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof ListIngredientCommand);

        ListIngredientCommand listIngredientCommand = (ListIngredientCommand) result;
        int index = listIngredientCommand.index;
        assertEquals(1, index);
    }

    @Test
    public void parseCommand_missingListIngredientsIndex_returnsErrorMessage() {
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
        assertEquals(ErrorMessages.MISSING_ARGUMENT_FOR_LIST_INGREDIENTS + ListIngredientCommand.MESSAGE_USAGE,
                feedbackToUser);
    }

    @Test
    public void parseCommand_invalidListIngredientsIndex_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_ingredients dish/a";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_DISH_INDEX_TO_LIST, feedbackToUser);
    }

    @Test
    public void parseCommand_listIngredientIndexOutOfBounds_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_ingredients dish/1";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.UNLISTED_DISH, feedbackToUser);
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
    public void parseCommand_notIntDeleteIndex_returnsErrorMessage() {
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
        assertEquals(ErrorMessages.DISH_INDEX_NOT_INT, feedbackToUser);
    }

    @Test
    public void parseCommand_invalidDeleteIndex_returnsErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "delete -1";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_DISH_INDEX, feedbackToUser);
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

    //@@author ziyi105
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
        String testUserInput = "edit_price dish/1";

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
    void parseCommand_missingArgumentsTypeForEditPrice_wrongArgMsg() {
        Menu menu = new Menu();
        Dish testDish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(testDish);
        String testUserInput = "edit_price dish/ price/4";

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
        assertEquals(ErrorMessages.MISSING_DISH_IN_EDIT_PRICE, actualOutput.get(0));
    }

    @Test
    void parseCommand_outOfBoundDishIndexForEditPrice_invalidIndexForEditPrice() {
        Menu menu = new Menu();
        Dish testDish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(testDish);
        String testUserInput = "edit_price dish/2 price/3";

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

    @Test
    void parseCommand_nonDigitDishIndexForEditPrice_invalidIndexForEditPrice() {
        Menu menu = new Menu();
        Dish testDish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(testDish);
        String testUserInput = "edit_price dish/d price/3";

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
        assertEquals(ErrorMessages.WRONG_DISH_INDEX_TYPE_FOR_EDIT_PRICE, actualOutput.get(0));
    }

    //@@author DextheChik3n
    @Test
    void parseCommand_noArgumentsUserInput_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        Parser parser = new Parser();

        String testInput = "12345";

        Command outputCommand = parser.parseCommand(menu, testInput, ui, pantry, sales, currentDate);

        assertTrue(outputCommand instanceof IncorrectCommand);
    }

    @Test
    void parseCommand_dishWithOneIngredientForAddDish_dishAddedToMenu() {
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

        assertEquals("christmas ham", getOutputDish.getName());
        assertEquals((float) 50.0, getOutputDish.getPrice());
        assertEquals("[ham - 1000g]", getOutputDish.getIngredients().toString());
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

        assertEquals("chicken rice", getOutputDish.getName());
        assertEquals((float) 2.0, getOutputDish.getPrice());
        assertEquals("[rice - 100g, chicken - 200g, water - 100ml]", getOutputDish.getIngredients().toString());
    }

    @Test
    void parseCommand_invalidArgumentInputForAddDish_incorrectCommand() {
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
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_missingArgumentDishInputForAddDish_incorrectCommand() {
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
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_invalidQuantityUnitForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/Chicken Rice price/2.50 ingredient/rice qty/1 cup";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_negativeDishPriceForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/Chicken Rice price/-2.50 ingredient/rice qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_negativeIngredientQtyForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/Chicken Rice price/2.50 ingredient/rice qty/-100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_emptyDishNameForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/ price/2.50 ingredient/rice qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_tooLongDishNameForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/this dish name is probably too long for the test to pass oh noes "
                + "price/2.50 ingredient/rice qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_specialCharInDishNameForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/Ch1k3m R!ce price/2.50 ingredient/rice qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_repeatedDishNameForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        Ingredient ingredient = new Ingredient("rice");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        menu.addDish(new Dish("chicken rice", ingredients, (float) 2.13));

        String addDishTestInput = "add name/Chicken Rice price/2.50 ingredient/rice qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(1, menu.getSize());
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

        assertEquals("christmas ham", getOutputDish.getName());
        assertEquals((float) 50.0, getOutputDish.getPrice());
        assertEquals("[ham - 1000g]", getOutputDish.getIngredients().toString());
    }

    @Test
    void parseCommand_repeatedIngredientArgumentForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/chicken rice price/2.50 ingredient/rice ingredient/rice qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_repeatedQtyArgumentForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/chicken rice price/2.50 ingredient/rice qty/100g qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_missingIngredientNameForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/chicken rice price/2.50 ingredient/chicken qty/300g, ingredient/ qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_veryLongIngredientNameForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/chicken rice price/2.50 ingredient/chicken qty/300g "
                + ", ingredient/this ingredient name is too long to fit inside the menu table qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_invalidIngredientQtyForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        //Test for negative ingredient qty
        String addDishTestInput = "add name/chicken rice price/2.50 ingredient/chicken qty/300g "
                + ", ingredient/rice qty/-100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());

        //Test for more than max limit ingredient qty
        addDishTestInput = "add name/chicken rice price/2.50 ingredient/chicken qty/300g "
                + ", ingredient/rice qty/99999999g";
        outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_missingIngredientQtyUnitForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/chicken rice price/2.50 ingredient/chicken qty/300 "
                + ", ingredient/rice qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_specialCharInIngredientNameForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/chicken rice price/2.50 ingredient/chicken qty/300g "
                + ", ingredient/r!ce qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void parseCommand_repeatedIngredientNameInputForAddDish_incorrectCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String addDishTestInput = "add name/chicken rice price/2.50 ingredient/chicken qty/300g "
                + ", ingredient/chicken qty/100g";
        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, addDishTestInput, ui, pantry, sales, currentDate);

        //Test for incorrect Command type returned
        assertTrue(outputCommand instanceof IncorrectCommand);

        //Test for no dish added in menu
        outputCommand.execute();
        assertEquals(0, menu.getSize());
    }

    @Test
    void isRepeatedArgument_noRepeatArgument_false() {
        String inputString = "name/christmas Ham price/50.00 ingredient/Ham qty/1000g";
        String inputArgument = "price/";
        assertFalse(Parser.isRepeatedArgument(inputString, inputArgument));
    }

    @Test
    void isRepeatedArgument_repeatArgument_true() {
        String inputString = "name/christmas Ham price/50.00 price/12.00 ingredient/Ham qty/1000g";
        String inputArgument = "price/";
        assertTrue(Parser.isRepeatedArgument(inputString, inputArgument));
    }

    @Test
    void isRepeatedArgument_emptyString_true() {
        String inputString = "";
        String inputArgument = "price/";
        assertFalse(Parser.isRepeatedArgument(inputString, inputArgument));
    }

    @Test
    void isRepeatedArgument_emptyArgument_true() {
        String inputString = "name/christmas Ham price/50.00 price/12.00 ingredient/Ham qty/1000g";
        String inputArgument = "";
        assertTrue(Parser.isRepeatedArgument(inputString, inputArgument));
    }

    @Test
    void isRepeatedArgument_nullInput_true() throws NullPointerException {
        String inputString = "name/christmas Ham price/50.00 price/12.00 ingredient/Ham qty/1000g";
        String inputArgument = "price/";
        assertThrows(NullPointerException.class, () -> Parser.isRepeatedArgument(null, inputArgument));
        assertThrows(NullPointerException.class, () -> Parser.isRepeatedArgument(inputString, null));
    }

    @Test
    void parsePriceToFloat_validPriceString_exactFloatValue() throws ParserException {
        String inputPriceString = "3.1";

        assertEquals((float) 3.1, Parser.parsePriceToFloat(inputPriceString));
    }

    @Test
    void parsePriceToFloat_largePriceString_parserExceptionThrown() {
        String inputPriceString = "99999999999.99";

        assertThrows(ParserException.class, () -> Parser.parsePriceToFloat(inputPriceString));
    }

    @Test
    void parsePriceToFloat_moreThanTwoDPPriceString_parserExceptionThrown() {
        String inputPriceString = "1.9999";

        assertThrows(ParserException.class, () -> Parser.parsePriceToFloat(inputPriceString));
    }

    @Test
    void parsePriceToFloat_whitespaceInPriceString_exactFloatValue() throws ParserException {
        String inputPriceString = " 1.99 ";

        assertEquals((float) 1.99, Parser.parsePriceToFloat(inputPriceString));
    }

    @Test
    void parsePriceToFloat_negativePriceString_parserExceptionThrown() {
        String inputPriceString = "-1.99";

        assertThrows(ParserException.class, () -> Parser.parsePriceToFloat(inputPriceString));
    }

    @Test
    void parsePriceToFloat_emptyPriceString_parserExceptionThrown() {
        String inputPriceString = "";

        assertThrows(ParserException.class, () -> Parser.parsePriceToFloat(inputPriceString));
    }

    @Test
    void isRepeatedDishName_existingDishName_true() {
        Menu menu = new Menu();
        Dish dish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(dish);

        String inputDishName = "chicken rice";

        assertTrue(Parser.isRepeatedDishName(inputDishName, menu));
    }

    @Test
    void isRepeatedDishName_nonExistingDishName_false() {
        Menu menu = new Menu();
        Dish dish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(dish);

        String inputDishName = "chicken chop";

        assertFalse(Parser.isRepeatedDishName(inputDishName, menu));
    }

    @Test
    void isRepeatedDishName_nullString_nullPointerExceptionThrown() throws NullPointerException {
        Menu menu = new Menu();
        Dish dish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(dish);

        assertThrows(NullPointerException.class, () -> Parser.isRepeatedDishName(null, menu));
    }

    @Test
    void isRepeatedDishName_emptyDishName_false() {
        Menu menu = new Menu();
        Dish dish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(dish);

        String inputDishName = "";

        assertFalse(Parser.isRepeatedDishName(inputDishName, menu));
    }

    @Test
    void isRepeatedIngredientName_nonExistingIngredientName_false() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("rice");
        ingredients.add(ingredient);

        assertFalse(Parser.isRepeatedIngredientName("apple", ingredients));
    }

    @Test
    void isRepeatedIngredientName_existingIngredientName_true() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("rice");
        ingredients.add(ingredient);

        assertTrue(Parser.isRepeatedIngredientName("rice", ingredients));
    }

    @Test
    void isRepeatedIngredientName_nullIngredientName_nullPointerExceptionThrown() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> Parser.isRepeatedIngredientName(null, ingredients));
    }

    @Test
    void isNameLengthInvalid_moreThanMaxLengthString_true() {
        assertTrue(Parser.isNameLengthInvalid("this string is more than 35 characters"));
    }

    @Test
    void isNameLengthInvalid_lessThanMaxLengthString_false() {
        assertFalse(Parser.isNameLengthInvalid("this str is less than 35 chars"));
    }

    @Test
    void isNameLengthInvalid_nullString_nullPointerExceptionThrown() throws NullPointerException {
        assertThrows(NullPointerException.class, () ->Parser.isNameLengthInvalid(null));
    }

    //@@author ShaniceTang
    @Test
    void parseCommand_returnViewTotalStockCommandClass() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String userInput = "view_stock";

        ParserUtil parserUtil = new Parser();
        Command outputCommand = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        ViewTotalStockCommand viewTotalStockCommand = new ViewTotalStockCommand(pantry, ui);

        assertEquals(viewTotalStockCommand.getClass(), outputCommand.getClass());
    }

    @Test
    void parseCommand_missingArgsForBuyIngredient_returnErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();

        String userInput = "buy_ingredient";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_INGREDIENT_ARGUMENTS, feedbackToUser);
    }

    @Test
    void parseCommand_invalidArgsForBuyIngredient_returnErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "buy_ingredient ingredient/rice qty/5 cups";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_UNIT_MESSAGE, feedbackToUser);
    }

    @Test
    void isValidUnit_invalidUnit_returnFalse() {
        assertFalse(Parser.isValidUnit("kg"));
    }

    @Test
    void isEmptyUnit_emptyUnit_returnTrue() {
        assertTrue(Parser.isEmptyUnit(""));
    }

    @Test
    void checkForMismatchUnit_mismatchingUnit_throwParserException() {
        Menu menu = new Menu();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("chicken", 500, "g"));
        Dish dish = new Dish("chicken rice", ingredients, 3.00F);
        menu.addDish(dish);

        Ingredient ingredient = new Ingredient("chicken", 500, "ml");

        assertThrows(ParserException.class, () -> Parser.checkForMismatchUnit(menu, ingredient));
    }
    //@@author

    @Test
    void parseCommand_listTotalSalesCommand_returnInstanceOfListTotalSalesCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_total_sales";
        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof ListTotalSalesCommand);
    }

    @Test
    void parseCommand_validListSaleIndex_returnInstanceOfListSaleByDayCommand() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_sale day/1";

        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof ListSaleByDayCommand);

        ListSaleByDayCommand listSaleByDayCommand = (ListSaleByDayCommand) result;
        int day = listSaleByDayCommand.getDay();
        assertEquals(1, day);
    }

    @Test
    void parseCommand_invalidListSaleIndex_showErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_sale day/a";

        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_DAY_FORMAT, feedbackToUser);
    }

    @Test
    void parseCommand_missingListSaleIndex_showErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_sale day/";

        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_SHOW_SALE_DAY_FORMAT_MESSAGE
                + ListSaleByDayCommand.MESSAGE_USAGE, feedbackToUser);
    }

    @Test
    void parseCommand_invalidListSaleFormat_showErrorMessage() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        Pantry pantry = new Pantry(ui);
        Sales sales = new Sales();
        CurrentDate currentDate = new CurrentDate();
        String userInput = "list_sale /1";

        ParserUtil parserUtil = new Parser();
        Command result = parserUtil.parseCommand(menu, userInput, ui, pantry, sales, currentDate);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(ErrorMessages.INVALID_SHOW_SALE_DAY_FORMAT_MESSAGE
                + ListSaleByDayCommand.MESSAGE_USAGE, feedbackToUser);
    }
}
