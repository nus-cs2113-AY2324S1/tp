package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit test class for FindParser.
 * This class tests if the find command is parsed correctly.
 * It also tests if the correct exceptions are thrown when the command is invalid.
 * The following are the parameters that can be parsed:
 *
 * /t <type> /cat <category> /de <description> /date <date>
 *  - type is mandatory
 *  - category, description and date are optional
 *  - type can be either "expense" or "income"
 *  - category food, transport, utilities
 *  - description can be any string
 *  - date must be in the format "dd/MM/yyyy"
 *  - date must be a valid date
 *  - date cannot be in the future
 *
 */
public class FindParserTest {
    /**
     * This method tests if the find command is parsed correctly.
     * Specifically, it tests if the parameters are parsed correctly.
     * @throws KaChinnnngException if the command format is invalid
     */
    @Test
    public void validFindCommand_parsedCorrectly() throws KaChinnnngException{
        String[] expected = {"expense", "food", "lunch", "12/Oct/2023"};
        String[] result = FindParser.parseFindCommand("/t expense /cat food /de lunch /date 12/Oct/2023");
        assertArrayEquals(expected, result);
    }

    /**
     * This method tests if the correct exception is thrown when the type is missing.
     */
    @Test
    public void missingMandatoryTypeField_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/cat food /de lunch /date 12/Oct/2023");
        });
    }

    /**
     * This method tests if the correct exception is thrown when the type is invalid.
     * Specifically, it tests if the correct exception is thrown when the type is not "expense" or "income".
     */
    @Test
    public void usingInvalidParameter_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/t expense /cat food /de lunch /date 12/Oct/2023 /invalid");
        });
    }

    /**
     * Tests if the correct exception is thrown when the full word for type is used instead of the shortcut.
     */
    @Test
    public void usingFullWordTypeInsteadOfShortcut_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/type expense /cat food /de lunch /date 12/Oct/2023");
        });
    }

    /**
     * Tests if the correct exception is thrown when only the mandatory type parameter is provided,
     * and all other optional parameters are missing.
     */
    @Test
    public void missingAllOptionalFields_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/t expense");
        });
    }

    /**
     * Tests if the correct exception is thrown when a parameter is provided without its corresponding value.
     */
    @Test
    public void missingValueForParameter_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/t ");
        });
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/t expense /cat ");
        });
    }

    /**
     * Tests the correct parsing of a valid find command with the "income" type and no category.
     * Expects successful parsing into an array of parameters.
     *
     * @throws KaChinnnngException if the command format is invalid
     */
    @Test
    public void validFindCommandWithIncomeType_noCategoryParsed() throws KaChinnnngException {
        String[] expected = {"income", null, "bonus", "15/Oct/2023"};
        String[] result = FindParser.parseFindCommand("/t income /de bonus /date 15/Oct/2023");
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the correct parsing of a valid find command with only type and category.
     * Expects successful parsing into an array of parameters.
     *
     * @throws KaChinnnngException if the command format is invalid
     */
    @Test
    public void validFindCommandWithOnlyTypeAndCategory_parsedCorrectly() throws KaChinnnngException {
        String[] expected = {"expense", "food", null, null};
        String[] result = FindParser.parseFindCommand("/t expense /cat food");
        assertArrayEquals(expected, result);
    }

    /**
     * Tests if the correct exception is thrown when deprecated parameter names are used.
     */
    @Test
    public void usingOldParameterNames_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/t expense /description value");
        });
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/t expense /category food");
        });
    }

    /**
     * Tests if the correct exception is thrown for an empty command string.
     */
    @Test
    public void emptyCommand_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("");
        });
    }

    /**
     * Tests if the correct exception is thrown for a command string consisting only of spaces.
     */
    @Test
    public void commandWithOnlySpaces_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("    ");
        });
    }

    /**
     * Tests if the correct exception is thrown for a command containing a parameter that doesn't exist.
     */
    @Test
    public void findCommandWithNonexistentParameter_throwsException() {
        assertThrows(KaChinnnngException.class, () -> {
            FindParser.parseFindCommand("/t expense /nonexistent value");
        });
    }
}
