package seedu.stocker.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.stocker.drugs.Inventory;

class AddCommandTest {

    @Test
    public void executeTest() {
        AddCommand command = new AddCommand("Paracetamol", "12/05/2024", 12L); 
        command.setData(new Inventory());
        String expectedOutput = "New drug added in the inventory: Paracetamol";
        assertEquals(command.execute().feedbackToUser, new CommandResult(expectedOutput).feedbackToUser);
        assertEquals(command.execute().getRelevantDrugs(), new CommandResult(expectedOutput).getRelevantDrugs());
    }
}
