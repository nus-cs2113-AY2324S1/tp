package quizhub.command;

import org.junit.jupiter.api.io.TempDir;
import quizhub.parser.Parser;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;

import quizhub.storage.MockStorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
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
    /**
     * Valid command
     * Input: Provide valid input in the format "question/answer/category/difficulty."
     * Expected Output: Verify that the application accepts the input and stores it in the local text file correctly.
     */
    @Test
    public void testValidCommand(){
        String input = "short question/answer/module/easy";
        String expectedOutput = "I have added the following question OwO:" +
                " [S] question / answer | module | EASY" +
                " Now you have 1 questions in the list! UWU";
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
    }

    /**
     * Incorrect delimiter (not using "/")
     * Input: short [question]_[answer]_[module]_[difficulty]
     */
    @Test
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
    public void testDuplicateHandling() {
        // Define the input short answer question
        String input = "short duplicate_question/duplicate_answer/module/easy";

        // Add the question once
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);

        // Attempt to add the same question again
        command.executeCommand(ui, mockStorage, questionList);

        // Expected output after adding the question once
        String expectedOutputOnce = "I have added the following question OwO:" +
                " [S] duplicate_question / duplicate_answer | module | EASY" +
                " Now you have 1 questions in the list! UWU";

        // Expected output after attempting to add the same question again
        String expectedOutputDuplicate = CommandShortAnswer.DUPLICATED_INPUT.strip();

        // Capture and trim actual output
        String actualOutput = outputStreamCaptor.toString().trim();

        // Remove multiple spaces and newlines for consistent comparison
        actualOutput = actualOutput.replaceAll("\\s+", " ");

        // Check if the first addition was successful
        Assertions.assertTrue(actualOutput.contains(expectedOutputOnce));

        // Check if the second addition was handled as a duplicate
        Assertions.assertTrue(actualOutput.contains(expectedOutputDuplicate));
    }

    @Test
    /**
     * File Existence
     * Input: Run the application without the local text file existing.
     * Expected Output: Verify that the application creates the file if it doesn't exist or uses an existing one
     * when updating data.
     */
    public void testFileExistence() {
        // Ensure the storage is initially empty
        mockStorage.clearData();

        // Define the input command
        String input = "short new_question/new_answer/module/easy";

        // Execute the command (this should trigger file creation)
        Command command = Parser.parseCommand(input);
        command.executeCommand(ui, mockStorage, questionList);

        // Expected output after adding the question and creating the file
        String expectedOutput = "I have added the following question OwO:" +
                " [S] new_question / new_answer | module | EASY" +
                " Now you have 1 questions in the list! UWU";

        // Verify that the data was saved to the storage (file created)
        Assertions.assertTrue(mockStorage.dataExists());

        // Verify the output
        testCliOutputCorrectness(expectedOutput);
    }

    @Test
    /**
    * Test storage is updated after deleting a short answer
    */
    public void testStorageDeleteShortAns() {
        // Create a QuestionList and add some short answer questions
        QuestionList questionList = new QuestionList();

        // Format questions to add
        String[] questionsToAdd = {
                "short Question1 / Answer1 / Mod1 / NORMAL",
                "short Question2 / Answer2 / Mod2 / NORMAL",
                "short Question3 / Answer3 / Mod3 / NORMAL",
                "short Question4 / Answer4 / Mod4 / NORMAL"
        };

        // Initialize MockStorage with the QuestionList
        MockStorage mockStorage = new MockStorage("testStorage.txt");

        // Add questions to MockStorage and QuestionList
        for (String question : questionsToAdd) {
            Command command = Parser.parseCommand(question);
            command.executeCommand(ui, mockStorage, questionList);
        }

        // Delete a short answer question from the QuestionList
        questionList.deleteQuestionByIndex(2);

        // Update the storage with the modified QuestionList
        mockStorage.updateData(questionList);

        // Verify that the storage reflects the changes
        String storageData = mockStorage.loadData();
        String expectedStorageData = "S | undone | Question1 / Answer1 | Mod1 | NORMAL\n" +
                "S | undone | Question3 / Answer3 | Mod3 | NORMAL\n" +
                "S | undone | Question4 / Answer4 | Mod4 | NORMAL";

        // Ensure that the deleted question is no longer present in the storage
        Assertions.assertTrue(storageData.contains("Question1"));
        Assertions.assertFalse(storageData.contains("Question2"));
        Assertions.assertTrue(storageData.contains("Question3"));
        Assertions.assertTrue(storageData.contains("Question4"));
        Assertions.assertEquals(expectedStorageData, storageData);
    }
}
