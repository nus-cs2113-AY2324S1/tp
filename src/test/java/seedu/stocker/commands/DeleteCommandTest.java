package seedu.stocker.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.Inventory;

class DeleteCommandTest {

    @Test
    public void executeTest() {
        // Create an inventory and add a drug to it
        Inventory inventory = new Inventory();
        Drug doliprane = new Drug("Doliprane", "12/06/2035", 52L);
        inventory.addDrug(doliprane);

        // Create a DeleteCommand for the drug
        DeleteCommand deleteCommand = new DeleteCommand("Doliprane");
        deleteCommand.setData(inventory);

        // Test the execution of the DeleteCommand
        String expectedOutput = "Drug removed from inventory: Name: Doliprane, Expiry Date: 12/06/2035, Quantity: 52";
        assertEquals(deleteCommand.execute().feedbackToUser, expectedOutput);

        // Ensure that the drug is no longer in the inventory
        assertEquals(inventory.getAllDrugs().size(), 0);
    }
}