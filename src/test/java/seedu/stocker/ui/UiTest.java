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
        toTest.printVersion1Help();

        String expectedOutput = "Here are a list of possible commands" + System.lineSeparator()
                + "1. add - Add a drug into the system" + System.lineSeparator()
                + "2. delete - Remove a drug from the system" + System.lineSeparator()
                + "3. list - List all current drugs in the system" + System.lineSeparator()
                + "4. find - Find a specific drug in the system" + System.lineSeparator()
                + "5. help - List all available commands" +System.lineSeparator()
                + System.lineSeparator()+ System.lineSeparator()
                + "Here is the formatting for the commands" + System.lineSeparator()
                + "For add:" + System.lineSeparator()
                + "add /n <drug name> /d <expiry date> /q <quantity>" + System.lineSeparator()
                + System.lineSeparator()
                + "For delete:" + System.lineSeparator()
                + "delete /n <drug name>" + System.lineSeparator() + System.lineSeparator()
                + "For list:" + System.lineSeparator()
                + "list" +System.lineSeparator() + System.lineSeparator()
                + "For find:" + System.lineSeparator()
                + "find <keyword>" + System.lineSeparator() +System.lineSeparator()
                + "For help:" + System.lineSeparator()
                + "help" + System.lineSeparator();

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
