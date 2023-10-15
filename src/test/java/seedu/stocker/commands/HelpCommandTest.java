package seedu.stocker.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {
    @Test
    public void executeTest() {
        HelpCommand command = new HelpCommand();

        String expectedOutput = System.getProperty("line.separator")
                + "add: Adds a new drug to the drug list. Parameters: NAME, EXPIRY DATE, QUANTITY,  "
                + System.getProperty("line.separator")
                + "Example: add /n Doliprane /d 12/06/2035 /q 52"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "delete: Removes a drug from drug list. Parameters: Name  "
                + System.getProperty("line.separator")
                + "Example: delete <Drug Name>"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "help: Shows program usage instructions. "
                + System.getProperty("line.separator")
                + "Example: help"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "list: List all drug information that is being tracked by the system. "
                + System.getProperty("line.separator")
                + "Example: list"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "find: Finds drug in inventory "
                + System.getProperty("line.separator")
                + "Example: find panadol"
                + System.getProperty("line.separator")
                + System.getProperty("line.separator")
                + "bye: Exits the program."
                + System.getProperty("line.separator")
                + "Example: bye";

        assertEquals( new CommandResult(expectedOutput).feedbackToUser,command.execute().feedbackToUser);
    }
}
