package quizhub.command;

import org.junit.jupiter.api.*;

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
        String[] questionsToAdd = { "short Question1 / Answer1", "short Question2 / Answer2",
                "short Question3 / Answer3", "short Question4 / Answer4" };
        Question.qnType qnType = Question.qnType.SHORTANSWER;
        boolean showMessage = false;
        for (String question : questionsToAdd) {
            questionList.addToQuestionList(question, qnType, showMessage);
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
        String actualOutput = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test finding with no criteria
     */
    @Test
    void testFindNoCriteria() {
        String expectedOutput = "Ono! You did not indicate if you are searching by description or time :<" +
                "\r\n    Please format your input as find /description [description] or find /time [time]!";
        questionList.searchList("find");
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test finding by description with no keyword
     */
    @Test
    void testFindDescriptionNoKeyword() {
        String expectedOutput = "Ono! You did not indicate the keywords you are searching by :<" +
                "\r\n    Please format your input as find /description [description] or find /time [time]!";
        questionList.searchList("find /description");
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test finding by description with matches
     */
    @Test
    void testFindDescriptionWithMatches() {
        String expectedOutput = "Here are questions that matched your search:\r\n"
                + "    1: [S][X] Question1 / Answer1\n" 
                + "    2: [S][] Question2 / Answer2\n" 
                + "    3: [S][X] Question3 / Answer3\n" 
                + "    4: [S][] Question4 / Answer4";
        questionList.searchList("find /description Question");
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test finding by description with no matches
     */
    @Test
    void testFindDescriptionNoMatches() {
        String expectedOutput = "Here are questions that matched your search:\r\n"
                + "    No results found :< Check your keyword is correct?";
        questionList.searchList("find /description no matches");
        testCliOutputCorrectness(expectedOutput);
    }
}
