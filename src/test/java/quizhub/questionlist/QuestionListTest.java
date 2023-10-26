package quizhub.questionlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quizhub.question.Question;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionListTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void test_addToQuestionList_valid() {
        Question.QnType qnType = Question.QnType.SHORTANSWER;
        boolean showMessage = true;
        QuestionList qns = new QuestionList();
        qns.addShortAnswerQn("Question", "Answer",
                "Mod1", Question.QnDifficulty.NORMAL, true);
        assertEquals("I have added the following question OwO:" +
                "      [S] Question / Answer | Mod1 | NORMAL    Now you have 1 questions in the list! UWU",
                outputStreamCaptor.toString().strip().replaceAll("\\R", ""));

    }
}
