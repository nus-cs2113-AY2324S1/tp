package quizhub.command;

import org.junit.jupiter.api.io.TempDir;
import quizhub.parser.Parser;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
import quizhub.ui.MockUi;
import quizhub.storage.Storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class CommandShortAnswerTest {

    private QuestionList questionList;
    private Ui ui;
    private MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        // Create a temporary file in the tempDir
        Path tempFile = tempDir.resolve("testStorage.txt");
        mockStorage = new MockStorage(tempFile.toString());
        questionList = new QuestionList();
        ui = new Ui(mockStorage, questionList);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private void testCliOutputCorrectness(String expectedOutput) {
        assert expectedOutput != null : "Expected output should not be null";
        String actualOutput = outputStreamCaptor.toString().trim();
        actualOutput = actualOutput.replace("\r", "");
        actualOutput = actualOutput.replace("\n", "");
        actualOutput = actualOutput.replace(System.lineSeparator(), "");
        actualOutput = actualOutput.replaceAll("\\s+", " "); // Replace multiple spaces with a single space
        System.out.println(expectedOutput + "\n" +actualOutput);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    @Test
    /**
     * Valid command
     * Input: Provide valid input in the format "question/answer/category/difficulty."
     * Expected Output: Verify that the application accepts the input and stores it in the local text file correctly.
     */
    public void testValidCommand(){
        String input = "short question/answer/module/easy";
        String expectedOutput = "I have added the following question OwO:" +
                " [S] question / answer | module | EASY" +
                " Now you have 1 questions in the list! UWU";
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    @Test
    /**
     * Incorrect delimiter (not using "/")
     * Input: short [question]_[answer]_[module]_[difficulty]
     */
    public void testInvalidDelimiter() {
        String input = "short question_answer_module_easy";
        String expectedOutput = CommandShortAnswer.INVALID_FORMAT_MSG.strip();
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    @Test
    /**
     * Missing one or more required fields
     * Input: short [question]/[module]/[difficulty]
     */
    public void testMissingFields(){
        String input = "short question//module/easy";
        String expectedOutput = CommandShortAnswer.MISSING_FIELDS_MSG.strip() + " " + CommandShortAnswer.INVALID_FORMAT_MSG.strip();
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    @Test
    /**
     * Additional fields or characters:
     * Input: short [question]/[answer]/[module]/[difficulty]/extra
     */
    public void testAdditionalFields(){
        String input = "short question/answer/module/easy/ADDITIONAL";
        String expectedOutput = CommandShortAnswer.TOO_MANY_ARGUMENTS_MSG.strip();
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    @Test
    /**
     * Invalid values for difficulty (assuming it has predefined values):
     * Input: short [question]/[answer]/[module]/invalid_difficulty
     */
    public void testInvalidDifficulty(){
        String input = "short question/answer/module/invalid";
        String expectedOutput = "Ono! We only support easy, normal and hard " +
                "difficulty levels" + " No changes will be made to your difficulty level" + " "
                + CommandShortAnswer.INVALID_DIFFICULTY_MSG.strip();
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    @Test
    /**
     * Duplicate Entry Handling
     * Input: Add the same question/answer combination twice.
     * Expected Output: Ensure that the application detects and handles duplicate entries, preventing them from being
     added to the file again.
     */
    public void testDuplicateHandling(){

    }

    @Test
    /**
     * File Existence
     * Input: Run the application without the local text file existing.
     * Expected Output: Verify that the application creates the file if it doesn't exist or uses an existing one
     when updating data.
     */
    public void testFileExistence(){

    }

    @Test
    /**
     * Listing questions by category
     */
    public void testListQnByModule(){

    }

    @Test
    /**
     * Listing questions by difficulty
     */
    public void testListQnByDifficulty(){

    }

    @Test
    /**
     * Test storage is updated after adding a new short answer
     */
    public void testStorageAddShortAns(){

    }

//    @Test
//    /**
//     * Test storage is updated after deleting a short answer
//     */
//    public void testStorageDeleteShortAns(){
//
//    }

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
