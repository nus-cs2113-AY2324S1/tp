package fittrack.command;

import org.junit.jupiter.api.Test;

import static fittrack.command.HelpCommand.USAGE;
import static fittrack.command.InvalidCommand.MESSAGE_INVALID_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    @Test
    void execute_help_pass() {
        HelpCommand helpCommand = new HelpCommand("help");
        helpCommand.setArguments("");
        CommandResult result = helpCommand.execute();
        assertEquals(HelpCommand.HELP, result.getFeedback());
    }

    @Test
    void setArguments_emptyString_helpOfHelp() {
        HelpCommand helpCommand = new HelpCommand("help");
        helpCommand.setArguments("");
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_help_helpOfHelp() {
        HelpCommand helpCommand = new HelpCommand("help help");
        helpCommand.setArguments("help");
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_exit_helpOfExit() {
        HelpCommand helpCommand = new HelpCommand("help exit");
        helpCommand.setArguments("exit");
        assertEquals(ExitCommand.HELP, helpCommand.getHelpMessage());
    }

    @Test
    void setArguments_foo_invalidCmdMsg() {
        HelpCommand helpCommand = new HelpCommand("help foo");
        helpCommand.setArguments("foo");
        assertEquals(
                String.format(MESSAGE_INVALID_COMMAND, "foo") + "\n" + USAGE, 
                helpCommand.getHelpMessage()
        );
    }
}
