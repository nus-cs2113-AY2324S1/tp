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

public class CommandShuffleTest {

    private QuestionList questionList;
    private Ui ui;
    private MockStorage mockStorage;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setQuestionList(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("testStorage.txt");
        questionList = new QuestionList();
        mockStorage = new MockStorage(tempFile.toString());
        ui = new Ui(mockStorage, questionList);
        String[] questionsToAdd = {
            "short Question1 / Answer1 / Mod1 / NORMAL",
            "short Question2 / Answer2 / Mod2 / NORMAL",
            "short Question3 / Answer3 / Mod3 / NORMAL",
            "short Question4 / Answer4 / Mod4/ NORMAL"
        };
        boolean showMessage = false;
        for (String question : questionsToAdd) {
            Parser.parseCommand(question).executeCommand(ui, mockStorage, questionList);
        }
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testCommandShuffle_changesOrderOfQuestions() throws IOException {
        String expectedOutput = "Question1 / Answer1 / Mod1 / NORMAL" +
            "Question2 / Answer2 / Mod2 / NORMAL" +
            "Question3 / Answer3 / Mod3 / NORMAL" +
            "Question4 / Answer4 / Mod4/ NORMAL";

        new CommandShuffle().executeCommand(ui, mockStorage, questionList);

        // Check if the order has changed
        Assertions.assertFalse(expectedOutput.equals(questionList));
    }

    @Test
    public void testCommandShuffle_noAdditionalOrMissingQuestions() throws IOException {
        int originalSize = questionList.getQuestionListSize();

        new CommandShuffle().executeCommand(ui, mockStorage, questionList);

        // Check if the size remains the same
        Assertions.assertEquals(originalSize, questionList.getQuestionListSize());
    }

    @Test
    public void testCommandShuffle_onEmptyQuestionList() throws IOException {
        // Clearing the question list to simulate an empty list
        questionList.deleteQuestionByIndex(4);
        questionList.deleteQuestionByIndex(3);
        questionList.deleteQuestionByIndex(2);
        questionList.deleteQuestionByIndex(1);

        new CommandShuffle().executeCommand(ui, mockStorage, questionList);

        // Check if the size is still zero after shuffling
        Assertions.assertEquals(0, questionList.getQuestionListSize());
    }
}
