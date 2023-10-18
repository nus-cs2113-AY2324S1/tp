package seedu.cafectrl.parser;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.command.Command;

import seedu.cafectrl.command.IncorrectCommand;
import seedu.cafectrl.command.ListIngredientCommand;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;
import seedu.cafectrl.ui.UserOutput;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Junit test for Parser.java
 */
class ParserTest {
    @Test
    public void parseCommand_validCommand_successfulCommandParse() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice",
                new ArrayList<>(Arrays.asList(new Ingredient("Rice", "1 cup"),
                        new Ingredient("Chicken", "100g"))), 8.0F));
        menuItems.add(new Dish("Chicken Sandwich",
                new ArrayList<>(Arrays.asList(new Ingredient("Lettuce", "100g"),
                        new Ingredient("Chicken", "50g"))), 5.0F));
        Menu menu = new Menu(menuItems);

        String userInput = "list_ingredients 1";
        Command result = Parser.parseCommand(menu, userInput);

        assertTrue(result instanceof ListIngredientCommand);

        ListIngredientCommand listIngredientCommand = (ListIngredientCommand) result;
        int index = listIngredientCommand.index;
        assertEquals(1, index);
    }

    @Test
    public void parseCommand_missingIndex_returnsErrorMessage() {
        Menu menu = new Menu();
        String userInput = "list_ingredients";
        Command result = Parser.parseCommand(menu, userInput);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(Messages.MISSING_ARGUMENT_FOR_LIST_INGREDIENTS, feedbackToUser);
    }

    @Test
    public void parseCommand_invalidIndex_returnsErrorMessage() {
        Menu menu = new Menu();
        String userInput = "list_ingredients a";
        Command result = Parser.parseCommand(menu, userInput);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(Messages.MISSING_ARGUMENT_FOR_LIST_INGREDIENTS, feedbackToUser);
    }

    @Test
    public void parseCommand_indexOutOfBounds_returnsErrorMessage() {
        Menu menu = new Menu();
        String userInput = "list_ingredients 1";
        Command result = Parser.parseCommand(menu, userInput);

        assertTrue(result instanceof IncorrectCommand);

        IncorrectCommand incorrectCommand = (IncorrectCommand) result;
        String feedbackToUser = incorrectCommand.feedbackToUser;
        assertEquals(Messages.INVALID_DISH_INDEX, feedbackToUser);
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

        Command commandReturned = Parser.parseCommand(menu, testUserInput);
        commandReturned.execute(menu, ui);
        assertEquals(UserOutput.UNKNOWN_COMMAND_MESSAGE.message, actualOutput.get(0));
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

        Command commandReturned = Parser.parseCommand(menu, testUserInput);
        commandReturned.execute(menu, ui);
        assertEquals(Messages.MISSING_ARGUMENT_FOR_EDIT_PRICE, actualOutput.get(0));
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

        Command commandReturned = Parser.parseCommand(menu, testUserInput);
        commandReturned.execute(menu, ui);
        assertEquals(Messages.INVALID_DISH_INDEX, actualOutput.get(0));
    }
}
