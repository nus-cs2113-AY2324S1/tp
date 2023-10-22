package quizhub.command;

import org.junit.jupiter.api.io.TempDir;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
import quizhub.question.Question;
import quizhub.storage.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StartTest {

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
        assertEquals("    No questions found! Add questions before starting the quiz.",
                mockUi.getLastDisplayedMessage());
    }

    @Test
    public void testAddQuestionToListAndStorage() {
        // Add a question to the question list
        questionList.addToQuestionList("short What is 2 + 2?/4 / MA1511 / EASY", Question.QnType.SHORTANSWER, false);

        // Store the question in the mock storage
        mockStorage.saveData(questionList.toString());

        // Retrieve questions from the mock storage (without clearing the list)
        mockStorage.loadData(questionList);

        // Verify that the question was added to the list and retrieved from storage
        assertEquals(1, questionList.getQuestionListSize());
        // assertEquals("short What is 2 + 2?/4", questionList.getQuestionTextByIndex(1));
    }

    @Test
    public void testStartQuizWithQuestions() {
        // Add some questions to the question list
        questionList.addToQuestionList("short What is 2 + 2?/4 / MA1511 / EASY", Question.QnType.SHORTANSWER, false);
        questionList.addToQuestionList("short What is 3 + 3?/6 / MA1511 / EASY", Question.QnType.SHORTANSWER, false);

        // Set up user input for the quiz one by one
        mockUi.setUserInput("4");
        mockUi.setUserInput("6");

        // Start the quiz
        questionList.startQuiz(mockUi);

        // Verify that the expected messages are displayed
        assertEquals("    Starting the quiz...", mockUi.getLastDisplayedMessage());
        //assertEquals("Correct!", mockUi.getLastDisplayedMessage()); // Verify that "Correct!" is displayed
        //for both questions
        //assertEquals("Quiz completed!", mockUi.getLastDisplayedMessage());
        //assertEquals("Your score: 2/2", mockUi.getLastDisplayedMessage()); // Verify the final score message
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

        @Override
        public void displayMessage(String message) {
            lastDisplayedMessage = message;
        }

        public String getLastDisplayedMessage() {
            return lastDisplayedMessage;
        }
    }

    // MockStorage class for testing, using in-data memory
    public class MockStorage extends Storage {
        private List<String> questions = new ArrayList<>();

        public MockStorage(String filepath) {
            super(filepath);
        }

        public void saveData(String dataToAdd) {
            questions.add(dataToAdd);
        }

        public String loadData() {
            // In-memory storage, retrieve data from the list
            if (questions.isEmpty()) {
                return "";
            }
            // Concatenate the data with line breaks
            StringBuilder result = new StringBuilder();
            for (String line : questions) {
                result.append(line).append(System.lineSeparator());
            }
            return result.toString().trim();
        }

        public boolean dataExists() {
            return !questions.isEmpty();
        }

        public void clearData() {
            questions.clear();
        }
    }

}
