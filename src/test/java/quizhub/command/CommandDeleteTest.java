package quizhub.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.parser.Parser;
import quizhub.storage.MockStorage;
import quizhub.ui.Ui;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

public class CommandDeleteTest {
    private QuestionList questionList;
    private Parser parser;
    private Ui ui;
    private MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Create a new question list and populate with dummy SHORTANSWER questions
     */
    @BeforeEach
    public void setQuestionList(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("testStorage.txt");
        questionList = new QuestionList();
        parser = new Parser();
        mockStorage = new MockStorage(tempFile.toString());
        ui = new Ui(mockStorage, questionList);
        String[] questionsToAdd = { "short Question1 / Answer1 / Mod1 / NORMAL",
            "short Question2 / Answer2 / Mod2 / NORMAL",
            "short Question3 / Answer3 / Mod3 / NORMAL",
            "short Question4 / Answer4 / Mod4/ NORMAL" };
        Question.QnType qnType = Question.QnType.SHORTANSWER;
        boolean showMessage = false;
        for (String question : questionsToAdd) {
            Parser.parseCommand(question).executeCommand(ui, mockStorage, questionList);
        }
        questionList.markQuestionAsDone(1, showMessage);
        questionList.markQuestionAsDone(3, showMessage);
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
        System.out.println(expectedOutput + "\n" +actualOutput);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test deleting with out-of-bound index
     */
    @Test
    void testDeleteOutOfBoundIndex() {
        String expectedOutput = "Ono! Please enter valid question number *sobs*";
        questionList.deleteQuestionByIndex(-1);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test deleting with invalid type index
     */
    @Test
    void testDeleteInvalidTypeIndex() {
        String expectedOutput = Ui.INVALID_COMMAND_MSG + System.lineSeparator() +
                        Parser.INVALID_INTEGER_INDEX_MSG + System.lineSeparator() +
                        CommandDelete.INVALID_FORMAT_MSG;
        parser.parseCommand("delete test").executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput.strip());
    }

    /**
     * Test deleting with missing index
     */
    @Test
    void testDeleteMissingIndex() {
        String expectedOutput = Ui.INVALID_COMMAND_MSG + System.lineSeparator() +
                CommandDelete.MISSING_INDEX_MSG + System.lineSeparator() +
                CommandDelete.INVALID_FORMAT_MSG;
        parser.parseCommand("delete").executeCommand(ui, mockStorage, questionList);;
        testCliOutputCorrectness(expectedOutput.strip());
    }

    // /**
    // * Test deleting with valid index
    // * */
    @Test
    void testDeleteValidIndex() {
        String expectedOutput = "Roger that! I have deleted the following question >w< !\r\n" +
            "        [S][X] Question1 / Answer1 | Mod1 | NORMAL\n" +
            "    Now you have 3 questions in the list! UWU";
        questionList.deleteQuestionByIndex(1);
        testCliOutputCorrectness(expectedOutput);
    }
}
