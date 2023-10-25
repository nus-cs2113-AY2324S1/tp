package quizhub.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import quizhub.question.Question;
import quizhub.questionlist.QuestionList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandFindTest {
    private static QuestionList questionList;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Create a new question list and populate with dummy SHORTANSWER questions
     */
    @BeforeAll
    public static void setQuestionList() {
        questionList = new QuestionList();
        String[] questionsToAdd = { "short Question1 / Answer1 / Mod1 / NORMAL",
            "short Question2 / Answer2 / Mod2 / NORMAL",
            "short Question3 / Answer3 / Mod3 / NORMAL",
            "short Question4 / Answer4 / Mod4 / NORMAL" };
        Question.QnType qnType = Question.QnType.SHORTANSWER;
        boolean showMessage = false;
        for (String question : questionsToAdd) {
            //questionList.addToQuestionList(question, qnType, showMessage);
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
        Assertions.assertEquals(expectedOutput, actualOutput);
    }


    /*
    /**
     * Test finding with no criteria
     */
    /*
    @Test
    void testFindNoCriteria() {
        String expectedOutput = "Ono! You did not indicate if you are searching by description or module :<" +
                "\r\n    Please format your input as find /description [description] or find /module [module]!";
        testCliOutputCorrectness(expectedOutput);
    }
     */

    /*
    /**
     * Test finding by description with no keyword
     */
    /*
    @Test
    void testFindDescriptionNoKeyword() {
        String expectedOutput = "Ono! You did not indicate the keywords you are searching by :<" +
                "\r\n    Please format your input as find /description [description] or find /module [module]!";
        questionList.searchList("find /description");
        testCliOutputCorrectness(expectedOutput);
    }
    */

    /*
    /**
     * Test finding by description with matches
     */
    /*
    @Test
    void testFindDescriptionWithMatches() {
        String expectedOutput = "Here are questions that matched your search:\r\n"
                + "    1: [S][X] Question1 / Answer1 | Mod1 | NORMAL\n"
                + "    2: [S][] Question2 / Answer2 | Mod2 | NORMAL\n"
                + "    3: [S][X] Question3 / Answer3 | Mod3 | NORMAL\n"
                + "    4: [S][] Question4 / Answer4 | Mod4 | NORMAL";
        questionList.searchList("find /description Question");
        testCliOutputCorrectness(expectedOutput);
    }
     */

    /*
    /**
     * Test finding by description with no matches
     */
    /*
    @Test
    void testFindDescriptionNoMatches() {
        String expectedOutput = "Here are questions that matched your search:\r\n"
                + "    No results found :< Check your keyword is correct?";
        questionList.searchList("find /description no matches");
        testCliOutputCorrectness(expectedOutput);
    }
    */
}
