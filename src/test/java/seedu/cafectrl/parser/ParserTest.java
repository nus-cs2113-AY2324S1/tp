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
}
