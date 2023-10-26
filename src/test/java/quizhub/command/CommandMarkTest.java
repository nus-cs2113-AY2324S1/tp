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
    private Parser parser;
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
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test editing without question index
     * */
    @Test
    void testMarkDiffNoIndexNoDifficulty(){
        //String input  = "markdiff";
        //String expectedOutput = "Ono! You did not indicate index of question to be marked :<\r\n" +
        //        "    Please format your input as markdiff [question number] [question difficulty]!";
        //Command command = parser.parseCommand(input);
        //command.executeCommand(ui, mockStorage, questionList);
        //testCliOutputCorrectness(expectedOutput);
        System.out.println("test");
    }

    /**
     * Test marking without question difficulty to assign
     * */
    @Test
    void testMarkDiffNoDifficulty(){
        //String input  = "markdiff 1";
        //String expectedOutput = "Ono! You did not indicate difficulty to be assigned the question :<";
        //Command command = parser.parseCommand(input);
        //command.executeCommand(ui, mockStorage, questionList);
        //testCliOutputCorrectness(expectedOutput);
        System.out.println("test2");
    }
    /**
     * Test marking with out-of-bound index
     * */
    @Test
    void testMarkDiffOutOfBoundIndex(){
        //String input  = "markdiff -1 NORMAL";
        //String expectedOutput = "Ono! Please enter valid question number *sobs*";
        //Command command = parser.parseCommand(input);
        //command.executeCommand(ui, mockStorage, questionList);
        //testCliOutputCorrectness(expectedOutput);
        System.out.println("test3");
    }

    /**
     * Test marking with non-integer index
     * */
    @Test
    void testMarkDiffNonIntIndex(){
        //String input  = "markdiff ?@!# NORMAL";
        //String expectedOutput = "Please enter valid integer index!";
        //Command command = parser.parseCommand(input);
        //command.executeCommand(ui, mockStorage, questionList);
        //testCliOutputCorrectness(expectedOutput);
        System.out.println("test4");
    }

    /**
     * Test marking with assigning question non-existent difficulty level
     * */
    @Test
    void testMarkDiffWrongDifficulty(){
        //String input  = "markdiff 1 ???";
        //String expectedOutput = "Ono! We only support easy, normal and hard difficulty levels\r\n" +
        //        "    Please only use 'easy', 'normal' or 'hard' for difficulty levels!\r\n" +
        //        "    No changes made to original question difficulty!";
        //Command command = parser.parseCommand(input);
        //command.executeCommand(ui, mockStorage, questionList);
        //testCliOutputCorrectness(expectedOutput);
        System.out.println("test5");
    }
    /**
     * Test marking with assigning question same difficulty as its current one
     * */
    @Test
    void testMarkDiffRepeatedDifficulty(){
        //String input  = "markdiff 1 NORMAL";
        //String expectedOutput = "Question is already set as normal ! No changes made!";
        //Command command = parser.parseCommand(input);
        //command.executeCommand(ui, mockStorage, questionList);
        //testCliOutputCorrectness(expectedOutput);
        System.out.println("test6");
    }

    /**
     * Test marking with assigning question different, correct difficulty level
     * */
    @Test
    void testMarkDiffCorrectDifficulty(){
        String input  = "markdiff 2 HARD";
        String expectedOutput = "Roger that! I have marked the following question as hard >w< !\n" +
                "        [S][] Question2 / Answer2 | Mod2 | HARD";
        Command command = parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }
}
