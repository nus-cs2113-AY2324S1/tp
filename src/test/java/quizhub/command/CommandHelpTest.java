package quizhub.command;

import org.junit.jupiter.api.io.TempDir;
import quizhub.questionlist.QuestionList;
import quizhub.ui.Ui;
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


public class CommandHelpTest {

    private QuestionList questionList;
    private Ui mockUi;
    private MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(@TempDir Path tempDir) throws IOException {
        // Create a temporary file in the tempDir
        Path tempFile = tempDir.resolve("testStorage.txt");
        mockStorage = new MockStorage(tempFile.toString()); // Pass the temporary file path
        questionList = new QuestionList();
        mockUi = new Ui(mockStorage,questionList);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    private void testCliOutputCorrectness(String expectedOutput){
        String actualOutput = outputStreamCaptor.toString().trim();
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    
    /**
     * Test if help command displays the correct output
     * */
    @Test
    public void testHelpCommand() {
        /*
        String expectedOutput = "Here are the list of commands you can use:\r\n" +
                "    1. help - shows the list of commands you can use\r\n" +
                "    2. short [question]/[answer] - adds a short answer question and its answer to the list,\r\n" +
                "     3. list - shows the list of questions and answers,\r\n" +
                "     4. delete [question number] - deletes the question and answer at the specified number,\r\n" +
                "     5. find /[description] - displays all questions that " +
                "contains the the specified description,\r\n" +
                "     6. edit [question number] /description [description] - edits the description of the question " +
                "with the specified number,\r\n" +
                "     7. edit [question number] /answer [answer] - edits the answer to the question with the " +
                "specified number,\r\n" +
                "     8. start - starts the quiz,\r\n" +
                "     9. shuffle - shuffle quiz questions to a random order,\r\n" +
                "     10. markdiff [question number] [question difficulty] - sets the difficulty of question " +
                "with the specified number,\r\n" +
                "     11. bye - exits the program";
         */

        String expectedOutput= "Here are the list of commands you can use:" +
                System.lineSeparator() +
                "    1. help - shows the list of commands you can use" +
                System.lineSeparator() +
                "    2. short [question]/[answer]/[module]/[difficulty] - adds a short answer question and " +
                "its answer to the list," +
                System.lineSeparator() +
                "     3. list - shows the list of questions and answers," +
                System.lineSeparator() +
                "     4. delete [question number] - deletes the question and answer at the specified number," +
                System.lineSeparator() +
                "     5. find /[description] - displays all questions that contains the the specified description," +
                System.lineSeparator() +
                "     6. find /[module] - displays all questions that belong to the specified module," +
                System.lineSeparator() +
                "     7. edit [question number] /description [description] - edits the description of the question " +
                "with the specified number," +
                System.lineSeparator() +
                "     8. edit [question number] /answer [answer] - edits the answer to the question with " +
                "the specified number," +
                System.lineSeparator() +
                "     9. start /[quiz mode] [start details] /[qn mode] - " +
                "starts the quiz with option for /module or /all and /random or /normal," +
                System.lineSeparator() +
                "     10. shuffle - shuffle quiz questions to a random order," +
                System.lineSeparator() +
                "     11. markdiff [question number] [question difficulty] - sets the difficulty of question " +
                "with the specified number," +
                System.lineSeparator() +
                "     12. bye - exits the program";

        CommandHelp help = new CommandHelp();
        help.executeCommand(mockUi, mockStorage, questionList);
        testCliOutputCorrectness(expectedOutput);
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
