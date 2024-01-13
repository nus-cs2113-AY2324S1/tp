package seedu.wildwatch.parser;

import org.junit.jupiter.api.Test;
import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.command.AddCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.error.InvalidInputErrorType;

public class AddCommandParserTest {
    private final AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_success() throws Exception {
        String input;
        AddCommand expected;
        AddCommand actual;

        // add command with remark
        input = "add D/02-03-2023 S/Annam Leaf Turtle N/Ariel R/Injured left flipper";
        expected = new AddCommand(
                new Entry("02-03-2023",
                        "Annam Leaf Turtle",
                        "Ariel",
                        "Injured left flipper"));

        actual = parser.parse(input);
        assertEquals(actual, expected);

        // add command without remark
        input = "add D/02-03-2023 S/Annam Leaf Turtle N/Ariel";
        expected = new AddCommand(
                new Entry("02-03-2023",
                        "Annam Leaf Turtle",
                        "Ariel",
                        ""));

        actual = parser.parse(input);
        assertEquals(actual, expected);

        // add command with spacings between prefix and values
        input = "add D/ 02-03-2023 S/ Annam Leaf Turtle N/ Ariel R/ Injured left flipper";
        expected = new AddCommand(
                new Entry("02-03-2023",
                        "Annam Leaf Turtle",
                        "Ariel",
                        "Injured left flipper"));

        actual = parser.parse(input);
        assertEquals(actual, expected);

        // add command with extra spacings after definitions
        input = "add   D/  02-03-2023   S/  Annam Leaf Turtle   N/  Ariel   R/  Injured left flipper  ";
        expected = new AddCommand(
                new Entry("02-03-2023",
                        "Annam Leaf Turtle",
                        "Ariel",
                        "Injured left flipper"));

        actual = parser.parse(input);
        assertEquals(actual, expected);

        // add command with remark tag specified but left blank
        input = "add D/02-03-2023 S/Annam Leaf Turtle N/Ariel R/";
        expected = new AddCommand(
                new Entry("02-03-2023",
                        "Annam Leaf Turtle",
                        "Ariel",
                        ""));

        actual = parser.parse(input);
        assertEquals(actual, expected);
    }

    @Test
    public void parse_exceptionThrown() throws Exception {

        // D/ prefix is not present
        final String testInput1 = "add S/Annam Leaf Turtle N/Ariel R/Injured left flipper";
        InvalidInputException exception1 = assertThrows(InvalidInputException.class, () -> parser.parse(testInput1));
        assertEquals(exception1.getError(), InvalidInputErrorType.CUSTOM);
        assertEquals(exception1.getCustomMessage(), "D/ is not defined");

        // date is not specified
        final String testInput2 = "add D/ S/Annam Leaf Turtle N/Ariel R/Injured left flipper";
        InvalidInputException exception2 = assertThrows(InvalidInputException.class, () -> parser.parse(testInput2));
        assertEquals(exception2.getError(), InvalidInputErrorType.CUSTOM);
        assertEquals(exception2.getCustomMessage(), "Date value cannot be empty.");

        // date is not in a correct format
        final String testInput3 = "add D/1 S/Annam Leaf Turtle N/Ariel R/Injured left flipper";
        InvalidInputException exception3 = assertThrows(InvalidInputException.class, () -> parser.parse(testInput3));
        assertEquals(exception3.getError(), InvalidInputErrorType.INVALID_DATE);
        assertEquals(exception3.getCustomMessage(), "Command is invalid.");

        // S/ prefix is not present
        final String testInput4 = "add D/02-03-2023 N/Ariel R/Injured left flipper";
        InvalidInputException exception4 = assertThrows(InvalidInputException.class, () -> parser.parse(testInput4));
        assertEquals(exception4.getError(), InvalidInputErrorType.CUSTOM);
        assertEquals(exception4.getCustomMessage(), "S/ is not defined");

        // species is not specified
        final String testInput5 = "add D/02-03-2023 S/ N/Ariel R/Injured left flipper";
        InvalidInputException exception5 = assertThrows(InvalidInputException.class, () -> parser.parse(testInput5));
        assertEquals(exception5.getError(), InvalidInputErrorType.CUSTOM);
        assertEquals(exception5.getCustomMessage(), "Species is not defined.");

        // N/ prefix is not present
        final String testInput6 = "add D/02-03-2023 S/Annam Leaf Turtle R/Injured left flipper";
        InvalidInputException exception6 = assertThrows(InvalidInputException.class, () -> parser.parse(testInput6));
        assertEquals(exception6.getError(), InvalidInputErrorType.CUSTOM);
        assertEquals(exception6.getCustomMessage(), "N/ is not defined");

        // name is not specified
        final String testInput7 = "add D/02-03-2023 S/Annam Leaf Turtle N/ R/Injured left flipper";
        InvalidInputException exception7 = assertThrows(InvalidInputException.class, () -> parser.parse(testInput7));
        assertEquals(exception7.getError(), InvalidInputErrorType.CUSTOM);
        assertEquals(exception7.getCustomMessage(), "Name is not defined");

    }
}
