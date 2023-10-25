package quizhub.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import quizhub.question.Question;
import quizhub.questionlist.QuestionList;

public class CommandListTest {
    private QuestionList questionList;
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
        String questionToAdd = "short Question / Answer / Mod1 / EASY";
        Question.QnType qnType = Question.QnType.SHORTANSWER;
        boolean showMessage = false;
        //questionList.addToQuestionList(questionToAdd, qnType, showMessage);
        String expectedOutput = "1: [S][] Question / Answer | Mod1 | EASY";
        questionList.printQuestionList();
        String actualOutput = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test display of populated question list
     * with one marked SHORTANSWER question.
     * */
    @Test
    void testListOneMarkedShort(){
        String questionToAdd = "short Question / Answer / Mod1 / HARD";
        Question.QnType qnType = Question.QnType.SHORTANSWER;
        boolean showMessage = false;
        //questionList.addToQuestionList(questionToAdd, qnType, showMessage);
        questionList.markQuestionAsDone(1, showMessage);
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
        String[] questionsToAdd = { "short Question1 / Answer1 / Mod1 / HARD ",
            "short Question2 / Answer2 / Mod2 / HARD",
            "short Question3 / Answer3 / Mod3 / NORMAL",
            "short Question4 / Answer4 / Mod4 / EASY" };
        Question.QnType qnType = Question.QnType.SHORTANSWER;
        boolean showMessage = false;
        for (String question:questionsToAdd) {
            //questionList.addToQuestionList(question, qnType, showMessage);
        }
        questionList.markQuestionAsDone(1, showMessage);
        questionList.markQuestionAsDone(3, showMessage);
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
