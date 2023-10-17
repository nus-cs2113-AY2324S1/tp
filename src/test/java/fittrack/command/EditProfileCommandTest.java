package fittrack.command;

import fittrack.parser.CommandParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditProfileCommandTest {

    @Test
    void setArguments_help_helpOfHelp() {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setArguments("help", new CommandParser());
        assertEquals(HelpCommand.HELP, helpCommand.getHelpMessage());
    }
}
