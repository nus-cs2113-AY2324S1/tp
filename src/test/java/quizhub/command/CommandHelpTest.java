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
        actualOutput = actualOutput.replace("\r", "");
        actualOutput = actualOutput.replace("\n", "");
        actualOutput = actualOutput.replace(System.lineSeparator(), "");
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    
    /**
     * Test if help command displays the correct output
     * */
    @Test
    public void testHelpCommand() {
        String expectedOutput= "Here are the list of commands you can use:" +
                "    1. help - shows the list of commands you can use," +
                "    2. short [question]/[answer]/[module]/[difficulty] - adds a short answer question and " +
                "its answer to the list," +
                "    3. list - shows the list of questions and answers," +
                "    4. delete [question number] - deletes the question and answer at the specified number," +
                "    5. find /[description] - displays all questions that contains the the specified description," +
                "    6. find /[module] - displays all questions that belong to the specified module," +
                "    7. edit [question number] /description [description] - edits the description of the question " +
                "with the specified number," +
                "    8. edit [question number] /answer [answer] - edits the answer to the question with " +
                "the specified number," +
                "    9. start /[quiz mode] [start details] /[qn mode] - " +
                "starts the quiz with option for /module or /all and /random or /normal," +
                "    10. shuffle - shuffle quiz questions to a random order," +
                "    11. markdiff [question number] /[question difficulty] - sets the difficulty of question " +
                "with the specified number," +
                "    12. bye - exits the program";
        
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
