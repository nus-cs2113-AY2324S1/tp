package seedu.stocker.ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    @Test
    public void printVersion1HelpTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui toTest = new Ui();
        toTest.printversion1Help();

        String expectedOutput = "Here are a list of possible commands" + System.getProperty("line.separator")
                + "1. add - Add a drug into the system" + System.getProperty("line.separator")
                + "2. delete - Remove a drug from the system" + System.getProperty("line.separator")
                + "3. list - List all current drugs in the system" + System.getProperty("line.separator")
                + "4. find - Find a specific drug in the system" + System.getProperty("line.separator")
                + "5. help - List all available commands" + System.getProperty("line.separator")
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "Here is the formatting for the commands" + System.getProperty("line.separator")
                + "For add:" + System.getProperty("line.separator")
                + "add /n <drug name> /d <expiry date> /q <quantity>" + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "For delete:" + System.getProperty("line.separator")
                + "delete /n <drug name>" + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "For list:" + System.getProperty("line.separator")
                + "list" + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "For find:" + System.getProperty("line.separator")
                + "find <keyword>" + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "For help:" + System.getProperty("line.separator")
                + "help" + System.getProperty("line.separator");

        assertEquals(expectedOutput, outContent.toString());


    }

    @Test
    public void getIndexedListForViewing_success() {
        String expectedOutput = "\t1. Paracetamol" + System.lineSeparator()
            +  "\t2. Ibuprofene" + System.lineSeparator()
            +  "\t3. Doliprane" + System.lineSeparator();

        List<String> input = Arrays.asList("Paracetamol", "Ibuprofene", "Doliprane");
        assertEquals(expectedOutput, new Ui().getIndexedListForViewing(input));
    }
}
