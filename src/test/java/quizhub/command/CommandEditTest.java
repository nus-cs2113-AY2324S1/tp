package quizhub.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.io.TempDir;
import quizhub.questionlist.QuestionList;
import quizhub.parser.Parser;
import quizhub.storage.MockStorage;
import quizhub.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

public class CommandEditTest {
    private static QuestionList questionList;
    private static Ui ui;
    private static MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Create a new question list and populate with dummy SHORTANSWER questions
     * */
    @BeforeAll
    public static void setQuestionList(@TempDir Path tempDir) {
        Path tempFile = tempDir.resolve("testStorage.txt");
        questionList = new QuestionList();
        mockStorage = new MockStorage(tempFile.toString());
        ui = new Ui(mockStorage, questionList);
        String[] questionsToAdd = { "short Question1 / Answer1 / Mod1 / NORMAL",
            "short Question2 / Answer2 / Mod2 / NORMAL",
            "short Question3 / Answer3 / Mod3 / NORMAL",
            "short Question4 / Answer4 / Mod4 / NORMAL" };
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
     * */
    private void testCliOutputCorrectness(String expectedOutput){
        String actualOutput = outputStreamCaptor.toString().trim();
        actualOutput = actualOutput.replace("\r", "");
        actualOutput = actualOutput.replace("\n", "");
        actualOutput = actualOutput.replace(System.lineSeparator(), "");
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test editing with out-of-bound index
     * */
    @Test
    void testEditOutOfBoundIndex(){
        String expectedOutput = Ui.INVALID_INTEGER_INDEX_MSG.strip() + CommandEdit.INVALID_FORMAT_MSG;
        String userInput = "edit -1 /description NewDescription";
        Parser.parseCommand(userInput).executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with non-integer index
     * */
    @Test
    void testEditNonIntIndex(){
        String expectedOutput = Ui.INVALID_INTEGER_INDEX_MSG.strip() + CommandEdit.INVALID_FORMAT_MSG;
        String userInput = "edit abc /description NewDescription";
        Parser.parseCommand(userInput).executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with blank description and answer
     * */
    @Test
    void testEditBlankDes(){
        String expectedOutput = CommandEdit.MISSING_KEYWORD_MSG.strip() + CommandEdit.INVALID_FORMAT_MSG;
        String userInput = "edit 1 /description ";
        Parser.parseCommand(userInput).executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test editing with filled answer and blank description
     * */
    @Test
    void testEditBlankAns(){
        String expectedOutput = CommandEdit.MISSING_KEYWORD_MSG.strip() + CommandEdit.INVALID_FORMAT_MSG;
        String userInput = "edit 1 /answer ";
        Parser.parseCommand(userInput).executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with filled description and blank answer
     * */
    @Test
    void testEditNoIndex(){
        String expectedOutput = CommandEdit.MISSING_INDEX_MSG.strip() + CommandEdit.INVALID_FORMAT_MSG;
        String userInput = "edit ";
        Parser.parseCommand(userInput).executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with filled description and answer
     * */
    @Test
    void testEditValidDescription(){
        String expectedOutput = "Roger that! I have edited the following question >w< !" +
                "        [S][X] NewDescription / Answer3 | Mod3 | NORMAL" +
                "    Now you have 4 questions in the list! UWU";
        String userInput = "edit 3 /description NewDescription";
        Parser.parseCommand(userInput).executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with filled description and answer
     * */
    @Test
    void testEditValidAnswer(){
        String expectedOutput = "Roger that! I have edited the following question >w< !" +
                "        [S][] Question4 / NewAnswer | Mod4 | NORMAL" +
                "    Now you have 4 questions in the list! UWU";
        String userInput = "edit 4 /answer NewAnswer";
        Parser.parseCommand(userInput).executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
}
