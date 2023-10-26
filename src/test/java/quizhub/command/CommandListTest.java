package quizhub.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import org.junit.jupiter.api.io.TempDir;
import quizhub.parser.Parser;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.storage.MockStorage;
import quizhub.ui.Ui;

public class CommandListTest {
    private static QuestionList questionList;
    private static Ui ui;
    private static MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        questionList = new QuestionList();
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    /**
     * Compares system standard output with expected output.
     * Assertion error is thrown if the two are not the same.
     * */
    private void testCliOutputCorrectness(String expectedOutput){
        String actualOutput = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test display of empty question list.
     * */
    @Test
    void testListEmptyList(){
        String expectedOutput = "No questions found! Time to add some OWO";
        questionList.printQuestionList();
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test display of populated question list
     * with one unmarked SHORTANSWER question.
     * */
    @Test
    void testListOneUnmarkedShort(){
        String expectedOutput = "1: [S][] Question / Answer | Mod1 | EASY";
        questionList.addShortAnswerQn("Question", "Answer", "Mod1",
                Question.QnDifficulty.EASY, false);
        questionList.printQuestionList();
        String actualOutput = outputStreamCaptor.toString().strip();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test display of populated question list
     * with one marked SHORTANSWER question.
     * */
    @Test
    void testListOneMarkedShort(){
        questionList.addShortAnswerQn("Question", "Answer", "Mod1",
                Question.QnDifficulty.HARD, false);
        questionList.markQuestionAsDone(1, false);
        String expectedOutput = "1: [S][X] Question / Answer | Mod1 | HARD";
        questionList.printQuestionList();
        String actualOutput = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test display of populated question list
     * with a mix of marked and unmarked SHORTANSWER questions.
     * */
    @Test
    void testListMixedShorts(){
        questionList.addShortAnswerQn("Question1", "Answer1", "Mod1",
                Question.QnDifficulty.HARD, false);
        questionList.addShortAnswerQn("Question2", "Answer2", "Mod2",
                Question.QnDifficulty.HARD, false);
        questionList.addShortAnswerQn("Question3", "Answer3", "Mod3",
                Question.QnDifficulty.NORMAL, false);
        questionList.addShortAnswerQn("Question4", "Answer4", "Mod4",
                Question.QnDifficulty.EASY, false);
        questionList.markQuestionAsDone(1, false);
        questionList.markQuestionAsDone(3, false);
        String expectedOutput =
            "1: [S][X] Question1 / Answer1 | Mod1 | HARD\n" +
            "    2: [S][] Question2 / Answer2 | Mod2 | HARD\n" +
            "    3: [S][X] Question3 / Answer3 | Mod3 | NORMAL\n" +
            "    4: [S][] Question4 / Answer4 | Mod4 | EASY" ;
        questionList.printQuestionList();
        String actualOutput = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

}
