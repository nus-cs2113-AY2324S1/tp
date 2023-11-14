package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitCommandTest {
    @Test
    void testExecute() {
        Parser parser = new Parser();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.execute("", parser.container);

        String output = outputStream.toString();
        String expectedOutput = "Thanks for using SysLib! We have saved the current resources and events."
                + System.lineSeparator() +
                "See you next time!" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

}
