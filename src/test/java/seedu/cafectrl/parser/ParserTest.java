package seedu.cafectrl.parser;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;
import seedu.cafectrl.ui.UserOutput;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit test for Parser.java
 */
class ParserTest {

    @Test
    void parseCommand_unrecognisedInput_unknownCommand () {
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
}
