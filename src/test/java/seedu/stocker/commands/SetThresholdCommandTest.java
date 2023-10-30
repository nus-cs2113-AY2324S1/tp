package seedu.stocker.commands;

import org.junit.jupiter.api.Test;
import seedu.stocker.drugs.Inventory;
import seedu.stocker.drugs.SalesList;
import seedu.stocker.drugs.Cart;
import seedu.stocker.drugs.Drug;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetThresholdCommandTest {

    @Test
    public void executeTest() {
        // Create a new inventory
        Inventory inventory = new Inventory();
        Drug drug1 = new Drug("Panadol", "04/07/2030");
        inventory.addNewDrug("Panadol", drug1, 120);


        // Create the SetThresholdCommand
        SetThresholdCommand command = new SetThresholdCommand("Panadol", 75);
        // Set the modified inventory for the command
        command.setData(inventory, new SalesList(), new Cart());

        command.execute();

        String expectedOutput = "Threshold quantity set for Panadol: 75";

        assertEquals(command.execute().feedbackToUser, expectedOutput);

    }

    @Test
    public void executeTestWithNoDrug() {
        Inventory inventory = new Inventory();
        Drug drug1 = new Drug("Panadol", "04/07/2030");
        inventory.addNewDrug("Panadol", drug1, 120);
        // Create the SetThresholdCommand for a drug that doesn't exist
        SetThresholdCommand command = new SetThresholdCommand("Paracetamol", 75);

        command.setData(inventory, new SalesList(), new Cart());
        command.execute();

        String expectedOutput = "Drug not found.";

        // Check if the result indicates the drug was not found
        assertEquals(command.execute().feedbackToUser, expectedOutput);
    }
}
