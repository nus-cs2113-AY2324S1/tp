package fittrack.command;

import fittrack.parser.CommandParser;
import org.junit.jupiter.api.Test;

import static fittrack.command.HelpCommand.MESSAGE_INVALID_COMMAND;
import static org.junit.jupiter.api.Assertions.*;

class HelpCommandTest {

    @Test
    void execute_help_pass() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setArguments("", new CommandParser());
        CommandResult result = helpCommand.execute();
        assertEquals(HelpCommand.HELP, result.getFeedback());
    }

    @Test
    void setArguments_emptyString_helpOfHelp() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setArguments("", new CommandParser());
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_help_helpOfHelp() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setArguments("help", new CommandParser());
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_exit_helpOfExit() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setArguments("exit", new CommandParser());
        assertEquals(ExitCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_foo_invalidCmdMsg() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setArguments("foo", new CommandParser());
        assertEquals(String.format(MESSAGE_INVALID_COMMAND, "foo"), helpCommand.getHelpMessage());
    }
}