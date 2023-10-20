package quizhub.command;

import org.junit.jupiter.api.*;

import quizhub.question.Question;
import quizhub.questionlist.QuestionList;
import quizhub.parser.Parser;
import quizhub.ui.Ui;
import quizhub.storage.Storage;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CommandDeleteTest {
    private QuestionList questionList;
    private Parser parser;
    private Ui ui;
    private MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Create a new question list and populate with dummy SHORTANSWER questions
     */
    @BeforeEach
    public void setQuestionList(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("testStorage.txt");
        questionList = new QuestionList();
        parser = new Parser();
        mockStorage = new MockStorage(tempFile.toString());
        ui = new Ui(mockStorage, questionList);
        String[] questionsToAdd = { "short Question1 / Answer1", "short Question2 / Answer2",
                "short Question3 / Answer3", "short Question4 / Answer4" };
        Question.qnType qnType = Question.qnType.SHORTANSWER;
        boolean showMessage = false;
        for (String question : questionsToAdd) {
            questionList.addToQuestionList(question, qnType, showMessage);
        }
        questionList.markQuestionAsDone(1, showMessage);
        questionList.markQuestionAsDone(3, showMessage);
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
     * Test deleting with out-of-bound index
     */
    @Test
    void testDeleteOutOfBoundIndex() {
        String expectedOutput = "Ono! Please enter valid question number *sobs*";
        questionList.deleteQuestionByIndex(-1);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test deleting with invalid type index
     */
    @Test
    void testDeleteInvalidTypeIndex() {
        String expectedOutput = "Please enter a valid command :0\r\n" +
        "    Please enter valid integer index!";
        parser.parseCommand("delete test").executeCommand(ui, mockStorage, questionList);;
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Test deleting with missing index
     */
    @Test
    void testDeleteMissingIndex() {
        String expectedOutput = "Please enter a valid command :0\r\n" +
        "    Please enter valid integer index!";
        parser.parseCommand("delete").executeCommand(ui, mockStorage, questionList);;
        testCliOutputCorrectness(expectedOutput);
    }

    // /**
    // * Test deleting with valid index
    // * */
    @Test
    void testDeleteValidIndex() {
        String expectedOutput = "Roger that! I have deleted the following question >w< !\r\n" +
                "        [S][X] Question1 / Answer1\n" +
                "    Now you have 3 questions in the list! UWU";
        questionList.deleteQuestionByIndex(1);
        testCliOutputCorrectness(expectedOutput);
    }

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
