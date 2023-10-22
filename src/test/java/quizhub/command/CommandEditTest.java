package quizhub.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import quizhub.question.Question;
import quizhub.questionlist.QuestionList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandEditTest {
    private static QuestionList questionList;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Create a new question list and populate with dummy SHORTANSWER questions
     * */
    @BeforeAll
    public static void setQuestionList(){
        questionList = new QuestionList();
        String[] questionsToAdd = { "short Question1 / Answer1 / Mod1 / NORMAL", "short Question2 / Answer2 / Mod2 / NORMAL",
            "short Question3 / Answer3 / Mod3 / NORMAL", "short Question4 / Answer4 / Mod4 / NORMAL" };
        Question.QnType qnType = Question.QnType.SHORTANSWER;
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
     * Test editing with out-of-bound index
     * */
    @Test
    void testEditOutOfBoundIndex(){
        String expectedOutput = "Ono! Please enter valid question number *sobs*";
        questionList.editQuestionByIndex(-1, "", "");
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with non-integer index
     * */
    @Test
    void testEditNonIntIndex(){
        String expectedOutput = "Ono! Please enter valid question number *sobs*";
        questionList.editQuestionByIndex('a', "", "");
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with blank description and answer
     * */
    @Test
    void testEditBlankDescAns(){
        String expectedOutput = "Roger that! I have edited the following question >w< !\r\n" +
                "        [S][X]  /  | Mod1 | NORMAL\n" +
                "    Now you have 4 questions in the list! UWU";
        questionList.editQuestionByIndex(1, "", "");
        testCliOutputCorrectness(expectedOutput);
    }
    /**
     * Test editing with filled answer and blank description
     * */
    @Test
    void testEditOnlyBlankDesc(){
        String expectedOutput = "Roger that! I have edited the following question >w< !\r\n" +
                "        [S][]  / NewAnswer | Mod2 | NORMAL\n" +
                "    Now you have 4 questions in the list! UWU";
        questionList.editQuestionByIndex(2, "", "NewAnswer");
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with filled description and blank answer
     * */
    @Test
    void testEditOnlyBlankAns(){
        String expectedOutput = "Roger that! I have edited the following question >w< !\r\n" +
                "        [S][X] NewDescription /  | Mod3 | NORMAL\n" +
                "    Now you have 4 questions in the list! UWU";
        questionList.editQuestionByIndex(3, "NewDescription", "");
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test editing with filled description and answer
     * */
    @Test
    void testEditNonBlankDescAns(){
        String expectedOutput = "Roger that! I have edited the following question >w< !\r\n" +
                "        [S][] NewDescription / NewAnswer | Mod4 | NORMAL\n" +
                "    Now you have 4 questions in the list! UWU";
        questionList.editQuestionByIndex(4, "NewDescription", "NewAnswer");
        testCliOutputCorrectness(expectedOutput);
    }

}
