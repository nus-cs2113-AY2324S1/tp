package quizhub.questionlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quizhub.question.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static quizhub.question.Question.*;

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
        String input = "short Question / Answer / Mod";
        Question.qnType qnType = Question.qnType.SHORTANSWER;
        boolean showMessage = true;
        QuestionList qns = new QuestionList();
        qns.addToQuestionList(input, qnType, showMessage);
        assertEquals("I have added the following question OwO:      [S] Question / Answer    Now you have 1 questions in the list! UWU", outputStreamCaptor.toString().strip().replaceAll("\\R", ""));

    }
}