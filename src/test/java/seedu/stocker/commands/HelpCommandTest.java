package seedu.stocker.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {
    @Test
    public void executeTest() {
        HelpCommand command = new HelpCommand();

        String expectedOutput = "add: Adds a new drug to the drug list. Parameters: NAME, EXPIRY DATE, QUANTITY,  \n"
                + "Example: add /n Doliprane /d 12/06/2035 /q 52\n"
                + "delete: Removes a drug from drug list. Parameters: Name  \n"
                + "Example: delete <Drug Name>\n"
                + "help: Shows program usage instructions. \n"
                + "Example: help\n"
                + "list: List all drug information that is being tracked by the system. \n"
                + "Example: list\n"
                + "find: Finds drug in inventory \n"
                + "Example: find panadol\n"
                + "bye: Exits the program.\n"
                + "Example: bye";

        assertEquals( new CommandResult(expectedOutput).feedbackToUser,command.execute().feedbackToUser);
    }
}
