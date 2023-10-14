package seedu.stocker.commands;

import org.junit.jupiter.api.Test;
import seedu.stocker.drugs.Inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    @Test
    public void executeTest() {
        ListCommand command = new ListCommand();
        command.setData(new Inventory());
        String expectedOutput = "The inventory is empty.";
        assertEquals( new CommandResult(expectedOutput).feedbackToUser,command.execute().feedbackToUser);
    }
}
