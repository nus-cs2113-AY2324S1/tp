package seedu.cafectrl.parser;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.command.AddDishCommand;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;
import seedu.cafectrl.ui.UserOutput;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Junit test for Parser.java
 */
class ParserTest {

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

    @Test
    void parseCommand_validDishInputForAddDish_dishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        String testDishInputWithOneIngredient = "add name/Christmas Ham price/50.00 ingredient/Ham qty/1kg";
        Command outputCommand = Parser.parseCommand(menu, testDishInputWithOneIngredient);
        //Test for correct Command type returned
        assertTrue(outputCommand instanceof AddDishCommand);
        //Test for 1 Dish added to Menu
        outputCommand.execute(menu, ui);
        assertEquals(1, menu.getSize());
        //Test for correct parsing of dish arguments
        Dish getOutputDish = menu.getDish(0);
        assertEquals("Christmas Ham", getOutputDish.getName()); // Dish name test
        assertEquals((float) 50.0, getOutputDish.getPrice()); //Dish price test
        assertEquals("[Ham - 1kg]", getOutputDish.getIngredients().toString()); //Dish ingredients test
    }

    @Test
    void parseCommand_invalidDishInputForAddDish_noDishAddedToMenu() {
        Menu menu = new Menu();
        Ui ui = new Ui();
        //input name/ argument wrongly
        String testDishInputWithOneIngredient = "add named/Christmas Ham price/50.00 ingredient/Ham qty/1kg";
        Command outputCommand = Parser.parseCommand(menu, testDishInputWithOneIngredient);
        //Test for incorrect Command type returned
        assertFalse(outputCommand instanceof AddDishCommand);
        //Test for no dish added in menu
        outputCommand.execute(menu, ui);
        assertEquals(0, menu.getSize());
    }
}
