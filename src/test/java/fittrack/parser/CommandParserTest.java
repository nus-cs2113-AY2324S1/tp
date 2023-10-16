package fittrack.parser;

import fittrack.command.Command;
import fittrack.command.HelpCommand;
import fittrack.command.InvalidCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandParserTest {

    @Test
    void parseCommand() {
    }

    @Test
    void getBlankCommand_help_helpCommand() {
        Command blankCommand = new CommandParser().getBlankCommand("help");
        assertInstanceOf(HelpCommand.class, blankCommand);
    }

    @Test
    void getBlankCommand_foo_invalidCommand() {
        Command blankCommand = new CommandParser().getBlankCommand("foo");
        assertInstanceOf(InvalidCommand.class, blankCommand);
    }

    @Test
    void parseProfile_h180w80_success() {
        try {
            double[] profile = new CommandParser().parseProfile("h/180 w/80");
            assertEquals(180., profile[0]);
            assertEquals(80., profile[1]);
        } catch (PatternMatchFailException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseProfile_fail() {
        CommandParser parser = new CommandParser();
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/ w/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/180 w/"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/ w/80"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("h/180 80"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("180 w/80"));
        assertThrows(PatternMatchFailException.class, () -> parser.parseProfile("180 80"));
        assertThrows(NumberFormatException.class, () -> parser.parseProfile("h/180 w/eighty"));
    }

    @Test
    void getFirstWord_helloWorld_hello() {
        String firstWord = new CommandParser().getFirstWord("hello world");
        assertEquals("hello", firstWord);
    }

    @Test
    void getFirstWord_loremIpsum_lorem() {
        String firstWord = new CommandParser().getFirstWord(
                "Lorem\nipsum\ndolor sit amet, consectetur adipisicing elit, \n" +
                        "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        );
        assertEquals("Lorem", firstWord);
    }

    @Test
    void getFirstWord_hi_hi() {
        String firstWord = new CommandParser().getFirstWord("hi");
        assertEquals("hi", firstWord);
    }

    @Test
    void getFirstWord_emptyString_fail() {
        assertThrows(
                AssertionError.class,
                () -> new CommandParser().getFirstWord("")
        );
    }
}
