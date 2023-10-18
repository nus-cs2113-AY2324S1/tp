package seedu.cafectrl.parser;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.command.Command;
import seedu.cafectrl.command.IncorrectCommand;
import seedu.cafectrl.data.Menu;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit test for Parser.java
 */
class ParserTest {

    @Test
    void parseCommand_missingArg_returnIncorrectCommandObject() {
        Menu menu = new Menu();

        String fullUserInput = "delete";
        Command command = Parser.parseCommand(menu, fullUserInput);

        assertEquals(IncorrectCommand.class, command.getClass());
    }

}
