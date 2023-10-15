package cashleh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui testUi = new Ui();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private final String HORIZONTAL_LINE = "\t____________________________________________________________";
    private final String SEPARATOR = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testPrintSingle() {
        testUi.printText("Welcome to CashLeh!");
        assertEquals(HORIZONTAL_LINE + SEPARATOR + "\tWelcome to CashLeh!" + SEPARATOR
                + HORIZONTAL_LINE + SEPARATOR, outputStreamCaptor.toString());
    }

    @Test
    void testPrintArray() {
        testUi.printMultipleText(new String[]{"Welcome to CashLeh!", "What can I do for you?"});
        assertEquals(HORIZONTAL_LINE + SEPARATOR + "\tWelcome to CashLeh!" + SEPARATOR
                + "\tWhat can I do for you?" + SEPARATOR + HORIZONTAL_LINE + SEPARATOR, outputStreamCaptor.toString());
    }

    @Test
    void testPrintArrayList() {
        ArrayList<String> testArrayList = new ArrayList<>();
        testArrayList.add("Welcome to CashLeh!");
        testArrayList.add("What can I do for you?");
        testUi.printMultipleText(testArrayList);
        assertEquals(HORIZONTAL_LINE + SEPARATOR + "\tWelcome to CashLeh!" + SEPARATOR
                + "\tWhat can I do for you?" + SEPARATOR + HORIZONTAL_LINE + SEPARATOR, outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
