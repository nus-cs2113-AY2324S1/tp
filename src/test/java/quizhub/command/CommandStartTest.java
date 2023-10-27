package quizhub.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import quizhub.parser.Parser;
import quizhub.questionlist.QuestionList;
import quizhub.storage.MockStorage;
import quizhub.ui.MockUi;
import quizhub.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandStartTest {

    private QuestionList questionList;
    private MockStorage mockStorage;
    private Ui ui;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    /**
     * Compares system standard output with expected output
     * Assertion error is thrown if the two are not the same
     */
    private void testCliOutputCorrectness(String expectedOutput) {
        String actualOutput = outputStreamCaptor.toString().trim();
        actualOutput = actualOutput.replace("\r", "");
        actualOutput = actualOutput.replace("\n", "");
        actualOutput = actualOutput.replace(System.lineSeparator(), "");
        assertEquals(expectedOutput, actualOutput);
    }
    @Nested
    public class UninitialisedQnListBlock {
        @BeforeEach
        public void setUp(@TempDir Path tempDir) throws IOException {
            Path tempFile = tempDir.resolve("testStorage.txt");
            questionList = new QuestionList();
            mockStorage = new MockStorage(tempFile.toString());
            ui = new Ui(mockStorage, questionList);
            System.setOut(new PrintStream(outputStreamCaptor));
        }
        @BeforeEach
        public void setUpOutput() {
            System.setOut(new PrintStream(outputStreamCaptor));
        }
        @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
        }

        @Test
        public void testStartQuizWithNoQuestions() {
            String input  = "start /all /random";
            String expectedOutput = "No questions found! Add questions before starting the quiz.";
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }

    }
    /**
     * Test starting quiz when question list is empty
     * */
    @Nested
    public class InitialisedQnListWithErrorBlock {
        /**
         * Create a new blank question list
         */
        @BeforeEach
        public void setQuestionList(@TempDir Path tempDir) throws IOException {
            Path tempFile = tempDir.resolve("testStorage.txt");
            questionList = new QuestionList();
            mockStorage = new MockStorage(tempFile.toString());
            ui = new Ui(mockStorage, questionList);
            String[] questionsToAdd = {"short Question1 / Answer1 / Mod1 / NORMAL",
                "short Question2 / Answer2 / Mod2 / NORMAL",
                "short Question3 / Answer3 / Mod3 / NORMAL",
                "short Question4 / Answer4 / Mod4/ NORMAL"};
            boolean showMessage = false;
            for (String question : questionsToAdd) {
                Parser.parseCommand(question).executeCommand(ui, mockStorage, questionList);
            }
            questionList.markQuestionAsDone(1, showMessage);
            questionList.markQuestionAsDone(3, showMessage);
            System.setOut(new PrintStream(outputStreamCaptor));
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
         * Test starting quiz without specifying quiz mode
         */
        @Test
        public void testStartQuizNoQuizMode() {
            String input = "start";
            String expectedOutput = CommandStart.MISSING_QUIZ_MODE_MSG.strip().replace(System.lineSeparator(), "")
                    + CommandStart.INVALID_FORMAT_MSG;
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }

        /**
         * Test starting quiz in all mode without start details without question mode
         */
        @Test
        public void testStartQuizAllModeNoDetailsNoQnMode() {
            String input = "start /all";
            String expectedOutput = CommandStart.MISSING_QN_MODE_MSG.strip() + CommandStart.INVALID_FORMAT_MSG;
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }

        /**
         * Test starting quiz in all mode with start details without question mode
         */
        @Test
        public void testStartQuizAllModeWithDetailsNoQnMode() {
            String input = "start /all details";
            String expectedOutput = CommandStart.MISSING_QN_MODE_MSG.strip() + CommandStart.INVALID_FORMAT_MSG;
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }
        /**
         * Test starting quiz in module mode without start details without question mode
         */
        @Test
        public void testStartQuizModuleModeNoDetailsNoQnMode() {
            String input = "start /module";
            String expectedOutput = CommandStart.MISSING_START_DETAILS.strip() + CommandStart.INVALID_FORMAT_MSG;
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }

        /**
         * Test starting quiz in module mode with start details without question mode
         */
        @Test
        public void testStartQuizModuleModeWithDetailsNoQnMode() {
            String input = "start /module cs2113";
            String expectedOutput = CommandStart.MISSING_QN_MODE_MSG.strip() + CommandStart.INVALID_FORMAT_MSG;
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }

        /**
         * Test starting quiz in module mode without start details with question mode
         */
        @Test
        public void testStartQuizModuleModeNoDetailsWithQnMode() {
            String input = "start /module /random";
            String expectedOutput = CommandStart.MISSING_START_DETAILS.strip() + CommandStart.INVALID_FORMAT_MSG;
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }
        /**
         * Test starting quiz in all mode with excessive arguments at the back
         */
        @Test
        public void testStartQuizAllModeWithDetailsWithQnModeTooManyArguments() {
            String input = "start /all Mod2 /normal random";
            String expectedOutput = CommandStart.TOO_MANY_ARGUMENTS_MSG.strip() + CommandStart.INVALID_FORMAT_MSG;
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }
        /**
         * Test starting quiz in module mode with excessive arguments at the back
         */
        @Test
        public void testStartQuizModuleModeWithDetailsWithQnModeTooManyArguments() {
            String input = "start /module Mod2 /random normal";
            String expectedOutput = CommandStart.TOO_MANY_ARGUMENTS_MSG.strip() + CommandStart.INVALID_FORMAT_MSG;
            Command command = Parser.parseCommand(input);
            command.executeCommand(ui, mockStorage, questionList);
            testCliOutputCorrectness(expectedOutput);
        }
    }

    @Nested
    public class InitialisedQnListWithoutErrorBlock {
        MockUi mockUi;
        @BeforeEach
        public void setQuestionList(@TempDir Path tempDir) {
            // Create a temporary file in the tempDir
            Path tempFile = tempDir.resolve("testStorage.txt");
            mockStorage = new MockStorage(tempFile.toString()); // Pass the temporary file path
            questionList = new QuestionList();
            mockUi = new MockUi(questionList, mockStorage);
            String[] questionsToAdd = {"short Question1 / Answer1 / Mod1 / NORMAL",
                "short Question2 / Answer2 / Mod2 / NORMAL",
                "short Question3 / Answer3 / Mod3 / NORMAL",
                "short Question4 / Answer4 / Mod4/ NORMAL"};
            for (String question : questionsToAdd) {
                Parser.parseCommand(question).executeCommand(ui, mockStorage, questionList);
            }
        }
        /**
         * Test starting quiz in all mode without start details with question mode random
         */
        @Test
        public void testStartQuizAllModeNoDetailsWithRandomQnMode() {
            mockUi.setUserInput("Answer1");
            mockUi.setUserInput("Answer2");
            mockUi.setUserInput("Answer3");
            mockUi.setUserInput("Answer4");
            String input = "start /all /normal";
            Command command = Parser.parseCommand(input);
            command.executeCommand(mockUi, mockStorage, questionList);
            assertEquals("    Your score: 4/4", mockUi.getLastDisplayedMessage());
        }

        /**
         * Test starting quiz in all mode with start details with question mode
         */
        @Test
        public void testStartQuizAllModeWithDetailsWithQnMode() {
            mockUi.setUserInput("Answer1");
            mockUi.setUserInput("Answer8");
            mockUi.setUserInput("Answer3");
            mockUi.setUserInput("Answer2");
            String input = "start /all details /normal";
            Command command = Parser.parseCommand(input);
            command.executeCommand(mockUi, mockStorage, questionList);
            assertEquals("    Your score: 2/4", mockUi.getLastDisplayedMessage());
        }
        /**
         * Test starting quiz in module mode with start details with question mode
         */
        @Test
        public void testStartQuizModuleModeWithDetailsWithQnMode() {
            mockUi.setUserInput("Answer1");
            String input = "start /module Mod3 /normal";
            Command command = Parser.parseCommand(input);
            command.executeCommand(mockUi, mockStorage, questionList);
            assertEquals("    Your score: 0/1", mockUi.getLastDisplayedMessage());
        }

        /**
         * Test starting quiz in module mode with multiple start details with question mode
         */
        @Test
        public void testStartQuizModuleModeWithMultipleDetailsWithQnMode() {
            mockUi.setUserInput("Answer3");
            mockUi.setUserInput("Answer4");
            mockUi.setUserInput("Answer2");
            String input = "start /module Mod3 Mod1 Mod2 /normal";
            Command command = Parser.parseCommand(input);
            command.executeCommand(mockUi, mockStorage, questionList);
            assertEquals("    Your score: 2/3", mockUi.getLastDisplayedMessage());
        }

    }
}
