package quizhub.parser;

import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
import quizhub.question.Question;
import quizhub.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.io.TempDir;

import java.util.LinkedList;
import java.util.Queue;

import java.io.IOException;
import java.nio.file.Path;

public class QuestionListTest {

    private QuestionList questionList;
    private UiMock mockUi;
    private MockStorage mockStorage;

    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        // Create a temporary file in the tempDir
        Path tempFile = tempDir.resolve("testStorage.txt");
        mockStorage = new MockStorage(tempFile.toString()); // Pass the temporary file path
        questionList = new QuestionList();
        mockUi = new UiMock(questionList);
    }

    @Test
    public void testStartQuizWithNoQuestions() {
        // Ensure the quiz doesn't start if there are no questions
        questionList.startQuiz(mockUi);
        assertEquals("No questions found! Add questions before starting the quiz.", mockUi.getLastDisplayedMessage());
    }

    @Test
    public void testStartQuizWithQuestions() {
        // Add some questions to the question list
        questionList.addToQuestionList("short What is 2 + 2?/4", Question.qnType.SHORTANSWER, false);
        questionList.addToQuestionList("short What is 3 + 3?/6", Question.qnType.SHORTANSWER, false);

        // Set up user input for the quiz one by one
        mockUi.setUserInput("4");
        mockUi.setUserInput("6");

        // Start the quiz
        questionList.startQuiz(mockUi);

        // Verify that the expected messages are displayed
        assertEquals("Starting the quiz...", mockUi.getLastDisplayedMessage());
        assertEquals("Correct!", mockUi.getLastDisplayedMessage()); // Verify that "Correct!" is displayed for both questions
        assertEquals("Quiz completed!", mockUi.getLastDisplayedMessage());
        assertEquals("Your score: 2/2", mockUi.getLastDisplayedMessage()); // Verify the final score message
    }

    // A simple mock class for Ui
    public class UiMock extends Ui {
        private final Queue<String> userInputQueue = new LinkedList<>();
        private String lastDisplayedMessage;

        public UiMock(QuestionList tasks) {
            super(mockStorage, tasks);
        }

        public void setUserInput(String input) {
            userInputQueue.add(input);
        }

        public String getUserInput() {
            if (userInputQueue.isEmpty()) {
                throw new RuntimeException("No more user inputs provided.");
            }
            return userInputQueue.poll();
        }

        public void displayMessage(String message) {
            lastDisplayedMessage = message;
        }

        public String getLastDisplayedMessage() {
            return lastDisplayedMessage;
        }
    }

    // MockStorage class for testing
    private class MockStorage extends Storage {
        private String data = "";

        public MockStorage(String filePath) {
            super(filePath);
        }

        public void saveData(String data) {
            this.data = data;
        }

        public String loadData() {
            return data;
        }

        public boolean dataExists() {
            return !data.isEmpty();
        }

        public void clearData() {
            data = "";
        }
    }
}
