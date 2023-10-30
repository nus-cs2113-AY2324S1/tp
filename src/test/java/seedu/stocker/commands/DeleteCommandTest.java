package seedu.stocker.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;

class DeleteCommandTest {

    @Test
    public void executeTest() {
        // Create an inventory and add a drug to it
        Inventory inventory = new Inventory();
        Drug doliprane = new Drug("Doliprane", "12/06/2035");
        inventory.addNewDrug("doliprane", doliprane, "ABC1234", 52L);

        // Create a DeleteCommand for the drug
        DeleteCommand deleteCommand = new DeleteCommand("Doliprane");
        deleteCommand.setData(inventory, new SalesList(), new Cart());

        // Test the execution of the DeleteCommand
        String expectedOutput = "Drug removed from inventory: Doliprane";
        assertEquals(deleteCommand.execute().feedbackToUser, expectedOutput);

        // Ensure that the drug is no longer in the inventory
        assertEquals(0, inventory.getStockEntries().size());
    }
}
