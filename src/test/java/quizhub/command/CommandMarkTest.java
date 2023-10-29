package quizhub.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;
import quizhub.parser.Parser;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.storage.MockStorage;
import quizhub.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

public class CommandMarkTest {
    private QuestionList questionList;
    private Ui ui;
    private MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    /**
     * Create a new question list and populate with dummy SHORTANSWER questions
     * */
    @BeforeEach
    public void setQuestionList(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("testStorage.txt");
        questionList = new QuestionList();
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
     * */
    private void testCliOutputCorrectness(String expectedOutput){
        String actualOutput = outputStreamCaptor.toString().trim();
        actualOutput = actualOutput.replace("\r", "");
        actualOutput = actualOutput.replace("\n", "");
        actualOutput = actualOutput.replace(System.lineSeparator(), "");
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    /**
     * Test marking without question index and difficulty level
     * */
    @Test
    void testMarkDiffNoIndexNoDifficulty(){
        String input  = "markdiff";
        String expectedOutput = CommandMarkDifficulty.MISSING_INDEX_MSG.strip()
                + CommandMarkDifficulty.INVALID_FORMAT_MSG;
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking without question difficulty to assign
     * */
    @Test
    void testMarkDiffNoDifficulty(){
        String input  = "markdiff 1";
        String expectedOutput = CommandMarkDifficulty.MISSING_DIFFICULTY_MSG.strip()
                + CommandMarkDifficulty.INVALID_FORMAT_MSG;
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with out-of-bound index
     * */
    @Test
    void testMarkDiffOutOfBoundIndex(){
        String input  = "markdiff -1 /NORMAL";
        String expectedOutput = Ui.INVALID_INTEGER_INDEX_MSG.strip()
                + CommandMarkDifficulty.INVALID_FORMAT_MSG;
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with non-number index
     * */
    @Test
    void testMarkDiffNonNumIndex(){
        String input  = "markdiff ?@!# /NORMAL";
        String expectedOutput = Ui.INVALID_INTEGER_INDEX_MSG.strip()
                + CommandMarkDifficulty.INVALID_FORMAT_MSG;
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with non-integer index
     * */
    @Test
    void testMarkDiffNonIntIndex(){
        String input  = "markdiff 3.14 /NORMAL";
        String expectedOutput = Ui.INVALID_INTEGER_INDEX_MSG.strip()
                + CommandMarkDifficulty.INVALID_FORMAT_MSG;
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with multiple index
     * */
    @Test
    void testMarkDiffMultipleIndex(){
        String input  = "markdiff 1 23 111 /NORMAL";
        String expectedOutput = CommandMarkDifficulty.TOO_MANY_INDEX_MSG.strip()
                + CommandMarkDifficulty.INVALID_FORMAT_MSG;
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with assigning question non-existent difficulty level
     * */
    @Test
    void testMarkDiffWrongDifficulty(){
        String input  = "markdiff 1 /???";
        String expectedOutput = Ui.INVALID_QUESTION_DIFFICULTY_MSG.strip()
                .replace(System.lineSeparator(), "");
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with assigning question multiple difficulty levels using a single argument /
     * */
    @Test
    void testMarkDiffMultipleDifficultySingleArgument(){
        String input  = "markdiff 1 /NORMAL hard EASY";
        String expectedOutput = CommandMarkDifficulty.TOO_MANY_DIFFICULTY_MSG.strip()
                + CommandMarkDifficulty.INVALID_FORMAT_MSG;
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with assigning question multiple difficulty levels using multiple argument /
     * */
    @Test
    void testMarkDiffMultipleDifficultyMultipleArgument(){
        String input  = "markdiff 1 /NORMAL /HaRd /eASY";
        String expectedOutput = CommandMarkDifficulty.TOO_MANY_DIFFICULTY_MSG.strip()
                + CommandMarkDifficulty.INVALID_FORMAT_MSG;
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with assigning question same difficulty as its current one
     * */
    @Test
    void testMarkDiffRepeatedDifficulty(){
        String input  = "markdiff 1 /NORMAL";
        String expectedOutput = "Question is already set as normal ! No changes made!";
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test marking with assigning question different, correct difficulty level
     * */
    @Test
    void testMarkDiffCorrectDifficulty(){
        String input  = "markdiff 2 /HARD";
        String expectedOutput = "Roger that! I have marked the following question as hard >w< !" +
                "        [S][] Question2 / Answer2 | Mod2 | HARD";
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
}
