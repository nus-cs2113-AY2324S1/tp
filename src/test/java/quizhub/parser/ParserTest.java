package quizhub.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quizhub.command.Command;
import quizhub.command.CommandDelete;
import quizhub.command.CommandInvalid;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private QuestionList questionList;
    private Parser parser;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        parser = new Parser(questionList);
    }

    /**
     * Test command parser with empty commands (invalid commands)
     * */
    @Test
    void test_parseCommand_emptyCommand() {
        final String[] emptyInputs = { "", "  ", "\n  \n" };
        final String resultMessage = Ui.INVALID_COMMAND_MSG + System.lineSeparator() + Ui.INVALID_COMMAND_FEEDBACK;
        parseAndAssertIncorrectWithMessage(resultMessage, emptyInputs);
    }

    @Test
    void test_parseCommand_invalidIntegerCommand() {
        final String[] invalidIntegers = {
            "delete system32",
            "delete 1111111111111111111111111111111111111111111111111"
        };
        final String resultMessage = Ui.INVALID_INTEGER_INDEX_MSG +
                System.lineSeparator() + CommandDelete.INVALID_FORMAT_MSG;
        parseAndAssertIncorrectWithMessage(resultMessage, invalidIntegers);
    }

    /*
    @Test
    void test_parseCommand_commandShortAnswer() {
        final String validInput = "short What's 9 + 10 / 21";
        CommandShortAnswer result = parseAndAssertCommandType(validInput, CommandShortAnswer.class);
        assertEquals("short What's 9 + 10 / 21", result.getUserInput());
    }
     */

    /**
     * Test list of INVALID inputs and validate their feedback
     * This function is adapted from ADDRESS_BOOK_LEVEL_2
     * @param feedback The target feedback message for invalid command
     * @param inputs The invalid inputs that would generate the same feedback
     * */
    private void parseAndAssertIncorrectWithMessage(String feedback, String[] inputs) {
        for (String input : inputs) {
            final CommandInvalid result = parseAndAssertCommandType(input, CommandInvalid.class);
            assertEquals(feedback, result.feedback);
        }
    }

    /**
     * Parses and tests the command type returned
     * This function is adapted from ADDRESS_BOOK_LEVEL_2
     * @param input The command input to be tested
     * @param expectedClass The expected command class
     * */
    private <T extends Command> T parseAndAssertCommandType(String input, Class<T> expectedClass) {
        final Command result = Parser.parseCommand(input);
        assertTrue(result.getClass().isAssignableFrom(expectedClass));
        return (T) result;
    }
}
