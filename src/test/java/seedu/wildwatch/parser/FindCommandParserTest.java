//@@woodenclock
package seedu.wildwatch.parser;

import org.junit.jupiter.api.Test;
import seedu.wildwatch.command.FindCommand;
import seedu.wildwatch.error.InvalidInputErrorType;
import seedu.wildwatch.exception.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FindCommandParserTest {
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_success() throws InvalidInputException {
        String input;
        FindCommand expected;
        FindCommand actual;

        // finding a date
        input = "find 02-15-2023";
        expected = new FindCommand("02-15-2023");

        actual = parser.parse(input);
        assertEquals(actual, expected);

        // finding a species
        input = "find Annam Leaf Turtle";
        expected = new FindCommand("Annam Leaf Turtle");

        actual = parser.parse(input);
        assertEquals(actual, expected);

        // finding a name
        input = "find Ariel";
        expected = new FindCommand("Ariel");

        actual = parser.parse(input);
        assertEquals(actual, expected);

        // finding a remark
        input = "find Injured Left Flipper";
        expected = new FindCommand("Injured Left Flipper");

        actual = parser.parse(input);
        assertEquals(actual, expected);
    }

    @Test
    public void parse_exceptionThrown() {

        // empty find command
        final String testInput1 = "    find   ";
        InvalidInputException exception1 = assertThrows(InvalidInputException.class, () -> parser.parse(testInput1));
        assertEquals(exception1.getError(), InvalidInputErrorType.EMPTY_FIND_INPUT);
    }
}
