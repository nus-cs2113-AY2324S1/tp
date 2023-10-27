package quizhub.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;
import quizhub.parser.Parser;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.storage.MockStorage;
import quizhub.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

public class CommandFindTest {
    private static QuestionList questionList;
    private static Ui ui;
    private static MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Create a new question list and populate with dummy SHORTANSWER questions
     */
    @BeforeAll
    public static void setQuestionList(@TempDir Path tempDir) {
        Path tempFile = tempDir.resolve("testStorage.txt");
        questionList = new QuestionList();
        mockStorage = new MockStorage(tempFile.toString());
        ui = new Ui(mockStorage, questionList);
        String[] questionsToAdd = { "short Question1 / Answer1 / Mod1 / NORMAL",
            "short Question2 / Answer2 / Mod2 / NORMAL",
            "short Question3 / Answer3 / Mod3 / NORMAL",
            "short Question4 / Answer4 / Mod4 / NORMAL"
        };
        Question.QnType qnType = Question.QnType.SHORTANSWER;
        boolean showMessage = false;
        for (String question:questionsToAdd) {
            Parser.parseCommand(question).executeCommand(ui, mockStorage, questionList);
        }
        questionList.markQuestionAsDone(1, showMessage);
        questionList.markQuestionAsDone(3, showMessage);
    }

    @BeforeEach
    public void setUpOutput() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    /**
     * Compares system standard output with expected output
     * Assertion error is thrown if the two are not the same
     */
    private void testCliOutputCorrectness(String expectedOutput) {
        assert expectedOutput != null : "Expected output should not be null";
        String actualOutput = outputStreamCaptor.toString().trim();
        actualOutput = actualOutput.replace("\r", "");
        actualOutput = actualOutput.replace("\n", "");
        actualOutput = actualOutput.replace(System.lineSeparator(), "");
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test finding with no criteria
     */
    @Test
    void testFindNoCriteria() {
        String expectedOutput = Ui.INVALID_COMMAND_MSG + 
                CommandFind.MISSING_CRITERIA_MSG + 
                CommandFind.INVALID_FORMAT_MSG;
        Parser.parseCommand("find").executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput.strip());
    }

    /**
     * Test finding by description with no keyword
     */
    @Test
    void testFindDescriptionNoKeyword() {
        String expectedOutput = Ui.INVALID_COMMAND_MSG + 
                CommandFind.MISSING_KEYWORD_MSG + 
                CommandFind.INVALID_FORMAT_MSG;
        Parser.parseCommand("find /description").executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput.strip());
    }

    /**
     * Test finding by description with matches
     */
    @Test
    void testFindDescriptionWithMatches() {
        String expectedOutput = "Here are questions that matched your search:"
                + "    1: [S][X] Question1 / Answer1 | Mod1 | NORMAL"
                + "    2: [S][] Question2 / Answer2 | Mod2 | NORMAL"
                + "    3: [S][X] Question3 / Answer3 | Mod3 | NORMAL"
                + "    4: [S][] Question4 / Answer4 | Mod4 | NORMAL";
        Parser.parseCommand("find /description Question").executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test finding by description with no matches
     */

    @Test
    void testFindDescriptionNoMatches() {
        String expectedOutput = "Here are questions that matched your search:"
                + "    No results found :< Check your keyword is correct?";
        Parser.parseCommand("find /description no matches").executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test finding by module with no keyword
     */
    @Test
    void testFindModuleNoKeyword() {
        String expectedOutput = Ui.INVALID_COMMAND_MSG + 
                CommandFind.MISSING_KEYWORD_MSG + 
                CommandFind.INVALID_FORMAT_MSG;
        Parser.parseCommand("find /module").executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput.strip());
    }

    /**
     * Test finding by module with matches
     */ 
    @Test
    void testFindModuleWithMatches() {
        String expectedOutput = "Here are questions that matched your search:"
                + "    1: [S][X] Question1 / Answer1 | Mod1 | NORMAL"
                + "    2: [S][] Question2 / Answer2 | Mod2 | NORMAL"
                + "    3: [S][X] Question3 / Answer3 | Mod3 | NORMAL"
                + "    4: [S][] Question4 / Answer4 | Mod4 | NORMAL";
        Parser.parseCommand("find /module Mod").executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test finding by module with no matches
     */
    @Test
    void testFindModuleNoMatches() {
        String expectedOutput = "Here are questions that matched your search:"
                + "    No results found :< Check your module is correct?";
        Parser.parseCommand("find /module no matches").executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
}
