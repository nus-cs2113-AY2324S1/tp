package quizhub.command;

import org.junit.jupiter.api.*;

import quizhub.question.Question;
import quizhub.questionlist.QuestionList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandDeleteTest {
    private static QuestionList questionList;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Create a new question list and populate with dummy SHORTANSWER questions
     * */
    @BeforeAll
    public static void setQuestionList(){
        questionList = new QuestionList();
        String[] questionsToAdd = {"short Question1 / Answer1", "short Question2 / Answer2",
                "short Question3 / Answer3", "short Question4 / Answer4"};
        Question.qnType qnType = Question.qnType.SHORTANSWER;
        boolean showMessage = false;
        for (String question:questionsToAdd) {
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
     * */
    private void testCliOutputCorrectness(String expectedOutput){
        String actualOutput = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Test deleting with out-of-bound index
     * */
    @Test
    void testDeleteOutOfBoundIndex(){
        String expectedOutput = "Ono! Please enter valid question number *sobs*";
        questionList.deleteQuestionByIndex(-1);
        testCliOutputCorrectness(expectedOutput);
    }

    // /**
    //  * Test deleting with valid index
    //  * */
    // @Test
    // void testDeleteValidIndex(){
    //     String expectedOutput = "Roger that! I have deleted the following question >w< !\r\n" +
    //             "        [S][X] Question1 / Answer1\r\n" +
    //             "    Now you have 3 questions in the list! UWU";
    //     questionList.deleteQuestionByIndex(1);
    //     testCliOutputCorrectness(expectedOutput);
    // }

}
